package app_2D.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app_2D.modele.Log4j;
import app_2D.modele.PyActionListener;
import app_2D.modele.PyParseur;

public class PyFenetrePrincipale
{
	public static final Logger PyLogger = LogManager.getLogger(Log4j.class);
	
	static PyDessinPnl rightPnl = null;
	
	/////////////////////////////////////////// Action Listener /////////////////////////////////////
	  static PyActionListener pyActionListener = new PyActionListener();
   /////////////////////////////////////////////////////////////////////////////////////////////////
	
	static void addPanel1(JPanel mainPanel)
	{
		JPanel pan1=new JPanel();
		pan1.setOpaque(true);
		pan1.setBackground(Color.CYAN);
		mainPanel.add(pan1);
		
		
		
		
		//checkbox
		JCheckBox checkbox1 = new JCheckBox("checkbox1");
		JCheckBox checkbox2 = new JCheckBox("checkbox2");
		JCheckBox checkbox3 = new JCheckBox("checkbox3");

		pan1.add(checkbox1);
		pan1.add(checkbox2);
		pan1.add(checkbox3);
		
		//evenement checkbox1
		checkbox1.addItemListener(new ItemListener()
		{
	      public void itemStateChanged(ItemEvent e)
	      {	        
	        if (e.getStateChange() == ItemEvent.SELECTED)
	        {
	        	pan1.setBackground(Color.white);
	        	rightPnl.setBackground(Color.white);
	        	//rightPnl.repaint();
	        	}
	        else if (e.getStateChange() == ItemEvent.DESELECTED)
	        {
				 pan1.setBackground(Color.ORANGE); 
				 rightPnl.setBackground(Color.ORANGE);
				// rightPnl.repaint();
				 }
				}
	    });
		
		//evenement checkbox2
		checkbox2.addItemListener(new ItemListener()
		{
	      public void itemStateChanged(ItemEvent e)
	      {	        
	        if (e.getStateChange() == ItemEvent.SELECTED)
	        {
	        	rightPnl.couleur_rec = Color.gray;
	        	rightPnl.repaint();
	        	
	        	}
	        else if (e.getStateChange() == ItemEvent.DESELECTED)
	        { 
				 rightPnl.couleur_rec = Color.BLACK;
				 rightPnl.repaint();
				 }
				}
	    });
	}

