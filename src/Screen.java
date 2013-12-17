import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Screen extends JFrame{
	
	
	// Ma fenetre
		private JPanel pan = new JPanel();
		
			//Conteneur de gauche;
			private JPanel contGauche = new JPanel();
				private JLabel titre = new JLabel("MusiPédia"); // Titre
				private JPanel recherche = new JPanel();  // Recherche simple
				private BufferedImage myPicture; // Logo
				private JPanel menuAvancé=new JPanel(); // Recherche Avancé
					private JPanel rechercheAvancee = new JPanel();
						private JLabel artisteLabel = new JLabel("Artiste");
						private JLabel genreLabel = new JLabel("Artiste");
						private JLabel albumLabel = new JLabel("Artiste");
				private JButton rechercher = new JButton("Rechercher");
		
 

	public Screen(){
		
		this.setTitle("Musique");       //défini le titre
		this.setSize(1600, 800);        //la taille       
//--------------------------------------------------------------------------------------------------//		
		

        pan.setLayout(new BoxLayout(pan, BoxLayout.LINE_AXIS));
        
        
        //Conteneur de gauche
        contGauche.setMinimumSize(new Dimension(500, 600));
        contGauche.setPreferredSize(new Dimension(500, 600));
        contGauche.setMaximumSize(new Dimension(500, 600));
        contGauche.setLayout(new BoxLayout(contGauche,BoxLayout.Y_AXIS));
        contGauche.setAlignmentX(LEFT_ALIGNMENT);
        contGauche.setAlignmentY(BOTTOM_ALIGNMENT);
        
        //Titre
        titre.setFont(new Font("Arial",Font.BOLD,20));
        contGauche.add(titre);
        
        
	      //Formulaire de recherche simple
	        recherche.setMinimumSize(new Dimension(500, 80));
	        recherche.setPreferredSize(new Dimension(500, 80));
	        recherche.setMaximumSize(new Dimension(500, 80));
	        recherche.setLayout(new BoxLayout(recherche,BoxLayout.Y_AXIS));
	        recherche.setBorder( BorderFactory.createTitledBorder("Recherche libre"));
	        recherche.setAlignmentX(LEFT_ALIGNMENT); //Algin-left
	        
        	//Free search
        	recherche.add(new JTextField(""));
        	recherche.add(rechercher); //Bouton
        	
		contGauche.add(recherche);
        
		
	      	//Intégration logo
			try {
				myPicture = ImageIO.read(new File("headphone-300.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		contGauche.add(picLabel);
			} catch (IOException e) {e.printStackTrace();}
		
        
	      //Menu de recherche Avancé
	        menuAvancé.setMinimumSize(new Dimension(500, 200));
	        menuAvancé.setPreferredSize(new Dimension(500, 200));
	        menuAvancé.setMaximumSize(new Dimension(500, 200));
	        menuAvancé.setLayout(new BoxLayout(menuAvancé,BoxLayout.Y_AXIS));
	        menuAvancé.setAlignmentX(LEFT_ALIGNMENT);
	
			//Formulaire de recherche Avancée
	        rechercheAvancee.setLayout(new BoxLayout(rechercheAvancee,BoxLayout.Y_AXIS));
	        rechercheAvancee.setBorder( BorderFactory.createTitledBorder("Recherche détaillé"));
	        
	        	//Artiste
	        	rechercheAvancee.add(artisteLabel);
	        	rechercheAvancee.add(new JTextField(""));
	       
	        	//Genre
	    		rechercheAvancee.add(genreLabel);
	    		rechercheAvancee.add(new JTextField(""));
		
	    		//Album
				rechercheAvancee.add(albumLabel);
				rechercheAvancee.add(new JTextField(""));
				
			menuAvancé.add(rechercheAvancee);
			menuAvancé.add(rechercher); //Bouton
	    	
		   contGauche.add(menuAvancé);
		   pan.add(contGauche);
	
	       		
		 //Resultat	
			//JTextField res1 = new JTextField("");
			//pan.add(res1);
	
		   	
	   
//--------------------------------------------------------------------------------------------------//		   
	    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);; //opération par défaut a la fermeture
	    this.setContentPane(pan);  
	    this.setVisible(true);
	 }
}