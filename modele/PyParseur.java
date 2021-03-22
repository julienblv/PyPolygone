package app_2D.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class PyParseur
{
	PyPoint2D point = null;
	PyContour2D contour = null;
	private PyPolygone2D polygone = null;
	private Set<PyPolygone2D> lspolygone=new HashSet<PyPolygone2D>();
	
	public PyPolygone2D getPolygone() {
		return polygone;
	}
	
	public PyParseur(String nomDuFichier) throws NumberFormatException, IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(nomDuFichier));
		String line;
	
		while ((line = in.readLine()) != null)
		{
			line = line.trim();
			
			if (line.length() == 0)
			continue;
			
			char c = line.charAt(0);
			if (c == '#')
			continue;
			
			if (c == '{')
			{
			//liste de polygone
			polygone = new PyPolygone2D(in);
			lspolygone.add(polygone);
			
			}
		}
		in.close();
		
	}
	
	public Set<PyPolygone2D> getLesPolygones(){
		return lspolygone;
	}
	
	public static void main(String[] argv)
	{
		try
		{
			PyParseur parseur = new PyParseur("2-polygones-had.poly");
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}