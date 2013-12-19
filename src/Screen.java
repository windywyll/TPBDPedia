import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Screen extends JFrame{
	
	
	// Ma fenetre
		private JPanel pan = new JPanel();
		
			//Conteneur de gauche;
			private JPanel contGauche = new JPanel();
				private JLabel titre = new JLabel("MusiPedia"); // Titre
				private JPanel recherche = new JPanel();  // Recherche simple
				private BufferedImage myPicture; // Logo
				private JPanel menuAvancee=new JPanel(); // Recherche Avancï¿½
					private JPanel rechercheAvancee = new JPanel();
						private JLabel artisteLabel = new JLabel("Artiste");
						private JLabel genreLabel = new JLabel("Genre");
						private JLabel albumLabel = new JLabel("Album");
				private JButton rechercher = new JButton("Rechercher");
				
			//Resultat
		
 

	public Screen(){
		
		this.setTitle("Musique");       //dï¿½fini le titre
		this.setSize(1600, 800);        //la taille       
//----------------------------------------Menu de gauche------------------------------------------------//		
		

		pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));
        
        
        //Conteneur de gauche
        contGauche.setMinimumSize(new Dimension(500, 700));
        contGauche.setPreferredSize(new Dimension(500, 700));
        contGauche.setMaximumSize(new Dimension(500, 700));
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
        
		
	      	//Integration logo
			try {
				myPicture = ImageIO.read(new File("headphone-300.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				contGauche.add(picLabel);
			} catch (IOException e) {e.printStackTrace();}
		
        
	      //Menu de recherche Avancï¿½
			menuAvancee.setMinimumSize(new Dimension(500, 200));
	        menuAvancee.setPreferredSize(new Dimension(500, 200));
	        menuAvancee.setMaximumSize(new Dimension(500, 200));
	        menuAvancee.setLayout(new BoxLayout(menuAvancee,BoxLayout.Y_AXIS));
	        menuAvancee.setAlignmentX(LEFT_ALIGNMENT);
	
			//Formulaire de recherche Avancï¿½e
	        rechercheAvancee.setLayout(new BoxLayout(rechercheAvancee,BoxLayout.Y_AXIS));
	        rechercheAvancee.setBorder( BorderFactory.createTitledBorder("Recherche detaille"));
	        
	        	//Artiste
	        	rechercheAvancee.add(artisteLabel);
	        	rechercheAvancee.add(new JTextField(""));
	       
	        	//Genre
	    		rechercheAvancee.add(genreLabel);
	    		rechercheAvancee.add(new JTextField(""));
		
	    		//Album
				rechercheAvancee.add(albumLabel);
				rechercheAvancee.add(new JTextField(""));
				
			menuAvancee.add(rechercheAvancee);
			menuAvancee.add(rechercher); //Bouton
	    	
		   contGauche.add(menuAvancee);
		   pan.add(contGauche);

		   
 //-------------------------------------------Resultat-------------------------------------------//
		   
		   		
		   ArrayList<String[]> resultat= new ArrayList<String[]>();
		   
		   //Pour l'exemple\\
		   String[] artiste1= new String[]{"Artiste","http://www.mkyong.com/image/mypic.jpg", "blablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef "};
		   String[] artiste2= new String[]{"Artiste","http://www.mkyong.com/image/mypic.jpg", "blablabla  zef zef zef zef zezeezefezfe erzegegrgz rger "};
		   String[] artiste3= new String[]{"Metalica","http://www.justmusic.fr/wp-content/uploads/2013/05/metallica-promo-photo-1200x12001.jpeg", "blablabla"};
		   resultat.add(artiste1);
		   resultat.add(artiste2);
		   resultat.add(artiste3);
		   //Pour l'exemple\\
		   
		   // Mon conteneur général et son scroll
		   JPanel resultatList = new JPanel();
		   JScrollPane _scroll = new JScrollPane();
		   _scroll.getVerticalScrollBar().setUnitIncrement(20); //Vitesse de scroll
		   
		   resultatList.setLayout(new BoxLayout(resultatList, BoxLayout.PAGE_AXIS));
		   //resultatList.setLayout(new GridLayout(resultat.size(), 1)); // Défini le nombre de lignes de résultat
		   

		   
		   //Pour chaque resultat
		   for(int i=0; i<resultat.size(); i++){
			   String[] resultatTemp = resultat.get(i); //Recupère mon tableau de résultat
			   
			   //Une box par contenu
			   JPanel resultatListContent = new JPanel();
			   //resultatListContent.setLayout(new GridLayout(1,2));
			   resultatListContent.setLayout(new BoxLayout(resultatListContent, BoxLayout.LINE_AXIS));
			   resultatListContent.setBorder( BorderFactory.createTitledBorder(resultatTemp[0])); //Titre
			   
			   JPanel resultatListContent2 = new JPanel();
			   

			   //-------Affichage url
			   	Image image = null;
		        try {
		            URL url = new URL(resultatTemp[1]); //  <----- 2 est un exemple. Dependra de l'index ou se situe l'URL
		            image = ImageIO.read(url);
		        } catch (IOException e) {e.printStackTrace();}
		        
		        
		        Image dimg = image.getScaledInstance(350, 400, Image.SCALE_SMOOTH); // Redimensionne l'image
		        JLabel img = new JLabel(new ImageIcon(dimg)); // Image
		        JLabel titre = new JLabel(resultatTemp[2]); //Contenu text
		        
		        resultatListContent.add(img);
		        resultatListContent.add(titre);
		        
		        resultatList.add(resultatListContent);	        
		   }

		   //pan.add(resultatList);
		   _scroll.setViewportView(resultatList);
		   pan.add(_scroll);
   
	    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);; //operation par defaut a la fermeture
	    this.setContentPane(pan);  
	    this.setVisible(true);
	 }
	
	
	
}