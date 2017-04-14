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
    private JComboBox<Equipe> teamsRemList = new JComboBox<>();
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
        usersList.addItemListener(selectBoxListener(usersList, this));
        panel.add(Box.createHorizontalStrut(100));
        panel.add(Box.createVerticalStrut(50));
        return panel;
    }

    private void makeUsersList() {
        inscriptions.getPersonnes().forEach(user -> usersList.addItem(user));
    }

    private ItemListener selectBoxListener(JComboBox<Users> box, UserMenu menu) {

        return (ItemEvent e) -> {

        	Users selected = (Users) box.getSelectedItem();
            menu.editNom.setText(selected.getNom());
            menu.editPrenom.setText(selected.getPrenom());
            menu.editEmail.setText(selected.getMail());
            menu.user = selected;
            selected.getCompetition().forEach(compet -> competsRemList.addItem(compet));
            menu.makeCompetsList();
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
            this.competsList.addItem(c);
        });
    }
    
    private JPanel addAndRemove() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(addTo());
        panel.add(removeFrom());
        return panel;
    }

    private JPanel addTo() {
        JPanel panel = getSubPanel("Ajouter à");
        panel.add(Box.createVerticalStrut(10));
        JComboBox boxTeams = new JComboBox();
        boxTeams.addItem("toto");
        boxTeams.addItem("riri");
        boxTeams.addItem("fifi");
        boxTeams.addItem("loulou");
        boxTeams.addItem("yolo");
        boxTeams.setPreferredSize(new Dimension(200, 20));
        boxTeams.addActionListener(addTeamAL(boxTeams));
        JPanel teams = new JPanel();
        this.addLabelledComponent(teams, "Ajouter à l'équipe :", boxTeams);
        competsList.setPreferredSize(new Dimension(200, 20));
        JPanel compets = new JPanel();
        this.addLabelledComponent(compets, "Ajouter à la compétition", competsList);
        compets.add(this.getButton("Ajouter", addCompetListener(this)));
        panel.add(teams);
        panel.add(Box.createVerticalStrut(20));
//		panel.add(Box.createHorizontalStrut(100));
        panel.add(compets);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(10));
        return panel;
    }
    
    private ActionListener addTeamAL(JComboBox box) {
        return (ActionEvent e) -> {
            System.out.println(box.getSelectedItem());
        };
    }

    private ActionListener addCompetListener(UserMenu menu) {
    	return (ActionEvent e) -> {
            Competition compet = (Competition) menu.competsList.getSelectedItem();
            user.inscription(compet);
            menu.competsRemList.addItem(compet);
            menu.competsList.removeItem(compet);
    	};
    }
    
    private JPanel removeFrom() {
        JPanel panel = new JPanel();
        this.competsRemList.setPreferredSize(new Dimension(200, 20));
        JPanel teams = new JPanel();
        teams.add(new JLabel("Enlever de l'équipe :"));
        JComboBox boxTeams = new JComboBox();
        boxTeams.addItem("toto");
        boxTeams.addItem("riri");
        boxTeams.addItem("fifi");
        boxTeams.addItem("loulou");
        boxTeams.addItem("yolo");
        boxTeams.setPreferredSize(new Dimension(200, 20));
        teams.add(boxTeams);
        panel.setBorder(BorderFactory.createTitledBorder("Enlever de"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(teams);
        JPanel compets = new JPanel();
        compets.add(new JLabel("Enlever de la compétition :"));
        compets.add(competsRemList);
        JButton competsRemBtn = new JButton("Enlever");
        competsRemBtn.addActionListener(competRemListener(this));
        compets.add(competsRemBtn);
        panel.add(teams);
        panel.add(Box.createVerticalStrut(20));
//		panel.add(Box.createHorizontalStrut(100));
        panel.add(compets);
        panel.add(Box.createVerticalStrut(30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private ActionListener competRemListener(UserMenu menu) {
    	return (ActionEvent arg0) -> {
            Competition compet = (Competition) menu.competsRemList.getSelectedItem();
            menu.user.desinscription(compet);
            menu.competsRemList.removeItem(compet);
            menu.competsList.addItem(compet);
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
        panel.add(getButton("Effacer", deleteBtnListener(this)));
        return panel;
    }

    private ActionListener deleteBtnListener(UserMenu menu) {
        return (ActionEvent e) -> {
            Users user1 = (Users) menu.usersList.getSelectedItem();
            inscriptions.remove(user1);
            menu.usersList.removeItem(user1);
        };
    }
}
