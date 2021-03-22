package app_2D.modele;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class PyPoint2D
{
	double x;
	double y;
	
	public PyPoint2D(double _x, double _y)
	{
		x = _x;
		y = _y;
	}
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public String toString()
	{
		return "[" + hashCode() + "] x=" +  x + " y=" + y;
	}
	

}
