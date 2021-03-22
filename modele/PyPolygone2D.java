package app_2D.modele;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class PyPolygone2D
{
	private PyContour2D contourExterne			= null; // Contient les points du polygone
	private ArrayList<PyContour2D> lesTrous		= new ArrayList<>(); // contient tous les trous du polygone
	String nomDuPolygone = null;
	
	private Rectangle2D boundingBox = null;
	//liste des points de la figure
	
//////////////////////////////////////////// Code pour le rectangle englobant ( Min et Max ) //////////////////////////////////////////////////////////

	public double getMaxX() {
		return boundingBox.getMaxX();
	}
	
	public double getMinX() {
		return boundingBox.getMinX();
	}
	
	public double getMaxY() {
		return boundingBox.getMaxY();
	}
	
	public double getMinY() {
		return boundingBox.getMinY();
	}
	
	
	
	// création d'un getter pour créer bounding box et la renvoyer dans les autres pgrm
	public Rectangle2D getBoundingBox() {
		return boundingBox;
	}

	
	public void calculBoundingBox() {
		
		List<PyPoint2D> maListe = contourExterne.lesPoints;
		double maxX = -Double.MAX_VALUE;
		double minX =  Double.MAX_VALUE;
		
		double maxY = -Double.MAX_VALUE;
		double minY =  Double.MAX_VALUE;
		
		double x;
		double y;
		
		double width;
		double height;
		
		
		
		for(int i =0; i<maListe.size();i++)
		{
			PyPoint2D monPoint = maListe.get(i);
			
			// Sans la fonction ToString : System.out.println("le point : " + monPoint.getX() + " " + monPoint.getY());
			System.out.println("le point : " + monPoint);
			
			x = monPoint.getX();
			y = monPoint.getY();
			
			if(x ==  0) {
				break;
			}
			
			if (x < minX)
			{
				minX = x;
			}
			
			if (x > maxX) {
				maxX = monPoint.getX();
				
			}
			
			if (y < minY) {
				minY = monPoint.getY();
			}
			
			if (y > maxY) {
				maxY = monPoint.getY();
				
			}
			
		}
		
		System.out.println("le Xmin est : " + minX );
		System.out.println("le Xmax est : " + maxX );
		System.out.println("le Ymin est : " + minY );
		System.out.println("le Ymax est : " + maxY );
		
		width =  (maxX) - (minX);
		height =  (maxY) - (minY);

	    // mettre le rectangle 2D dans la variable crée au début
		boundingBox = new Rectangle2D.Double(minX, minY, width, height);
		
		
	}	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public PyPolygone2D(BufferedReader in) throws IOException
	{
		String line;
		while ((line = in.readLine()) != null)
		{			
			line = line.trim();
			if (line.length() == 0)
				continue;
			
			if (nomDuPolygone == null)
			{
				nomDuPolygone = line;
				continue;
			}
			
			char c = line.charAt(0);
			if (c == '[')
			{
				if (contourExterne == null)
				{
					
					contourExterne = new PyContour2D(in);
				}
				else
				{
					PyContour2D trou = new PyContour2D(in);
					lesTrous.add(trou);
				}			
			}
			else if (c == '}')
			{
				calculBoundingBox(); 
				return ;
			} 
		}
	}
	
	@Override
	public String toString()
	{
		return "POLYGONE Contour externe Nb. points= " + contourExterne.lesPoints.size() + " Nb. trous= " + lesTrous.size();
	}

	public PyContour2D getContourExterne(){
		return contourExterne;
	}

	public ArrayList<PyContour2D> getLesTrous() {
		return lesTrous;
	}

	public double getHauteur() {
		return boundingBox.getHeight();
	}
	

}
