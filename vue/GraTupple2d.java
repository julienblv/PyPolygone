package app_2D.vue;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import app_2D.modele.PyPoint2D;


/**
 * 2 coordonnées doubles.
 */


public class GraTupple2d
{
	public static final GraTupple2d O		= new GraTupple2d(0, 0);
	public static final GraTupple2d axeOx	= new GraTupple2d(1, 0);
	public static final GraTupple2d axeOy	= new GraTupple2d(0, 1);

	private static final double PRECISION_NORME_NORMALE_d = 1E-12;

	public double coords[] = new double[2];

	public GraTupple2d()
	{
		coords[0] = 0f;
		coords[1] = 0f;
	}

	public GraTupple2d(Point2D.Double point)
	{
		coords[0] = point.x;
		coords[1] = point.y;

	}
	//Constructeur se basant sur la classe PyPoint2D
	GraTupple2d(PyPoint2D point)
	{
		coords[0]=point.getX();
		coords[1]=point.getY();
	}
	
	public GraTupple2d(GraTupple2d t)
	{
		coords[0] = t.coords[0];
		coords[1] = t.coords[1];
	}

	public GraTupple2d(double x, double y)
	{
		coords[0] = x;
		coords[1] = y;
	}

	public static GraTupple2d buildVector(GraTupple2d begin, GraTupple2d end)
	{
		return new GraTupple2d(end.coords[0] - begin.coords[0], end.coords[1] - begin.coords[1]);
	}

	public static GraTupple2d buildVector(Point2D.Double begin, Point2D.Double end) {
		return new GraTupple2d(end.x - begin.x, end.y - begin.y);
	}
	public static GraTupple2d buildVector(PyPoint2D point1, PyPoint2D point2) {
		double resultat1 = point2.getX() - point1.getX();
		double resultat2=point2.getY() - point1.getY();
		return new GraTupple2d(point2.getX() - point1.getX(), point2.getY() - point1.getY());
	}
	//vecteur normalisé
	public static GraTupple2d buildVectorNormalized(Point2D.Double begin, Point2D.Double end) {
		GraTupple2d resultat = new GraTupple2d(end.x - begin.x, end.y - begin.y);
		resultat.normalize();
		return resultat;
	}

	public static GraTupple2d buildVectorNormalized(GraTupple2d begin, GraTupple2d end) {
		GraTupple2d resultat = new GraTupple2d(end.coords[0] - begin.coords[0], end.coords[1] - begin.coords[1]);
		resultat.normalize();
		return resultat;
	}
	//vecteur normalisé a partir de la classe PyPoint2D
	public static GraTupple2d buildVectorNormalized(PyPoint2D point1, PyPoint2D point2){
		// TODO Auto-generated method stub
		GraTupple2d resultat = new GraTupple2d(point2.getX() - point1.getX(), point2.getY() - point1.getY());
		resultat.normalize();
		return resultat;
	}
	public static GraTupple2d getMilieu(GraTupple2d begin, GraTupple2d end)
	{
		return new GraTupple2d((begin.x() + end.x())/2, (begin.y() + end.y())/2);
	}



	public Point2D.Double asPoint2D()
	{
		return new Point2D.Double(coords[0], coords[1]);
	}   

	public double x()
	{
		return coords[0];
	}  

	public double x2()
	{
		return coords[0] * coords[0];
	}

	public double y()
	{
		return coords[1];
	}

	public void x(double val)
	{
		coords[0] = val;
	}

	public void y(double val)
	{
		coords[1] = val;
	}

	public void xy(double valX, double valY)
	{
		coords[0] = valX;
		coords[1] = valY;
	}

	public void newCoords(GraTupple2d t)
	{
		coords[0] = t.x();
		coords[1] = t.y();
	}

	public void add(GraTupple2d t)
	{
		coords[0] += t.x();
		coords[1] += t.y();
	}

	public GraTupple2d addReturn(GraTupple2d t)
	{
		coords[0] += t.x();
		coords[1] += t.y();
		return this;
	}

