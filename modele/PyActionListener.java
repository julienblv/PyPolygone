package app_2D.modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import app_2D.modele.algos.PyAlgo1;
import app_2D.vue.PyFenetrePrincipale;

public class PyActionListener implements ActionListener
{
	private PyScene2D scene2D;

	public PyScene2D getScene2D() {
		return scene2D;
	}


	public void setScene2D(PyScene2D scene2d)
	{
		scene2D = scene2d;
	}


	public void actionPerformed(ActionEvent e)
	{
		Object sourceE = e.getSource(); // on prends la valeur de l'objet dans laquelle va partir des infos
		
		if (sourceE instanceof JButton)
		{
			JButton bouton_click = (JButton) sourceE;
			String libelle_bouton_click = bouton_click.getText();
			
			if(libelle_bouton_click.equals("Algo 1"))
			{
				PyFenetrePrincipale.PyLogger.info("Execution de l'algorithme 1");					
				PyAlgo1 algo1 = new PyAlgo1(scene2D);
				algo1.executeAlgo();
			}
			
			if(libelle_bouton_click.equals("Algo 2"))
			{
				System.out.println("execution de l'algorithme 2");
			}

		}

	}

				
	public void keyPressed(KeyEvent ke)
	{
		
	}

	public void keyReleased(KeyEvent ke)
	{
		
	}

	public void keyTyped(KeyEvent ke)
	{
			
				
	}


}

