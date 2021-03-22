package app_2D.modele.algos;

import java.awt.geom.Rectangle2D;

import app_2D.modele.PyPolygone2D;
import app_2D.modele.PyScene2D;
import app_2D.vue.GraTupple2d;
import app_2D.vue.PyFenetrePrincipale;

public class PyAlgo1 extends PyAlgoBase
{

	// private static final BufferedReader String = null;

	public PyAlgo1(PyScene2D _scene2D)
	{
		super(_scene2D);		
	}
	
	// algorithme de calcul 
	
	public void executeAlgo() //throws IOException
	{
		PyFenetrePrincipale.PyLogger.info("PyAlgo1 executeAlgo --> déclaration des variables");
	
		PyFenetrePrincipale.PyLogger.info("PyAlgo1 executeAlgo --> démarrage de l'algorithme");
		
		// début 
		double positionVerticale = 0 ;
		
		for(int i =0 ; i < scene2D.getLesPolygones().size();i++) {	
			
			// récupération de la liste de polygones
			PyPolygone2D polygoneI = scene2D.getLesPolygones().get(i);
			
		
			System.out.println(polygoneI);
			
			// récup de la bounding box de polygoneI
			Rectangle2D boundingbox = polygoneI.getBoundingBox();
			
			if(i==0) { // cas i=0 placement du 1er rectangle
				boundingbox.setRect(0, 0, boundingbox.getWidth(), boundingbox.getHeight());
				continue;
			}
			
			PyPolygone2D boxPrecedente = scene2D.getLesPolygones().get(i-1);
			boundingbox.setRect(0, boundingbox.getMaxY(), boundingbox.getWidth(), boundingbox.getHeight());
			}
			
			
		PyFenetrePrincipale.PyLogger.info("PyAlgo1 executeAlgo --> fin de l'algorithme");
			
		}
		
	}
 
	
//	  //TODO	    
//
//
//	    trouver le vecteur de déplacement 
//
//	    appliquer le vecteur aux polygones + boites englobantes
//
//	    réafficher ( repaint )
				
		//}



