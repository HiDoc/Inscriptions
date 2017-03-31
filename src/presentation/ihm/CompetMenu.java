package presentation.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.inscriptions.Candidat;
import application.inscriptions.Competition;
import application.inscriptions.Inscriptions;

public class CompetMenu extends SubMenu {
	
	private JTextField nom = new JTextField(20);
	private JTextField date = new JTextField(20);
	private JTextField duree = new JTextField(20);
	private JTextField editNom = new JTextField(20);
	private JTextField editDate = new JTextField(20);
	private JTextField editDuree = new JTextField(20);
	private JCheckBox enTeam = new JCheckBox("En équipe");
	private JCheckBox editEnTeam = new JCheckBox("En équipe");
	private JComboBox<Competition> competList = new JComboBox<Competition>();
	private JComboBox<Candidat> candidatsList = new JComboBox<Candidat>();
	private JComboBox<Candidat> candidatsRemList = new JComboBox<Candidat>();
	private Inscriptions inscriptions;
	private Competition competition;
	
	public CompetMenu(Inscriptions ins)
	{
		this.inscriptions = ins;
	}
	
	public JPanel getPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createLineBorder(Color.decode("#EEEEEE"), 5));
		panel.add(addCompet());
		panel.add(selectCompet());
		panel.add(editCompet());
		panel.add(addAndRemove());
		panel.add(placeholder());
		return panel;	
	}
	
	private JPanel addCompet()
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel("Nom :"));
		panel.add(nom);
		panel.add(new JLabel("Date :"));
		panel.add(date);
		panel.add(new JLabel("Duree :"));
		panel.add(duree);
		panel.add(enTeam);
		panel.setBorder(BorderFactory.createTitledBorder("Ajouter une compétition"));
		JButton btn = new JButton("Ajouter");
		btn.addActionListener(addBtnListener(this));
		panel.add(btn);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}
	
	private ActionListener addBtnListener(CompetMenu menu)
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Competition compet = menu.inscriptions.createCompetition(
					menu.nom.getText(), 
					menu.parseDate(menu.date.getText()), 
					Integer.parseInt(menu.duree.getText()), 
					menu.enTeam.isSelected()
				);
				competList.addItem(compet);
			}
		};
	}
	
	private JPanel selectCompet()
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel("Selectionner une compétition : "));
		panel.setBorder(BorderFactory.createTitledBorder("Selectionner une compétition"));
		System.out.println(inscriptions.getCompetitions());
		inscriptions.getCompetitions().forEach(compet -> competList.addItem(compet));
		competList.addActionListener(competListListener(this));
		competList.setPreferredSize(new Dimension(200,20));
		panel.add(Box.createHorizontalStrut(100));
		panel.add(competList);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}
	
	private ActionListener competListListener(CompetMenu menu)
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Competition selected = (Competition) menu.competList.getSelectedItem();
                menu.editNom.setText(selected.getNom());
                menu.editDate.setText(selected.getDateToString());
                menu.editDuree.setText(Integer.toString(selected.getDuree()));
                menu.editEnTeam.setSelected(selected.getEnEquipe());
                menu.competition = selected;
                menu.makeCandidatsList();
                menu.makeCandidatsRemList();
			}
		};
	}
	
	private void makeCandidatsList()
	{
    	candidatsList.removeAllItems();
    	Set<Candidat> competCandidats = competition.getCandidats();
    	Set<Candidat> candidats = inscriptions.getCandidats();
    	for(Candidat c : candidats) {
    		boolean exists = false;
    		for(Candidat cc : competCandidats) {
    			exists = (cc.getId() ==  c.getId());
    		}
    		if(!exists)
    			candidatsList.addItem(c);
    	}
	}
	
	private void makeCandidatsRemList()
	{
		candidatsRemList.removeAllItems();
		competition.getCandidats().forEach(c -> candidatsRemList.addItem(c));
	}
	
	private JPanel editCompet()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Editer placeholder"));
		panel.add(new JLabel("Nom :"));
		panel.add(editNom);
		panel.add(new JLabel("Date :"));
		panel.add(editDate);
		panel.add(new JLabel("Durée :"));
		panel.add(editDuree);
		panel.add(editEnTeam);
		JButton editBtn = new JButton("Editer");
		editBtn.addActionListener(editBtnListener(this));
		panel.add(editBtn);
		panel.add(Box.createVerticalStrut(50));
		return panel;
		
	}
	
	private ActionListener editBtnListener(CompetMenu menu)
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Competition compet = (Competition) competList.getSelectedItem();
				compet.setNom(menu.editNom.getText());
				compet.setDate(menu.parseDate(menu.editDate.getText()));
				compet.setDuree(Integer.parseInt(menu.editDuree.getText()));
				compet.setEnEquipe(menu.editEnTeam.isSelected());
				menu.inscriptions.edit(compet);
			}
		};
	}
	
	private JPanel addAndRemove()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Modifier candidats"));
		panel.add(new JLabel("Ajouter candidat :"));
		candidatsList.setPreferredSize(new Dimension(200,20));
		candidatsList.addActionListener(addTeamAL());
		panel.add(candidatsList);
		panel.add(Box.createHorizontalStrut(100));
		panel.add(new JLabel("Enlever Candidat :"));
		candidatsRemList.setPreferredSize(new Dimension(200,20));
		candidatsRemList.addActionListener(addTeamAL());
		panel.add(candidatsRemList);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}
	
	
	private ActionListener addTeamAL()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
//				System.out.println(box.getSelectedItem());
			}
		};
	}

	
	private JPanel placeholder()
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,150));
		return panel;
	}
}
