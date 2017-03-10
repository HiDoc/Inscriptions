package presentation.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class UserMenu implements SubMenu{
	
	private JPanel panel = new JPanel();
	private JTextField nom = new JTextField(20);
	private JTextField prenom = new JTextField(20);
	private JTextField email = new JTextField(20);
	
	
	public JPanel getPanel() {
		//panel.setPreferredSize(new Dimension(MainIhm.WIDTH, MainIhm.HEIGHT));
		panel.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
		panel.setLayout(new GridLayout(2,1));
		panel.setBorder(BorderFactory.createLineBorder(Color.decode("#EEEEEE"), 5));
		panel.add(this.addUser());
		panel.add(this.selectUser());
		
		return panel;
	}
	
	private JPanel addUser()
	{
		JPanel addUser = new JPanel();
		addUser.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
		addUser.add(new JLabel("<html>Nom :<br/></html>"));
		addUser.add(nom);
		addUser.add(new JLabel("<html>prenom :<br/></html>"));
		addUser.add(prenom);
		addUser.add(new JLabel("email :"));
		addUser.add(email);
		addUser.setBorder(BorderFactory.createTitledBorder("Ajouter une personne"));
		addUser.add(new JButton("yolo"));
		return addUser;
	}
	
	private JPanel selectUser()
	{
		JPanel panel = new JPanel();
		JComboBox box = new JComboBox();
		box.addItem("toto");
		box.addItem("riri");
		box.addItem("fifi");
		box.addItem("loulou");
		box.addItem("yolo");
		box.setPreferredSize(new Dimension(200,20));
		JComboBox boxTeams = new JComboBox();
		boxTeams.addItem("toto");
		boxTeams.addItem("riri");
		boxTeams.addItem("fifi");
		boxTeams.addItem("loulou");
		boxTeams.addItem("yolo");
		JComboBox boxCompets = new JComboBox();
		boxCompets.addItem("toto");
		boxCompets.addItem("riri");
		boxCompets.addItem("fifi");
		boxCompets.addItem("loulou");
		boxCompets.addItem("yolo");
		panel.setBorder(BorderFactory.createTitledBorder("Selectionner une personne"));
		panel.add(box);
		panel.add(boxTeams);
		panel.add(boxCompets);
		return panel;
	}
}
