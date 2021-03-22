package app_2D.vue;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app_2D.modele.Log4j;
import app_2D.modele.PyContour2D;
import app_2D.modele.PyPoint2D;
import app_2D.modele.PyPolygone2D;

public class PyDessinPnl extends JPanel
{
	
	//private PyScene2D scene2D = new PyScene2D();
	//int xd, yd;
	Color couleur_rec = Color.green;
	Dragueur dragueur;
	//GraTupple2d vecteur=new GraTupple2d();;
	double distance = 10;
	
	public PyDessinPnl()
	{		
		// écouteur d’événement de la souris
		dragueur = new Dragueur(this);
		addMouseListener(dragueur );
		addMouseMotionListener(dragueur);
	}

	@Override
	public void paintComponent(Graphics g)
	{ 
		///////////////////////////////// Utilisation de Log4j + création du fichiers de logs ////////////////////////////////////
		PyFenetrePrincipale.PyLogger.info("Ouverture_du_programme");
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		super.paintComponent(g);
		System.out.println("\nFen_Dessin paintComponent");

		Graphics2D graf_2D = (Graphics2D)g;

		//----------------------------------------------------------
		//trait

		List<Trait> lesTraits = dragueur.getscene().getLesTraits();

		//on dessine les traits

		for (Trait t: lesTraits)
		{
			t.dessineToi(graf_2D);
		}

		//----------------------------------------------------------
		//Les polygones
		for (PyPolygone2D pyPolygone2D:dragueur.getscene().getLesPolygones())
		{ 
			Polygon java2DPolygonExterne = new Polygon();

			//on cherche les point s du contour externe
			PyContour2D pyContourExterne = pyPolygone2D.getContourExterne();
					
							
			//on les mets dans une liste
			List<PyPoint2D> lesPointsExt = pyContourExterne.getLesPoints();
			//System.out.println("\n--------------------------");
			for (PyPoint2D point:lesPointsExt)
			{
				// Ajouter les points du contour externe.
				java2DPolygonExterne.addPoint((int)point.getX(), (int)point.getY());
			

			}
			Area java2DAreaPolygon = new Area(java2DPolygonExterne);

			//----------------------------------------------------------
			// Les trous.

			//création du trou
			//liste des trou dans le fichier
			ArrayList<PyContour2D> pyLesTrous = pyPolygone2D.getLesTrous();

			//liste des points dans le trou

			//on parcours la liste des trou
			for (PyContour2D trou:pyLesTrous)
			{
				Polygon java2DPolygonTrou = new Polygon();
				List<PyPoint2D> lesPointsTrou = trou.getLesPoints();

				for (PyPoint2D point:lesPointsTrou) {

					java2DPolygonTrou.addPoint((int)point.getX(), (int)point.getY());
				}

				Area java2DAreaTrou = new Area(java2DPolygonTrou);
				// Soustraction du trou
				java2DAreaPolygon.subtract(java2DAreaTrou);
				graf_2D.fill(java2DAreaPolygon);
				//------------------
				//dessin vecteur trou
				GraTupple2d vecteurU;
				GraTupple2d vecteurV;
				int nbPoint2D=lesPointsTrou.size();
				
			} 
			//------------------------------------------------
			//dessin vecteur polygone
			GraTupple2d vecteurU;
			GraTupple2d vecteurV;
			int nbPoint2D=lesPointsExt.size();
			
			for (int i=0;i<nbPoint2D;i++)
			{
				System.out.println();
				PyPoint2D p0=lesPointsExt.get(i);
				PyPoint2D pApres=lesPointsExt.get((i+1)%nbPoint2D);
				PyPoint2D pAvant=lesPointsExt.get((i-1+nbPoint2D)%nbPoint2D);
				
				//creation des deux vecteur normalisé
				vecteurU=GraTupple2d.buildVectorNormalized(p0, pAvant);
				vecteurV=GraTupple2d.buildVectorNormalized(p0, pApres);

				vecteurU.mul(10);
				vecteurV.mul(10);
				
				//Deuxieme point du vecteurU
				int xpoint2VecteurU=(int)p0.getX()+(int)vecteurU.coords[0];
				int ypoint2VecteurU=(int)p0.getY()+(int)vecteurU.coords[1];
				
				Math.round(xpoint2VecteurU);
				Math.round(ypoint2VecteurU);
				
				//Deuxieme point du vecteurV
				int xpoint2VecteurV=(int)p0.getX()+(int)vecteurV.coords[0];
				int ypoint2VecteurV=(int)p0.getY()+(int)vecteurV.coords[1];
				
				Math.round(xpoint2VecteurV);
				Math.round(ypoint2VecteurV);
				
				((Graphics2D) g).setStroke(new BasicStroke(2));
				g.setColor(Color.RED);
				g.drawLine((int)p0.getX(),(int)p0.getY(),xpoint2VecteurU,ypoint2VecteurU );
				
				((Graphics2D) g).setStroke(new BasicStroke(2));
				g.setColor(Color.white);
				g.drawLine((int)p0.getX(),(int) p0.getY(), xpoint2VecteurV, ypoint2VecteurV);
				
				GraTupple2d.calculeAngleDeg_0_180(vecteurU,vecteurV);
				
			}
			
			//////////////////// Affiche la Bounding Box /////////////
			graf_2D.draw(pyPolygone2D.getBoundingBox());
			/////////////////////////////////////////////////////////
		}

	}







}
