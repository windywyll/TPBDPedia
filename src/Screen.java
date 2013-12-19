import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Screen extends JFrame implements ActionListener{
	
	
	// Ma fenetre
		private JPanel pan = new JPanel();
		
			//Conteneur de gauche;
			private JPanel contGauche = new JPanel();
				private JLabel titre = new JLabel("MusiPedia"); // Titre
				private JPanel recherche = new JPanel();  // Recherche simple
				private JTextField txtRecherche = new JTextField(); 
				private BufferedImage myPicture; // Logo
				private JPanel menuAvancee=new JPanel(); // Recherche Avancï¿½
					private JPanel rechercheAvancee = new JPanel();
						private JLabel artisteLabel = new JLabel("Artiste");
						private JLabel genreLabel = new JLabel("Genre");
						private JLabel albumLabel = new JLabel("Album");	
						private JTextField txtArtiste = new JTextField();
						private JTextField txtGenre = new JTextField();
						private JTextField txtAlbum = new JTextField();
				private JButton rechercher = new JButton("Rechercher");
				private JPanel navResults = new JPanel();
					private JButton suivant = new JButton("Suivant");
					private JButton precedent = new JButton("Precedent");	
				private int offset = 0;
				
				// Mon conteneur général et son scroll
				private JPanel resultatList = new JPanel();
				
				
			//Resultat
		
			//private ArrayList<String[]> results = null;
			private String valueAlbum;
			private String valueArtiste;
			private String valueGenre;
			private String plus;
			private String plus2;
			private int styleRecherche;
			private Recherche r = new Recherche();
			
			private JTextArea contenu = new JTextArea(); 
			private ArrayList<String[]> results= new ArrayList<String[]>();
			private JScrollPane _scroll = new JScrollPane();
			Color originalColor ; //Recupère la couleur de fond

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
	        rechercher.addActionListener(this);
        	recherche.add(txtRecherche);
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
	        	rechercheAvancee.add(txtArtiste);
	       
	        	//Genre
	    		rechercheAvancee.add(genreLabel);
	    		rechercheAvancee.add(txtGenre);
		
	    		//Album
				rechercheAvancee.add(albumLabel);
				rechercheAvancee.add(txtAlbum);
				
			menuAvancee.add(rechercheAvancee);
			menuAvancee.add(rechercher); //Bouton
	    	
			//Boutons de navigation
			navResults.setLayout(new BoxLayout(navResults,BoxLayout.X_AXIS));
			navResults.setAlignmentX(contGauche.LEFT_ALIGNMENT);
			
			precedent.setEnabled(false);
			suivant.setEnabled(false);
				//ajout des listener
				precedent.addActionListener(this);
				suivant.addActionListener(this);
				
				navResults.add(Box.createRigidArea(new Dimension(100,0)));	
				navResults.add(precedent);
				navResults.add(Box.createRigidArea(new Dimension(70,0)));
				navResults.add(suivant);
				
				
				
			
		   contGauche.add(menuAvancee);
		   contGauche.add(navResults);
		   pan.add(contGauche);
		   

		   
 //-------------------------------------------Resultat-------------------------------------------//
		   
		   
		   
		   //Pour l'exemple\\
		   String[] artiste1= new String[]{"Artiste","http://www.mkyong.com/image/mypic.jpg", "blablabla zfzefzef zefzefze fzefezfezf zef blablabla zfzefzef zefzefzzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef blablabla zfzefzef zefzefzef ezfzef zefzefzefzefzefzefze fzefezfezf zefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zefblablabla zfzefzef zefzefzef ezfzef zefzef zefzefzefzefze fzefezfezf zef"};
		   String[] artiste2= new String[]{"Artiste","http://www.mkyong.com/image/mypic.jpg", "blablabla  zef zef zef zef zezeezefezfe erzegegrgz rger "};
		   String[] artiste3= new String[]{"Metalica","http://www.justmusic.fr/wp-content/uploads/2013/05/metallica-promo-photo-1200x12001.jpeg", "blablabla"};
		   results.add(artiste1);
		   results.add(artiste2);
		   results.add(artiste3);
		   results.add(artiste3);
		   results.add(artiste3);
		   results.add(artiste3);
		   results.add(artiste3);
		   results.add(artiste3);
		   results.add(artiste3);
		   //Pour l'exemple\\
		   
		   
		   
		   originalColor = pan.getBackground(); //Recupère la couleur de fond
		   _scroll.getVerticalScrollBar().setUnitIncrement(30); //Vitesse de scroll
		   
		   resultatList.setLayout(new BoxLayout(resultatList, BoxLayout.PAGE_AXIS));
		   resultatList.setAlignmentX(LEFT_ALIGNMENT);
		   

		   
		   // Pour chaque resultat
		   this.refresh();
		   /*
		    * for(int i=0; i<results.size(); i++){
			   
			   String[] resultatTemp = results.get(i); //Recupère mon tableau de résultat
			   
			   //Une box par contenu
			   JPanel resultatListContent = new JPanel();
			   resultatListContent.setAlignmentX(LEFT_ALIGNMENT);
			   
			   BoxLayout bl = new BoxLayout(resultatListContent, BoxLayout.LINE_AXIS);
			   resultatListContent.setLayout(bl);
			   resultatListContent.setMinimumSize(new Dimension(1070, 100));
			   resultatListContent.setMaximumSize(new Dimension(1070, 2000));

			   
			   //Titre
			   resultatListContent.setBorder( BorderFactory.createTitledBorder(resultatTemp[0])); 
			   
			   //Affichage url
			   	Image image = null;
		        try {
		            URL url = new URL(resultatTemp[1]); //  <----- 2 est un exemple. Dependra de l'index ou se situe l'URL
		            image = ImageIO.read(url);
		        } catch (IOException e) {e.printStackTrace();}
		        
		        
		        Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionne l'image
		        JLabel img = new JLabel(new ImageIcon(dimg)); // Image
		        img.add(Box.createVerticalStrut(50));
		        
		        //Contenu text
		        JTextArea contenu = new JTextArea(resultatTemp[2]); 
		        contenu.setLineWrap(true);  /** On souhaite un retour à ligne automatique : */ 
		       /* contenu.setWrapStyleWord(true);/** On souhaite que les mots ne soient pas coupés : */ 
		        //contenu.setEditable(false); 
		       // contenu.setMinimumSize(new Dimension(700,200));
		       // contenu.setMaximumSize(new Dimension(900,5000));
		       // Color originalColor = pan.getBackground(); //Recupère la couleur de fond
		        //contenu.setBackground(originalColor); // L'applique au textarea
		        
		        
		        /*resultatListContent.add(img);
		        resultatListContent.add(contenu);
		        resultatList.add(resultatListContent);	       
		       
		   }*/


		   _scroll.setViewportView(resultatList);
		   pan.add(_scroll);
   
	    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);; //operation par defaut a la fermeture
	    this.setContentPane(pan);  
	    this.setVisible(true);
	 }
	
	
	void refresh(){
				
			resultatList.removeAll();
		 for(int i=0; i<results.size(); i++){
			   
			   String[] resultatTemp = results.get(i); //Recupère mon tableau de résultat
			   
			   //Une box par contenu
			   JPanel resultatListContent = new JPanel();
			   resultatListContent.setAlignmentX(LEFT_ALIGNMENT);
			   
			   BoxLayout bl = new BoxLayout(resultatListContent, BoxLayout.LINE_AXIS);
			   resultatListContent.setLayout(bl);
			   resultatListContent.setMinimumSize(new Dimension(1070, 100));
			   resultatListContent.setMaximumSize(new Dimension(1070, 2000));

			   
			   //Titre
			   resultatListContent.setBorder( BorderFactory.createTitledBorder(resultatTemp[0])); 
			   
			   //Affichage url
			   	Image image = null;
			   	if(resultatTemp[1]!=null){
			        try {
			            URL url = new URL(resultatTemp[1]); //  <----- 2 est un exemple. Dependra de l'index ou se situe l'URL
			            image = ImageIO.read(url);
			        } catch (IOException e) {e.printStackTrace();}
			        
			        if(image != null){
			        	Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionne l'image
				        JLabel img = new JLabel(new ImageIcon(dimg)); // Image
				        img.add(Box.createVerticalStrut(50));
			        }
			   	}
		        
		        
		        
		        //Contenu text
		        contenu = new JTextArea(resultatTemp[2]); 
		        contenu.setLineWrap(true);  /** On souhaite un retour à ligne automatique : */ 
		        contenu.setWrapStyleWord(true);/** On souhaite que les mots ne soient pas coupés : */ 
		        contenu.setEditable(false); 
		        contenu.setMinimumSize(new Dimension(700,200));
		        contenu.setMaximumSize(new Dimension(900,5000));
		        contenu.setBackground(originalColor); // L'applique au textarea
		        
		        
		        //resultatListContent.add(img);
		        resultatListContent.add(contenu);
		        resultatList.add(resultatListContent);	       
		       
		   }
	}
	
	void navChangeResults()
	{
		switch(styleRecherche)
		{
			//recherche globlale
			case 0: results = r.rechercheGlobale(this.txtRecherche.getText());
				break;
			//recherche album
			case 1: results = r.rechercheAlbum(valueAlbum, plus, plus2, offset);
				break;
			//recherche artiste
			case 2: results = r.rechercheArtiste(valueArtiste, plus, offset);
				break;
			//recherche genre
			case 3: results = r.rechercheGenre(valueGenre, offset);
				break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.results= new ArrayList<String[]>();
		
		r=new Recherche();
		if(e.getSource().equals(this.rechercher))
		{	
			//ArrayList<String[]> results = null;
			valueAlbum = this.txtAlbum.getText();
			valueArtiste = this.txtArtiste.getText();
			valueGenre = this.txtGenre.getText();
			plus = null;
			plus2 = null;
			
			if(!valueAlbum.equals("") || !valueArtiste.equals("") || !valueGenre.equals(""))
			{
				if(!valueAlbum.equals(""))
				{
					if(valueArtiste.equals(""))
					{
						plus = valueArtiste;
					}
					
					if(valueGenre.equals(""))
					{
						plus2 = valueGenre;
					}
					styleRecherche = 1;
					results = r.rechercheAlbum(valueAlbum, plus, plus2, offset);
				}
				else if(!valueArtiste.equals(""))
				{
					if(valueGenre.equals(""))
					{
						plus = valueGenre;
					}
					
					styleRecherche = 2;
					results = r.rechercheArtiste(valueArtiste, plus, offset);
				}
				else
				{
					styleRecherche = 3;
					results = r.rechercheGenre(valueGenre, offset);
				}
					
			}
			else if(!this.txtRecherche.getText().isEmpty())
			{
				if(valueAlbum.equals("") && valueArtiste.equals("") && valueGenre.equals(""))
				{
					styleRecherche = 0;
					System.out.println(this.txtRecherche.getText());
					results = r.rechercheGlobale(this.txtRecherche.getText());
				}
				for(int i = 0; i< results.size();i++)
				{
					System.out.println(results.get(i));
				}
			}
			if(results!=null )
			{
				if( results.size()<=50)
				{//disable des boutons precedent et suivant
					precedent.setEnabled(false);
					suivant.setEnabled(false);
				}			
				else
				{
					precedent.setEnabled(true);
					suivant.setEnabled(true);
				}
				
			}
			
		}
		if(e.getSource().equals(this.precedent))
		{
			if(offset > 0)
			{
				offset -= 50;
				this.navChangeResults();
			}
			if(offset == 0)
			{
				//disable du boutons precedent
				precedent.setEnabled(false);
			}
		}
		if(e.getSource().equals(this.suivant))
		{
				offset += 50;
				precedent.setEnabled(true);
				this.navChangeResults();
		}
		
		refresh();
		pan.updateUI();
		
	}
	
	
}