	public void add(double x, double y)
	{
		coords[0] += x;
		coords[1] += y;
	}

	public void sub(GraTupple2d t)
	{
		coords[0] -= t.x();
		coords[1] -= t.y();
	}

	public void sub(double x, double y)
	{
		coords[0] -= x;
		coords[1] -= y;
	}

	public void mul(double c)
	{
		coords[0] *= c;
		coords[1] *= c;
	}

	public void div(double c)
	{
		coords[0] /= c;
		coords[1] /= c;
	}

	public double scal(GraTupple2d v2)
	{
		return coords[0]*v2.x() + coords[1]*v2.y();
	}

	public double determinant(GraTupple2d v2)
	{
		return coords[0]*v2.y() - coords[1]*v2.x();
	}

	public double length()
	{
		return Math.sqrt(lengthSquared());
	}

	public double lengthSquared()
	{
		return coords[0]*coords[0] + coords[1]*coords[1];
	}

	// Angle forme avec v2.
	// EXCEPTION: si un des vecteurs est de longueur nulle.
	// L'angle est compris entre -PI et PI.
	public double getAngleRad(GraTupple2d v2)
	{
		double denom = Math.sqrt(lengthSquared() * v2.lengthSquared());

		if (denom == 0)
			return 0;

		double cos = scal(v2)/denom;
		double sin = determinant(v2)/denom;

		cos = (cos < -1) ? -1 : cos;
		cos = (cos >  1) ?  1 : cos;
		sin = (sin < -1) ? -1 : sin;
		sin = (sin >  1) ?  1 : sin;

		return (Math.asin(sin) < 0) ? -Math.acos(cos) : Math.acos(cos);
	}   

	public GraTupple2d copy()
	{
		return new GraTupple2d(this);
	}

	public GraTupple2d copyEtAdd(GraTupple2d vector)
	{
		return new GraTupple2d(x()+vector.x(), y() + vector.y());
	}

	public GraTupple2d copyEtSub(GraTupple2d vector)
	{
		return new GraTupple2d(x() - vector.x(), y() - vector.y());
	}

	public GraTupple2d copyEtMul(double valeur)
	{
		return new GraTupple2d(x() * valeur, y() * valeur);
	}

	public void normalize()
	{
		double norm = length();
		if (norm < PRECISION_NORME_NORMALE_d)
			return;
		coords[0] /= norm;
		coords[1] /= norm;
	}

	public void normalizeSansThrow()
	{
		double norm = length();
		if (norm < PRECISION_NORME_NORMALE_d)
		{
			DecimalFormat formateurDecimalesUS = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
			formateurDecimalesUS.applyPattern("###0.00000000000");
			String msgErreur = "GraTupple2d::normalizeSansThrow() ERROR 0: Norme = 0 (" + formateurDecimalesUS.format(norm) + ")";
			{
				System.err.println(msgErreur + " x: " + x() + " y: " + y());
			}
			return;
		}
		coords[0] /= norm;
		coords[1] /= norm;
	}



	public GraTupple2d getTupple2d(AffineTransform transfo)
	{
		Point2D.Double p2DThis = new Point2D.Double(x(), y());
		Point2D.Double p2DNew = new Point2D.Double();
		transfo.transform(p2DThis, p2DNew);
		return new GraTupple2d(p2DNew.x, p2DNew.y);
	}

	public void negate()
	{
		coords[0] = -coords[0];
		coords[1] = -coords[1];
	}

	public GraTupple2d copyNegated(){
		return new GraTupple2d(-x(), -y());
	}

	public GraTupple2d copyAndTransformBy(AffineTransform transfo)
	{    	
		Point2D ptSrc = new Point2D.Double(x(), y());
		Point2D ptDst = new Point2D.Double(x(), y());
		transfo.transform(ptSrc, ptDst);
		return new GraTupple2d(ptDst.getX(), ptDst.getY());
	}

