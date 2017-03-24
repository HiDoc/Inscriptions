package presentation.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import application.inscriptions.Candidat;
import application.inscriptions.Inscriptions;


public class UserMenu extends SubMenu{
	

	private JTextField nom = new JTextField(20);
	private JTextField prenom = new JTextField(20);
	private JTextField email = new JTextField(20);
	private Inscriptions inscriptions;
//	private SortedSet<Candidat> users;
	
	
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		//users = inscriptions.getCandidats();
		//panel.setPreferredSize(new Dimension(MainIhm.WIDTH, MainIhm.HEIGHT));
		panel.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
		//panel.setLayout(new GridLayout(5,1));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createLineBorder(Color.decode("#EEEEEE"), 5));
		panel.add(addUser());
		panel.add(editUser());
		panel.add(selectUser());
		panel.add(addAndRemove());
		panel.add(new JButton("Effacer"));
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
		addUser.add(new JButton("Ajouter"));
		addUser.add(Box.createVerticalStrut(50));
		return addUser;
	}
	
	private JPanel selectUser()
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel("Selectionner un utilisateur : "));
		panel.setBorder(BorderFactory.createTitledBorder("Selectionner une personne"));
		JComboBox box = new JComboBox();
		box.addItem("toto");
		box.addItem("riri");
		box.addItem("fifi");
		box.addItem("loulou");
		box.addItem("yolo");
		box.setPreferredSize(new Dimension(200,20));
		panel.add(Box.createHorizontalStrut(100));
		panel.add(box);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}

	private JPanel addAndRemove()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(addTo());
		panel.add(removeFrom());
		return panel;
	}
	
	private JPanel addTo()
	{
		JPanel panel = new JPanel();
		panel.add(Box.createVerticalStrut(10));
		//panel.setLayout(new GridLayout(2,1));
		JComboBox boxTeams = new JComboBox();
		boxTeams.addItem("toto");
		boxTeams.addItem("riri");
		boxTeams.addItem("fifi");
		boxTeams.addItem("loulou");
		boxTeams.addItem("yolo");
		boxTeams.setPreferredSize(new Dimension(200,20));
		boxTeams.addActionListener(addTeamAL(boxTeams));
		JPanel teams = new JPanel();
		teams.add(new JLabel("Ajouter à l'équipe :"));
		teams.add(boxTeams);
		JComboBox boxCompets = new JComboBox();
		boxCompets.addItem("toto");
		boxCompets.addItem("riri");
		boxCompets.addItem("fifi");
		boxCompets.addItem("loulou");
		boxCompets.addItem("yolo");
		boxCompets.setPreferredSize(new Dimension(200,20));
		JPanel compets = new JPanel();
		compets.add(new JLabel("Ajouter à la compétition :"));
		compets.add(boxCompets);
		panel.setBorder(BorderFactory.createTitledBorder("Ajouter a"));
		panel.add(teams);
		panel.add(Box.createVerticalStrut(20));
//		panel.add(Box.createHorizontalStrut(100));
		panel.add(compets);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(10));
		return panel;
	}
	
	private ActionListener addTeamAL(JComboBox box)
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(box.getSelectedItem());
			}
		};
	}
	private JPanel removeFrom()
	{
		JPanel panel = new JPanel();
		JComboBox boxTeams = new JComboBox();
		boxTeams.addItem("toto");
		boxTeams.addItem("riri");
		boxTeams.addItem("fifi");
		boxTeams.addItem("loulou");
		boxTeams.addItem("yolo");
		boxTeams.setPreferredSize(new Dimension(200,20));
		JPanel teams = new JPanel();
		teams.add(new JLabel("Enlever de l'équipe :"));
		teams.add(boxTeams);
		JComboBox boxCompets = new JComboBox();
		boxCompets.addItem("toto");
		boxCompets.addItem("riri");
		boxCompets.addItem("fifi");
		boxCompets.addItem("loulou");
		boxCompets.addItem("yolo");
		boxCompets.setPreferredSize(new Dimension(200,20));
		panel.setBorder(BorderFactory.createTitledBorder("Enlever de"));
		panel.add(Box.createVerticalStrut(10));
		panel.add(teams);
		
		JPanel compets = new JPanel();
		compets.add(new JLabel("Enlever de la compétition :"));
		compets.add(boxCompets);
		panel.add(teams);
		panel.add(Box.createVerticalStrut(20));
//		panel.add(Box.createHorizontalStrut(100));
		panel.add(compets);
		panel.add(Box.createVerticalStrut(30));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}
	
	private JPanel editUser()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Editer placeholder"));
		panel.add(new JLabel("<html>Nom :<br/></html>"));
		panel.add(new JTextField(20));
		panel.add(new JLabel("<html>prenom :<br/></html>"));
		panel.add(new JTextField(20));
		panel.add(new JLabel("email :"));
		panel.add(new JTextField(20));
		panel.add(new JButton("Editer"));
		panel.add(Box.createVerticalStrut(50));
		return panel;
		
	}
}
