import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Fenetre extends JFrame implements ActionListener{
	
	JPanel recherchePanel;
	JLabel recherche;
	JTextField txtRecherche;
	int width, height;
	JButton btnRecherche;

	public Fenetre()
	{
		width = 500;
		height = 500;
		this.setLayout(null);
		
		recherchePanel = new JPanel();
		recherche = new JLabel("Rechercher");
		txtRecherche = new JTextField();
		
		
		btnRecherche = new JButton("Rechercher");
		
		btnRecherche.addActionListener(this);
		
		//recherchePanel.setBounds(0,0,width,height/5);
		recherche.setBounds(60,20,100,20);		
		txtRecherche.setBounds(140,20,120,20);
		btnRecherche.setBounds(280,20,120,20);
		
		/*recherchePanel.add(recherche);
		recherchePanel.add(txtRecherche);
		recherchePanel.add(btnRecherche);
		*/
		
		//this.add(recherche);
		this.add(txtRecherche);
		this.add(btnRecherche);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(recherchePanel);
		this.setBounds(500,200,width,height);
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Fenetre f = new Fenetre();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