	static void addPanel2(JPanel mainPanel)
	{
		JPanel pan2=new JPanel();
		pan2.setOpaque(true);
		pan2.setBackground(Color.YELLOW);
		mainPanel.add(pan2);
		
		JButton button = new JButton("Effacer");
		button.setPreferredSize(new Dimension(100, 50));
		pan2.add(button, BorderLayout.CENTER);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				rightPnl.dragueur.getscene().clearScene();
		        rightPnl.repaint();
			}
		});
		//bouton radio
		JRadioButton bleu = new JRadioButton("Bleu");
		JRadioButton rouge = new JRadioButton("Rouge");
		JRadioButton vert = new JRadioButton("Vert"); 

		bleu.setBounds(40,60,200,50);
		rouge.setBounds(40,60,200,50);
		vert.setBounds(40,140,200,50); 

		ButtonGroup bg = new ButtonGroup();  	    
		bg.add(bleu);
		bg.add(rouge);
		bg.add(vert);

		pan2.add(bleu);
		pan2.add(rouge);
		pan2.add(vert);
	}


	static void addPanel3(JPanel mainPanel)
	{	
		JPanel pan3 = new JPanel();
		pan3.setOpaque(true);
		pan3.setBackground(Color.PINK);
		mainPanel.add(pan3);

		//textfield
		JTextField textField = new JTextField("Bonjour");
		textField.setColumns(10); 
		textField.setBounds(50,100, 200, 30);

///////////////////////////////////////////////////// CREATION DES 3 BOUTTONS //////////////////////////////////////////////////////

		JButton Algo_1 = new JButton("Algo 1");
		pan3.add(Algo_1);
		Algo_1.addActionListener(pyActionListener);
		
		JButton Algo_2 = new JButton("Algo 2");
		pan3.add(Algo_2);
		Algo_2.addActionListener(pyActionListener);
		
		JSlider Distance = new JSlider(JSlider.HORIZONTAL,
                0, 20, 5);;
		pan3.add(Distance);
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		pan3.add(textField);
	}
	
	public static void main(String argv[]) throws NumberFormatException, IOException
	{		
		//creation de la fenetre
		JFrame frameDeTest01 = new JFrame();
		
		//fenetre pour choisir le fichier à ouvrir
		JFrame fenetre = new JFrame();

		// nom et taille de la fenetre
		frameDeTest01.setTitle("Application Swing");
		frameDeTest01.setSize(1000,500);
		frameDeTest01.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameDeTest01.addWindowListener(new WindowListener()
			{
				public void windowClosed(WindowEvent arg0)
				{}
	
				@Override
				public void windowClosing(WindowEvent arg0)
				{
					PyFenetrePrincipale.PyLogger.info("Fermeture du programme.");
					PyFenetrePrincipale.PyLogger.traceExit();
					System.exit(0);
				}
	
				public void windowActivated(WindowEvent arg0) {}
				public void windowDeactivated(WindowEvent arg0) {}
				public void windowDeiconified(WindowEvent arg0) {}
				public void windowIconified(WindowEvent arg0) {}
				public void windowOpened(WindowEvent arg0) {}
			});
		

		// Construction du menubar
		JMenuBar menu=new JMenuBar();

		JMenu menuFichier=new JMenu("Fichier");
		JMenuItem Ouvrir = new JMenuItem("Ouvrir");
		menu.add(menuFichier);		
		menuFichier.add(Ouvrir);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier poly", "poly");
		
        JFileChooser fichier = new JFileChooser();
        fichier.addChoosableFileFilter(filter);
        //écouteur d'événement du menu ouvrir
        
        Ouvrir.addActionListener(new ActionListener() {
        	
        	
            public void actionPerformed(ActionEvent arg0) {
            	
            	//ouvre une boîte de dialogue d'ouverture de fichier.
                 int val_retour = fichier.showOpenDialog(fenetre);
                if (val_retour == JFileChooser.APPROVE_OPTION) { 
                	
                	File file = fichier.getSelectedFile();
                	//afficher le nom du fichier
                	System.out.println("Nom du fichier : "+file.getName()+"\n");
                	//afficher le chemin absolu du fichier
                	System.out.println("Chemin absolu : "+file.getAbsolutePath()+"\n");

                    try
                    {
                    	PyParseur lecteur = new PyParseur(file.getAbsolutePath());
                    	rightPnl.dragueur.getscene().addPolygone(lecteur.getPolygone());
                    	rightPnl.repaint();
                    	
					} catch (NumberFormatException | IOException e) {
						
						e.printStackTrace();
					}
                } else {
                	System.out.println("L'ouverture est annulée\n");
                }
            }
        });
        
        // Pour effacer tout:
        // rightPnl.getScene2D().clearScene()
        // rightPnl.repaint();
        
        
		frameDeTest01.setJMenuBar(menu);

		// Les panels
		//Container container = frameDeTest01.getContentPane();

		Container splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		frameDeTest01.getContentPane().add(splitPane);
		
		// Panel gauche.
		// =============
		JPanel leftPnl = new JPanel();
		leftPnl.setLayout(new GridLayout(3, 1));
		splitPane.add(leftPnl);

		// Panel 1
		// -------
		addPanel1(leftPnl);

		// Panel 2
		// -------
		addPanel2(leftPnl);

		// Panel 3
		// -------
		addPanel3(leftPnl);
		
		// Panel droit.
		// ============
		rightPnl = new PyDessinPnl();
		rightPnl.setBackground(Color.PINK);
		
		splitPane.add(rightPnl);
		
		pyActionListener.setScene2D(rightPnl.dragueur.getscene());
		
		//------------------
		//fichier temporaire
		File polygonFile = new File("2-polygones-had.poly");
		if (polygonFile.exists())
		{
			PyParseur lecteur = new PyParseur(polygonFile.getAbsolutePath());
			rightPnl.dragueur.getscene().addPolygone(lecteur.getPolygone());
			
			frameDeTest01.setVisible(true);
		}
	}
}