	public void transformBy(AffineTransform transfo)
	{    	
		Point2D ptSrc = new Point2D.Double(x(), y());
		Point2D ptDst = new Point2D.Double(x(), y());
		transfo.transform(ptSrc, ptDst);
		coords[0] = ptDst.getX();
		coords[1] = ptDst.getY();
	}

	public double distanceOf(double x, double y) {
		GraTupple2d v = new GraTupple2d(x, y);
		v.sub(this);
		return v.length();
	}

	public double distanceOf(GraTupple2d e) {
		GraTupple2d v = new GraTupple2d(e);
		v.sub(this);
		return v.length();
	}

	public double distanceOf(Point2D.Double e) {
		GraTupple2d v = new GraTupple2d(e.x, e.y);
		v.sub(this);
		return v.length();
	}



	public static double calculeLongueur(double x, double y)
	{
		return Math.sqrt(x*x + y*y);
	}


	/** 638b24
	 * Si v1 et v2 sont alignés, l'angle vaut 0°.
	 * S'il sont opposé, l'angle vaut 180°.
	 * @param v1
	 * @param v2
	 * @return En radians.
	 */
	
	
	public static double calculeAngleRad_0_180(GraTupple2d v1, GraTupple2d v2)
	{
		double denom = Math.sqrt(v1.lengthSquared() * v2.lengthSquared());

		if (denom == 0)
			return 0;

		double cos = v1.scal(v2)/denom;
		double sin = v1.determinant(v2)/denom;

		cos = (cos < -1) ? -1 : cos;
		cos = (cos >  1) ?  1 : cos;
		sin = (sin < -1) ? -1 : sin;
		sin = (sin >  1) ?  1 : sin;

		return (Math.asin(sin) < 0) ? -Math.acos(cos) : Math.acos(cos);
	}

	public static double calculeAngleRad_0_360(GraTupple2d v1, GraTupple2d v2)
	{
		double denom = Math.sqrt(v1.lengthSquared() * v2.lengthSquared());

		if (denom == 0)
			return 0;

		double cos = v1.scal(v2)/denom;
		double sin = v1.determinant(v2)/denom;

		cos = (cos < -1) ? -1 : cos;
		cos = (cos >  1) ?  1 : cos;
		sin = (sin < -1) ? -1 : sin;
		sin = (sin >  1) ?  1 : sin;

		double resultat = Math.acos(cos);
		if (sin < 0)
			resultat = Math.PI*2 - resultat;

		return resultat;
	}

	public static double calculeAngleDeg_0_180(GraTupple2d v1, GraTupple2d v2)
	{
		return Math.toDegrees(calculeAngleRad_0_180(v1, v2));
	}



	// Donne le vecteur perpendiculaire en tournant dans le sens INVERSE des aiguilles d'une montre (counterClockwise).
	//  
	//  | 0  -1 | x |x| = |-y|
	//  | 1   0 |   |y|   | x|
	public GraTupple2d getPerpendiculaire()
	{
		return new GraTupple2d(-y(), x());
	}

	// Teste si this est situé entre point1 et point2
	// IMPORTANT: this, point1 et point2 sont supposés alignés.
	public boolean isEntrePoints(GraTupple2d point1, GraTupple2d point2)
	{
		double d = point1.distanceOf(point2);
		double d1 = distanceOf(point1);
		double d2 = distanceOf(point2);
		return (d1 <= d) && (d2 <= d);
	}

	public void setLocation(Point2D.Double p2D)
	{
		coords[0] = p2D.x;
		coords[1] = p2D.y;
	}

	public void rotateAngleRad(double angleRad)
	{
		transformBy(AffineTransform.getRotateInstance(angleRad));
	}

	public void rotateAngleDeg(double angleDeg)
	{
		transformBy(AffineTransform.getRotateInstance(Math.toRadians(angleDeg)));
	}

	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "[" + hashCode() + "] x=" + coords[0] + " y=" + coords[1]+ " l=" + length();
		
	}
	 
//	public String toString()
//	{
//		return "" + x() + ", " + y();
//	}

}