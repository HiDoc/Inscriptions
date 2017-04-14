package presentation.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.inscriptions.Equipe;
import application.inscriptions.Inscriptions;
import application.inscriptions.Users;

public class TeamMenu extends SubMenu{
	
	private JTextField nom = new JTextField(20);
	private JPanel panel = new JPanel();
	private Equipe team;
	private JComboBox<Equipe> teamList;
	private Inscriptions inscriptions;
	
	public TeamMenu(Inscriptions ins)
	{
		this.inscriptions = ins;
	}
	
	public JPanel getPanel() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createLineBorder(Color.decode("#EEEEEE"), 5));
		panel.add(addTeam());
		panel.add(editTeam());
		panel.add(selectTeam());
		panel.add(addAndRemove());
		panel.add(new JButton("Effacer"));
		return panel;
	}
	
	private JPanel addTeam()
	{
		JPanel addUser = new JPanel();
		JButton addBtn = new JButton("Ajouter");
		addUser.add(new JLabel("Nom :"));
		addUser.add(nom);
		addUser.setBorder(BorderFactory.createTitledBorder("Ajouter une équipe"));
		addBtn.addActionListener(addBtnListener(this));
		addUser.add(addBtn);
		addUser.add(Box.createVerticalStrut(50));
		return addUser;
	}
	
	private ActionListener addBtnListener(TeamMenu menu)
	{
        return (ActionEvent e) -> {
            Equipe team = inscriptions.createEquipe(
                    menu.nom.getText()
            );
            menu.teamList.addItem(team);
            menu.team = team;
        };
	}
	
	private JPanel selectTeam()
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel("Selectionner une équipe : "));
		panel.setBorder(BorderFactory.createTitledBorder("Selectionner une équipe"));
		makeTeamList();
		teamList.setPreferredSize(new Dimension(200,20));
		panel.add(Box.createHorizontalStrut(100));
		panel.add(teamList);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}

	private void makeTeamList()
	{
		teamList = new JComboBox<>();
		inscriptions.getEquipes().forEach(team-> teamList.addItem(team));
	}
	
	private JPanel addAndRemove()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(addRemoveUsers());
		panel.add(addRemoveCompets());
		return panel;
	}
	
	private JPanel addRemoveUsers()
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
		teams.add(new JLabel("Ajouter un membre :"));
		teams.add(boxTeams);
		JComboBox boxCompets = new JComboBox();
		boxCompets.addItem("toto");
		boxCompets.addItem("riri");
		boxCompets.addItem("fifi");
		boxCompets.addItem("loulou");
		boxCompets.addItem("yolo");
		boxCompets.setPreferredSize(new Dimension(200,20));
		JPanel compets = new JPanel();
		compets.add(new JLabel("Enlever un membre :"));
		compets.add(boxCompets);
		panel.setBorder(BorderFactory.createTitledBorder("Gerer les membres"));
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
	private JPanel addRemoveCompets()
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
		teams.add(new JLabel("Ajouter a la competition :"));
		teams.add(boxTeams);
		JComboBox boxCompets = new JComboBox();
		boxCompets.addItem("toto");
		boxCompets.addItem("riri");
		boxCompets.addItem("fifi");
		boxCompets.addItem("loulou");
		boxCompets.addItem("yolo");
		boxCompets.setPreferredSize(new Dimension(200,20));
		panel.setBorder(BorderFactory.createTitledBorder("Gérer les compétitions :"));
		panel.add(Box.createVerticalStrut(10));
		panel.add(teams);
		
		JPanel compets = new JPanel();
		compets.add(new JLabel("Enlever de la compétition: "));
		compets.add(boxCompets);
		panel.add(teams);
		panel.add(Box.createVerticalStrut(20));
//		panel.add(Box.createHorizontalStrut(100));
		panel.add(compets);
		panel.add(Box.createVerticalStrut(30));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}
	
	private JPanel editTeam()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Editer placeholder"));
		panel.add(new JLabel("Nom :"));
		panel.add(new JTextField(20));
		panel.add(Box.createVerticalStrut(50));
		return panel;
		
	}
}
