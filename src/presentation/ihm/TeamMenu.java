package presentation.ihm;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.inscriptions.Competition;
import application.inscriptions.Equipe;
import application.inscriptions.Inscriptions;
import application.inscriptions.Users;

/**
 * Classe qui permet de contruire le panneau principal des équipes. Il est composé de 
 * plusieurs panneaux qui correspondent aux fonctionnalités de contrôle des équipes.
 * 
 * @author Flo
 */
public class TeamMenu extends SubMenu{
	
	private JTextField nom = new JTextField(20);
	private JTextField editNom = new JTextField(20);
	private Equipe team;
	private JComboBox<Equipe> teamList;
	private JComboBox<Users> userList = new JComboBox<>();
	private JComboBox<Users> userRemList = new JComboBox<>();
	private JComboBox<Competition> competList = new JComboBox<>();
	private JComboBox<Competition> competRemList = new JComboBox<>();
	
    /**
     * Constructeur de la classe TeamMenu, permet d'initialiser un 
     * objet inscription pour permettre la persistance
     * @param ins un objet Inscription
     */
    public TeamMenu(Inscriptions ins)
	{
		super(ins);
	}
	
    /**
     * Construit le panel principal du sous-menu Equipe
     * @return un objet jPanel
     */
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
            Equipe addTeam = inscriptions.createEquipe(
                    menu.nom.getText()
            );
            menu.teamList.addItem(addTeam);
            menu.team = addTeam;
        };
	}
	
	private JPanel selectTeam()
	{
		JPanel selectPanel = new JPanel();
		selectPanel.add(new JLabel("Selectionner une équipe : "));
		selectPanel.setBorder(BorderFactory.createTitledBorder("Selectionner une équipe"));
		makeTeamList();
		teamList.setPreferredSize(new Dimension(200,20));
		teamList.addActionListener(selectTeamListener());
		selectPanel.add(Box.createHorizontalStrut(100));
		selectPanel.add(teamList);
		selectPanel.add(Box.createVerticalStrut(50));
		return selectPanel;
	}

	private ActionListener selectTeamListener()
	{
		return (ActionEvent e) -> {
			Equipe selectTeam = (Equipe) this.teamList.getSelectedItem();
			inscriptions.refresh(selectTeam);
			this.team = selectTeam;
			System.out.println(selectTeam.getUsers());
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
		JPanel refreshPanel = new JPanel();
		refreshPanel.setLayout(new GridLayout(1,2));
		refreshPanel.add(addRemoveUsers());
		refreshPanel.add(addRemoveCompets());
		return refreshPanel;
	}
	
	private JPanel addRemoveUsers()
	{
		JPanel addRemovePanel = getSubPanel("Gérer les membres");
		JPanel users = new JPanel();
		JPanel usersRem = new JPanel();
		
		addRemovePanel.add(Box.createVerticalStrut(10));
		userList.setPreferredSize(new Dimension(200,20));
		this.addLabelledComponent(users, "Ajouter un membre :", userList);
		users.add(this.getButton("Ajouter", addUserListener()));
		userRemList.setPreferredSize(new Dimension(200,20));
		usersRem.add(new JLabel("Enlever un membre :"));
		usersRem.add(userRemList);
		usersRem.add(this.getButton("Enlever", remUserListener()));
		addRemovePanel.add(users);
		addRemovePanel.add(Box.createVerticalStrut(20));
		addRemovePanel.add(usersRem);
		addRemovePanel.setLayout(new BoxLayout(addRemovePanel, BoxLayout.Y_AXIS));
		addRemovePanel.add(Box.createVerticalStrut(10));
		return addRemovePanel;
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
		JPanel addRemovePanel = getSubPanel("Gérer les compétitions");
		JPanel compets = new JPanel();
		JPanel competsRem = new JPanel();
		
		competList.setPreferredSize(new Dimension(200,20));
		competRemList.setPreferredSize(new Dimension(200,20));
		this.addLabelledComponent(compets, "Ajouter à la compétition", competList);
		compets.add(this.getButton("Ajouter", addCompetListener()));
		addRemovePanel.add(Box.createVerticalStrut(10));

		this.addLabelledComponent(competsRem, "Enlever de la compétition", competRemList);
		competsRem.add(this.getButton("Enlever", remCompetListener()));
		addRemovePanel.add(compets);
		addRemovePanel.add(Box.createVerticalStrut(20));
		addRemovePanel.add(competsRem);
		addRemovePanel.add(Box.createVerticalStrut(30));
		addRemovePanel.setLayout(new BoxLayout(addRemovePanel, BoxLayout.Y_AXIS));
		return addRemovePanel;
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
		JPanel editPanel = new JPanel();
		editPanel.setBorder(BorderFactory.createTitledBorder("Editer placeholder"));
		this.addLabelledComponent(editPanel, "nom", editNom);
		editPanel.add(Box.createVerticalStrut(50));
		return editPanel;
		
	}
	
    private JPanel removeTeam() {
        JPanel removeTeamPanel = getSubPanel("Effacer l'equipe");
        removeTeamPanel.add(getButton("Effacer", deleteBtnListener()));
        return removeTeamPanel;
    }

    private ActionListener deleteBtnListener() {
        return (ActionEvent e) -> {
            Equipe deleteTeam = (Equipe) teamList.getSelectedItem();
            inscriptions.remove(deleteTeam);
            teamList.removeItem(deleteTeam);
        };
    }
}
