package app_2D.vue;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Trait
{
	//on declare la liste des point 
	private List<Point> listePoints=new ArrayList<>();
	
	private GeneralPath dessinDuTrait = null;
	
	public Trait()
	{
	}
	
	//on ajoute les points à la liste
	public void addPoint(Point p)
	{
		listePoints.add(p);
	}
		
	public void dessineToi(Graphics2D graf2D)
	{
		System.out.println("Trait dessineToi lesTraits.size(): " + listePoints.size());
		// Ici, le dessin du trait.
		
		if (dessinDuTrait == null)
			graf2D.draw(buildPath());
		else
			graf2D.draw(dessinDuTrait);
	}
	
	//on dessine le trait avec le buildpath
	private GeneralPath buildPath()
	{
		System.out.println("Trait buildPath()");
		
		GeneralPath path = new GeneralPath(); 
		int compteur=0;
		for (Point p:listePoints)
		{
			int x = (int) p.getX();
			int y = (int) p.getY();
			
			if (compteur==0)
			{
				path.moveTo(x, y);
				compteur++;
			}
			if (compteur!=0)
			{
				path.lineTo(x, y);
				compteur++;
			}
		}
		return path;
	}
	
	void termineToi()
	{
		dessinDuTrait = buildPath();
	}
}
