package presentation.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

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
    private JComboBox<Users> usersList;
    private JComboBox<Competition> competsRemList = new JComboBox<Competition>();
    private JComboBox<Competition> competsList = new JComboBox<Competition>();
    private JComboBox<Equipe> teamsRemList = new JComboBox<Equipe>();
    private Inscriptions inscriptions;
    private Users user;
//	private SortedSet<Candidat> users;

    public UserMenu(Inscriptions ins) {
        this.inscriptions = ins;
    }

    /**
     *
     * @return
     */
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.decode("#EEEEEE"), 5));
        panel.add(addUser());
        panel.add(editUser());
        panel.add(selectUser());
        panel.add(addAndRemove());
        panel.add(removeUser());
        return panel;
    }

    private JPanel addUser() {
        JPanel addUser = new JPanel();
        addUser.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
        addUser.add(new JLabel("<html>Nom :<br/></html>"));
        addUser.add(nom);
        addUser.add(new JLabel("<html>prenom :<br/></html>"));
        addUser.add(prenom);
        addUser.add(new JLabel("email :"));
        addUser.add(email);
        addUser.setBorder(BorderFactory.createTitledBorder("Ajouter une personne"));
        JButton addBtn = new JButton("Ajouter");
        addBtn.addActionListener(addBtnListener(this));
        addUser.add(addBtn);
        addUser.add(Box.createVerticalStrut(50));

        return addUser;
    }

    private ActionListener addBtnListener(UserMenu menu) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users user = inscriptions.createPersonne(
                        menu.nom.getText(),
                        menu.prenom.getText(),
                        menu.email.getText(),
                        0
                );
                menu.usersList.addItem(user);
                menu.user = user;
            }
        };
    }

    private JPanel selectUser() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Selectionner un utilisateur : "));
        panel.setBorder(BorderFactory.createTitledBorder("Selectionner une personne"));
        this.makeUsersList();
        usersList.setPreferredSize(new Dimension(200, 20));
        usersList.addActionListener(selectBoxListener(usersList, this));
        panel.add(Box.createHorizontalStrut(100));
        panel.add(usersList);
        panel.add(Box.createVerticalStrut(50));
        return panel;
    }

    private void makeUsersList() {
        usersList = new JComboBox<Users>();
        inscriptions.getPersonnes().forEach(user -> usersList.addItem(user));
    }

    private ActionListener selectBoxListener(JComboBox<Users> box, UserMenu menu) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Users selected = (Users) box.getSelectedItem();
                menu.editNom.setText(selected.getNom());
                menu.editPrenom.setText(selected.getPrenom());
                menu.editEmail.setText(selected.getMail());
                menu.user = selected;
                selected.getCompetition().forEach(compet -> competsRemList.addItem(compet));
                menu.makeCompetsList();
                System.out.println(selected.getCompetition());
            }
        };
    }
    
    private void makeCompetsList() {
    	competsList.removeAllItems();
    	Set<Competition> userComp = user.getCompetition();
    	Set<Competition> compets = inscriptions.getCompetitions();
    	for(Competition c : compets) {
    		boolean exists = false;
    		for(Competition uc : userComp) {
    			exists = (uc.getId() ==  c.getId());
    		}
    		if(!exists)
    			this.competsList.addItem(c);
    	}
    }
    
    private JPanel addAndRemove() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(addTo());
        panel.add(removeFrom());
        return panel;
    }

    private JPanel addTo() {
        JPanel panel = new JPanel();
        panel.add(Box.createVerticalStrut(10));
        //panel.setLayout(new GridLayout(2,1));
        JComboBox boxTeams = new JComboBox();
        boxTeams.addItem("toto");
        boxTeams.addItem("riri");
        boxTeams.addItem("fifi");
        boxTeams.addItem("loulou");
        boxTeams.addItem("yolo");
        boxTeams.setPreferredSize(new Dimension(200, 20));
        boxTeams.addActionListener(addTeamAL(boxTeams));
        JPanel teams = new JPanel();
        teams.add(new JLabel("Ajouter à l'équipe :"));
        teams.add(boxTeams);
        competsList.setPreferredSize(new Dimension(200, 20));
        JButton competsAddBtn = new JButton("Ajouter");
        competsAddBtn.addActionListener(addCompetListener(this));
        JPanel compets = new JPanel();
        compets.add(new JLabel("Ajouter à la compétition :"));
        compets.add(competsList);
        compets.add(competsAddBtn);
        panel.setBorder(BorderFactory.createTitledBorder("Ajouter a"));
        panel.add(teams);
        panel.add(Box.createVerticalStrut(20));
//		panel.add(Box.createHorizontalStrut(100));
        panel.add(compets);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(10));
        return panel;
    }
    
    private ActionListener addTeamAL(JComboBox box) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(box.getSelectedItem());
            }
        };
    }

    private ActionListener addCompetListener(UserMenu menu) {
    	return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Competition compet = (Competition) menu.competsList.getSelectedItem();
				user.inscription(compet);
				menu.competsRemList.addItem(compet);
				menu.competsList.removeItem(compet);
			}
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
    	return new ActionListener()
    	{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Competition compet = (Competition) menu.competsRemList.getSelectedItem();
				menu.user.desinscription(compet);
				menu.competsRemList.removeItem(compet);
				menu.competsList.addItem(compet);
				System.out.println(compet);
			}
    		
    	};
    }
    
    private JPanel editUser() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Editer placeholder"));
        panel.add(new JLabel("<html>Nom :<br/></html>"));
        panel.add(editNom);
        panel.add(new JLabel("<html>prenom :<br/></html>"));
        panel.add(editPrenom);
        panel.add(new JLabel("email :"));
        panel.add(editEmail);
        JButton button = new JButton("Editer");
        button.addActionListener(editBtnListener(this));
        panel.add(button);
        panel.add(Box.createVerticalStrut(50));
        return panel;
    }

    private ActionListener editBtnListener(UserMenu menu) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Users user = (Users) usersList.getSelectedItem();
                user.setNom(menu.editNom.getText());
                user.setPrenom(menu.editPrenom.getText());
                user.setMail(menu.editEmail.getText());
                menu.inscriptions.edit(user);
            }
        };
    }

    private JPanel removeUser() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Effacer l'utilisateur"));
        JButton button = new JButton("Effacer");
        button.addActionListener(deleteBtnListener(this));
        panel.add(button);
        return panel;
    }

    private ActionListener deleteBtnListener(UserMenu menu) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users user = (Users) menu.usersList.getSelectedItem();
                inscriptions.remove(user);
                menu.usersList.removeItem(user);
            }
        };
    }
}
