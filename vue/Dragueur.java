package app_2D.vue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import app_2D.modele.PyScene2D;

public class Dragueur implements MouseMotionListener, MouseListener
{
	private PyDessinPnl fenetre;
	private PyScene2D scene2D = new PyScene2D();
	private Trait trait;

	Dragueur(PyDessinPnl _fenetre)
	{
		fenetre = _fenetre; 
	}

	// MouseMotionListener
	// ===================
	public void mouseDragged(MouseEvent e)
	{
		if (trait!=null)
		{
			//on ajoute les points à la liste
			trait.addPoint(e.getPoint());
			fenetre.repaint();
		}

		//g.dispose();

	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}


	// MouseListener
	// =============
	//prend les coordonnées de la souris 	
	public void mousePressed(MouseEvent e)
	{
		//on crée le trait et on l'ajoute à la liste
		trait= new Trait();
		scene2D.AddTraits(trait);
	}

	public void mouseReleased(MouseEvent e)
	{
		//on arrete le dessin du trait et on vide la liste "trait" qui contient des points
		trait.termineToi();
		trait=null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	public PyScene2D getscene() {
		//renvoie la scene2D
		return scene2D;

	}
}




