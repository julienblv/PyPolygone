package app_2D.vue;

import java.awt.geom.Point2D;

public class vecteur {
	double x;
	double y;
	
	public double coords[] = new double[2];
	
	public vecteur(Point2D.Double point)
    {
        coords[0] = point.x;
        coords[1] = point.y;
    }
	
}
