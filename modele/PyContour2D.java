package app_2D.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PyContour2D
{
	List<PyPoint2D> lesPoints= new ArrayList<>();
	
	double x,y;
	PyContour2D(BufferedReader in) throws IOException
	{
		String line;
		while ((line = in.readLine()) != null)
		{			
			line = line.trim();

			System.out.println(line);
			if (line.length() == 0)
				continue;

			char c = line.charAt(0);

			if (c == ']')
				return;

			String[] result = line.split("\\s");
   			System.out.println("POINT: " + line + " -> " + result.length);
			
			x = Double.parseDouble(result[0]);
			y = Double.parseDouble(result[1]);
			
			PyPoint2D point2D = new PyPoint2D(x, y);
			lesPoints.add(point2D);
		}
		
	}
	public List<PyPoint2D> getLesPoints() {
		return lesPoints;
	}
	
	
}

