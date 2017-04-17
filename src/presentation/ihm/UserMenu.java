package presentation.ihm;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

public class UserMenu extends SubMenu {

    private JTextField nom = new JTextField(20);
    private JTextField prenom = new JTextField(20);
    private JTextField email = new JTextField(20);
    private JTextField editNom = new JTextField(20);
    private JTextField editPrenom = new JTextField(20);
    private JTextField editEmail = new JTextField(20);
    private JComboBox<Users> usersList = new JComboBox<>();
    private JComboBox<Competition> competsRemList = new JComboBox<>();
    private JComboBox<Competition> competsList = new JComboBox<>();
    private JComboBox<Equipe> teamRemList = new JComboBox<>();
    private JComboBox<Equipe> teamList = new JComboBox<>();
    private Users user;
//	private SortedSet<Candidat> users;

    public UserMenu(Inscriptions ins) {
    	super(ins);
    }

    /**
     * Renvoi l'onglet Personnes du panneau principal
     * @return un jPanel
     */
    public JPanel getPanel() {
        panel.add(addUser());
        panel.add(editUser());
        panel.add(selectUser());
        panel.add(addAndRemove());
        panel.add(removeUser());
        this.user = (Users) usersList.getSelectedItem();
        makeCompetsList();
        makeTeamsList();
        return panel;
    }

    private JPanel addUser() {
        JPanel addUser = getSubPanel("Ajouter une personne");
        
        this.addLabelledComponent(addUser,"Nom :", nom);
        this.addLabelledComponent(addUser, "prenom", prenom);
        this.addLabelledComponent(addUser, "Email :", email);
        addUser.add(getButton("Ajouter", addBtnListener(this)));
        addUser.add(Box.createVerticalStrut(50));

        return addUser;
    }

    private ActionListener addBtnListener(UserMenu menu) {
        return (ActionEvent e) -> {
            Users user = inscriptions.createPersonne(
                    menu.nom.getText(),
                    menu.prenom.getText(),
                    menu.email.getText(),
                    0
            );
            menu.usersList.addItem(user);
            menu.user = user;
        };
    }

    private JPanel selectUser() {
        JPanel panel = getSubPanel("Selectionner une personne");
        this.addLabelledComponent(panel, "Sélectionnez un utilisateur :", usersList);
        this.makeUsersList();
        usersList.setPreferredSize(new Dimension(200, 20));
        usersList.addItemListener(selectBoxListener());
        panel.add(Box.createHorizontalStrut(100));
        panel.add(Box.createVerticalStrut(50));
        return panel;
    }

    private void makeUsersList() {
        inscriptions.getPersonnes().forEach(user -> usersList.addItem(user));
    }

    private ItemListener selectBoxListener() {

        return (ItemEvent e) -> {
        	if(e.getStateChange() == ItemEvent.DESELECTED)
        		return;
        	Users selected = (Users) usersList.getSelectedItem();
        	inscriptions.refresh(selected);
            editNom.setText(selected.getNom());
            editPrenom.setText(selected.getPrenom());
            editEmail.setText(selected.getMail());
            user = selected;
           
            makeCompetsList();
            makeTeamsList();
        };
    }
    
    private void makeCompetsList() {
    	competsList.removeAllItems();
    	competsRemList.removeAllItems();
    	inscriptions.getCompetitions().forEach((c) -> {
            for(Competition uc : user.getCompetition()) {
                if(uc.getId() ==  c.getId()) {
                	this.competsRemList.addItem(c);
                	return;
                }
            }
            if(!c.getEnEquipe())
            	this.competsList.addItem(c);
        });
    }
    
    private void makeTeamsList() {
    	teamList.removeAllItems();
    	teamRemList.removeAllItems();
    	inscriptions.getEquipes().forEach((c) -> {
            for(Equipe uc : user.getEquipes()) {
                if(uc.getId() ==  c.getId()) {
                	this.teamRemList.addItem(c);
                	return;
                }
            }
            this.teamList.addItem(c);
        });
    }
    
