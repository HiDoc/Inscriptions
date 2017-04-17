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

import application.inscriptions.Competition;
import application.inscriptions.Equipe;
import application.inscriptions.Inscriptions;
import application.inscriptions.Users;
import data.hibernate.passerelle;

public class TeamMenu extends SubMenu{
	
	private JTextField nom = new JTextField(20);
	private JTextField editNom = new JTextField(20);
	private Equipe team;
	private JComboBox<Equipe> teamList;
	private JComboBox<Users> userList = new JComboBox<>();
	private JComboBox<Users> userRemList = new JComboBox<>();
	private JComboBox<Competition> competList = new JComboBox<>();
	private JComboBox<Competition> competRemList = new JComboBox<>();
	
	public TeamMenu(Inscriptions ins)
	{
		super(ins);
	}
	
	public JPanel getPanel() {
		panel.add(addTeam());
		panel.add(editTeam());
		panel.add(selectTeam());
		panel.add(addAndRemove());
		panel.add(removeTeam());
		this.team = (Equipe) teamList.getSelectedItem();
		this.makeUsersList();
		this.makeCompetsList();
		return panel;
	}
	
	private JPanel addTeam()
	{
		JPanel addTeam = getSubPanel("Ajoute une équipe");
		this.addLabelledComponent(addTeam, "Nom :", nom);
		addTeam.add(this.getButton("Ajouter", addBtnListener(this)));
		addTeam.add(Box.createVerticalStrut(50));
		return addTeam;
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
		teamList.addActionListener(selectTeamListener());
		panel.add(Box.createHorizontalStrut(100));
		panel.add(teamList);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}

	private ActionListener selectTeamListener()
	{
		return (ActionEvent e) -> {
			Equipe team = (Equipe) this.teamList.getSelectedItem();
			inscriptions.refresh(team);
			this.team = team;
			System.out.println(team.getUsers());
			makeUsersList();
			makeCompetsList();
		};
	}
	
	private void makeUsersList() {
    	userList.removeAllItems();
    	userRemList.removeAllItems();
    	inscriptions.getPersonnes().forEach((c) -> {
            for(Users uc : team.getUsers()) {
                if(uc.getId() ==  c.getId()) {
                	userRemList.addItem(c);
                	return;
                }
            }
            userList.addItem(c);
        });
    }
	
	private void makeCompetsList() {
    	competList.removeAllItems();
    	competRemList.removeAllItems();
    	inscriptions.getCompetitions().forEach((c) -> {
            for(Competition uc : team.getCompetition()) {
                if(uc.getId() ==  c.getId()) {
                	competRemList.addItem(c);
                	return;
                }
            }
            if(c.getEnEquipe())
            	competList.addItem(c);
        });
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
		JPanel panel = getSubPanel("Gérer les membres");
		JPanel users = new JPanel();
		JPanel usersRem = new JPanel();
		
		panel.add(Box.createVerticalStrut(10));
		userList.setPreferredSize(new Dimension(200,20));
		this.addLabelledComponent(users, "Ajouter un membre :", userList);
		users.add(this.getButton("Ajouter", addUserListener()));
		userRemList.setPreferredSize(new Dimension(200,20));
		usersRem.add(new JLabel("Enlever un membre :"));
		usersRem.add(userRemList);
		usersRem.add(this.getButton("Enlever", remUserListener()));
		panel.add(users);
		panel.add(Box.createVerticalStrut(20));
		panel.add(usersRem);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(10));
		return panel;
	}
	
	private ActionListener addUserListener()
	{
		return (ActionEvent e) -> {
			Users user = (Users) userList.getSelectedItem();
			team.addUser(user);
			userList.removeItem(user);
			userRemList.addItem(user);
		};
	}
	
	private ActionListener remUserListener()
	{
		return (ActionEvent e) -> {
			Users user = (Users) userRemList.getSelectedItem();
			team.removeUser(user);
			userList.addItem(user);
			userRemList.removeItem(user);
		};
	}
	private JPanel addRemoveCompets()
	{
		JPanel panel = getSubPanel("Gérer les compétitions");
		JPanel compets = new JPanel();
		JPanel competsRem = new JPanel();
		
		competList.setPreferredSize(new Dimension(200,20));
		competRemList.setPreferredSize(new Dimension(200,20));
		this.addLabelledComponent(compets, "Ajouter à la compétition", competList);
		compets.add(this.getButton("Ajouter", addCompetListener()));
		panel.add(Box.createVerticalStrut(10));

		this.addLabelledComponent(competsRem, "Enlever de la compétition", competRemList);
		competsRem.add(this.getButton("Enlever", remCompetListener()));
		panel.add(compets);
		panel.add(Box.createVerticalStrut(20));
		panel.add(competsRem);
		panel.add(Box.createVerticalStrut(30));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}
	
	private ActionListener addCompetListener()
	{
		return (ActionEvent e) -> {
			Competition compet = (Competition) competList.getSelectedItem();
			team.inscription(compet);
			competList.removeItem(compet);
			competRemList.addItem(compet);
		};
	}
	
	private ActionListener remCompetListener()
	{
		return (ActionEvent e) -> {
			Competition compet = (Competition) competRemList.getSelectedItem();
			team.desinscription(compet);
			competList.addItem(compet);
			competRemList.removeItem(compet);
		};
	}
	
	private JPanel editTeam()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Editer placeholder"));
		this.addLabelledComponent(panel, "nom", editNom);
		panel.add(Box.createVerticalStrut(50));
		return panel;
		
	}
	
    private JPanel removeTeam() {
        JPanel panel = getSubPanel("Effacer l'equipe");
        panel.add(getButton("Effacer", deleteBtnListener()));
        return panel;
    }

    private ActionListener deleteBtnListener() {
        return (ActionEvent e) -> {
            Equipe team = (Equipe) teamList.getSelectedItem();
            inscriptions.remove(team);
            teamList.removeItem(team);
        };
    }
}
