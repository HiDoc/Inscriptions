package presentation.ihm;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
 * Classe qui permet de contruire le panneau principal des utilisateurs. Il est
 * composé de plusieurs panneaux qui correspondent aux fonctionnalités de
 * contrôle des utilisateurs.
 *
 * @author Georges
 */
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

    /**
     * Contructeur de la classe UserMenu qui initialise un objet Inscription qui
     * permet de gérer les entrées et sorties dans la base de donnée
     *
     * @param ins l'objet d'inscription permettant la persistance
     */
    public UserMenu(Inscriptions ins) {
        super(ins);
    }

    /**
     * Renvoi l'onglet Personnes du panneau principal, composé d'ajout, d'édition, de suppression 
     * d'un utilisateur.
     *
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

        this.addLabelledComponent(addUser, "Nom :", this.nom);
        this.addLabelledComponent(addUser, "Prenom", this.prenom);
        this.addLabelledComponent(addUser, "Email :", this.email);
        addUser.add(getButton("Ajouter", addBtnListener(this)));
        addUser.add(Box.createVerticalStrut(50));

        return addUser;
    }

    private ActionListener addBtnListener(UserMenu menu) {
        return (ActionEvent e) -> {
            Users newUser = inscriptions.createPersonne(
                    menu.nom.getText(),
                    menu.prenom.getText(),
                    menu.email.getText(),
                    0
            );
            menu.usersList.addItem(newUser);
            menu.user = newUser;
        };
    }

    private JPanel selectUser() {
        JPanel userPanel = getSubPanel("Selectionner une personne");
        this.addLabelledComponent(userPanel, "Sélectionnez un utilisateur :", this.usersList);
        this.makeUsersList();
        this.usersList.setPreferredSize(new Dimension(200, 20));
        this.usersList.addItemListener(selectBoxListener());
        userPanel.add(Box.createHorizontalStrut(100));
        userPanel.add(Box.createVerticalStrut(50));
        return userPanel;
    }

    private void makeUsersList() {
        inscriptions.getPersonnes().forEach(users -> this.usersList.addItem(users));
    }

    private ItemListener selectBoxListener() {

        return (ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                return;
            }
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
            for (Competition uc : user.getCompetition()) {
                if (uc.getId() == c.getId()) {
                    this.competsRemList.addItem(c);
                    return;
                }
            }
            if (!c.getEnEquipe()) {
                this.competsList.addItem(c);
            }
        });
    }

    private void makeTeamsList() {
        teamList.removeAllItems();
        teamRemList.removeAllItems();
        inscriptions.getEquipes().forEach((c) -> {
            for (Equipe uc : user.getEquipes()) {
                if (uc.getId() == c.getId()) {
                    this.teamRemList.addItem(c);
                    return;
                }
            }
            this.teamList.addItem(c);
        });
    }

    private JPanel addAndRemove() {
        JPanel refreshPanel = new JPanel();
        refreshPanel.setLayout(new GridLayout(1, 2));
        refreshPanel.add(manageTeams());
        refreshPanel.add(manageCompets());
        return refreshPanel;
    }

    private JPanel manageTeams() {
        JPanel teamPanel = getSubPanel("Gérer les équipes");
        teamPanel.add(Box.createVerticalStrut(10));

        teamList.setPreferredSize(new Dimension(200, 20));
        JPanel teams = new JPanel();
        this.addLabelledComponent(teams, "Ajouter à l'équipe :", teamList);
        teams.add(this.getButton("Ajouter", addTeamListener()));

        JPanel teamsRem = new JPanel();
        this.addLabelledComponent(teamsRem, "Enlever de l'équipe :", teamRemList);
        teamRemList.setPreferredSize(new Dimension(200, 20));
        teamsRem.add(this.getButton("Enlever", teamRemListener()));

        teamPanel.add(teams);
        teamPanel.add(teamsRem);
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        teamPanel.add(Box.createVerticalStrut(10));
        return teamPanel;
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
        JPanel competitionPanel = this.getSubPanel("Gérer les compétitions");
        this.competsRemList.setPreferredSize(new Dimension(200, 20));
        this.competsList.setPreferredSize(new Dimension(200, 20));
        
        JPanel compets = new JPanel();
        this.addLabelledComponent(compets, "Ajouter à la compétition :", this.competsList);
        compets.add(this.getButton("Ajouter", addCompetListener()));
        competitionPanel.add(compets);
        competitionPanel.add(Box.createVerticalStrut(20));

        JPanel competsRem = new JPanel();
        competsRem.add(new JLabel("Enlever de la compétition :"));
        competsRem.add(this.competsRemList);
        competsRem.add(this.getButton("Enlever", competRemListener()));
        competitionPanel.add(Box.createVerticalStrut(20));

        competitionPanel.add(competsRem);
        competitionPanel.add(Box.createVerticalStrut(30));
        competitionPanel.setLayout(new BoxLayout(competitionPanel, BoxLayout.Y_AXIS));
        return competitionPanel;
    }

    private ActionListener competRemListener() {
        return (ActionEvent e) -> {
            Competition compet = (Competition) this.competsRemList.getSelectedItem();
            this.user.desinscription(compet);
            this.competsRemList.removeItem(compet);
            this.competsList.addItem(compet);
        };
    }

    private ActionListener teamRemListener() {
        return (ActionEvent e) -> {
            Equipe team = (Equipe) this.teamRemList.getSelectedItem();
            this.user.removeEquipe(team);
            this.teamRemList.removeItem(team);
            this.teamList.addItem(team);
        };
    }

    private JPanel editUser() {
        JPanel userPanel = getSubPanel("Editer utilisateur");
        this.addLabelledComponent(userPanel, "Nom :", editNom);
        this.addLabelledComponent(userPanel, "Prenom :", editPrenom);
        this.addLabelledComponent(userPanel, "Email :", editEmail);
        userPanel.add(getButton("Editer", editBtnListener(this)));
        userPanel.add(Box.createVerticalStrut(50));
        return userPanel;
    }

    private ActionListener editBtnListener(UserMenu menu) {
        return (ActionEvent e) -> {
            Users editUser = (Users) usersList.getSelectedItem();
            editUser.setNom(menu.editNom.getText());
            editUser.setPrenom(menu.editPrenom.getText());
            editUser.setMail(menu.editEmail.getText());
            menu.inscriptions.edit(editUser);
        };
    }

    private JPanel removeUser() {
        JPanel userPanel = getSubPanel("Effacer l'utilisateur");
        userPanel.add(getButton("Effacer", deleteBtnListener()));
        return userPanel;
    }

    private ActionListener deleteBtnListener() {
        return (ActionEvent e) -> {
            Users removeUser = (Users) usersList.getSelectedItem();
            inscriptions.remove(removeUser);
            usersList.removeItem(removeUser);
        };
    }
}