    private JPanel addAndRemove() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(manageTeams());
        panel.add(manageCompets());
        return panel;
    }

    private JPanel manageTeams() {
        JPanel panel = getSubPanel("Gérer les équipes");
        panel.add(Box.createVerticalStrut(10));
        
        teamList.setPreferredSize(new Dimension(200, 20));
        JPanel teams = new JPanel();
        this.addLabelledComponent(teams, "Ajouter à l'équipe :", teamList);
        teams.add(this.getButton("Ajouter", addTeamListener()));
        
        
        JPanel teamsRem = new JPanel();
        this.addLabelledComponent(teamsRem, "Enlever de l'équipe :", teamRemList);
        teamRemList.setPreferredSize(new Dimension(200, 20));
        teamsRem.add(this.getButton("Enlever", teamRemListener()));
        
//		panel.add(Box.createHorizontalStrut(100));
        panel.add(teams);
        panel.add(teamsRem);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(10));
        return panel;
    }
    
    private ActionListener addTeamListener() {
        return (ActionEvent e) -> {
        	Equipe team = (Equipe) teamList.getSelectedItem();
        	user.addEquipe(team);
        	teamRemList.addItem(team);
        	teamList.removeItem(team);
        };
    }

    private ActionListener addCompetListener() {
    	return (ActionEvent e) -> {
            Competition compet = (Competition) competsList.getSelectedItem();
            user.inscription(compet);
            competsRemList.addItem(compet);
            competsList.removeItem(compet);
    	};
    }
    
    private JPanel manageCompets() {
        JPanel panel = this.getSubPanel("Gérer les compétitions");
        this.competsRemList.setPreferredSize(new Dimension(200, 20));
        
        competsList.setPreferredSize(new Dimension(200, 20));
        JPanel compets = new JPanel();
        this.addLabelledComponent(compets, "Ajouter à la compétition :", competsList);
        compets.add(this.getButton("Ajouter", addCompetListener()));
        panel.add(compets);
        panel.add(Box.createVerticalStrut(20));
        
        JPanel competsRem = new JPanel();
        competsRem.add(new JLabel("Enlever de la compétition :"));
        competsRem.add(competsRemList);
        competsRem.add(this.getButton("Enlever", competRemListener()));
        panel.add(Box.createVerticalStrut(20));
//		panel.add(Box.createHorizontalStrut(100));
        panel.add(competsRem);
        panel.add(Box.createVerticalStrut(30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private ActionListener competRemListener() {
    	return (ActionEvent e) -> {
            Competition compet = (Competition) competsRemList.getSelectedItem();
            user.desinscription(compet);
            competsRemList.removeItem(compet);
            competsList.addItem(compet);
    	};
    }

    private ActionListener teamRemListener() {
    	return (ActionEvent e) -> {
            Equipe team = (Equipe) teamRemList.getSelectedItem();
            user.removeEquipe(team);
            teamRemList.removeItem(team);
            teamList.addItem(team);
    	};
    }
    
    private JPanel editUser() {
        JPanel panel = getSubPanel("Editer utilisateur");
        this.addLabelledComponent(panel, "Nom :", editNom);
        this.addLabelledComponent(panel, "Prenom :", editPrenom);
        this.addLabelledComponent(panel, "Email :", editEmail);
        panel.add(getButton("Editer", editBtnListener(this)));
        panel.add(Box.createVerticalStrut(50));
        return panel;
    }

    private ActionListener editBtnListener(UserMenu menu) {
        return (ActionEvent e) -> {
            Users user = (Users) usersList.getSelectedItem();
            user.setNom(menu.editNom.getText());
            user.setPrenom(menu.editPrenom.getText());
            user.setMail(menu.editEmail.getText());
            menu.inscriptions.edit(user);
        };
    }

    private JPanel removeUser() {
        JPanel panel = getSubPanel("Effacer l'utilisateur");
        panel.add(getButton("Effacer", deleteBtnListener()));
        return panel;
    }

    private ActionListener deleteBtnListener() {
        return (ActionEvent e) -> {
            Users user = (Users) usersList.getSelectedItem();
            inscriptions.remove(user);
            usersList.removeItem(user);
        };
    }
}
