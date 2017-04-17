package presentation.ihm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import application.inscriptions.Candidat;
import application.inscriptions.Competition;
import application.inscriptions.Inscriptions;
import application.inscriptions.Users;

public class CompetMenu extends SubMenu {
	
	private JTextField nom = new JTextField(20);
	private JTextField duree = new JTextField(20);
	private JTextField editNom = new JTextField(20);
	private JTextField editDuree = new JTextField(20);
	private JCheckBox enTeam = new JCheckBox("En équipe");
	private JCheckBox editEnTeam = new JCheckBox("En équipe");
	private JComboBox<Competition> competList = new JComboBox<Competition>();
	private JComboBox<Candidat> candidatsList = new JComboBox<Candidat>();
	private JComboBox<Candidat> candidatsRemList = new JComboBox<Candidat>();
	private Competition competition;
	private JDatePickerImpl dp;
	private JDatePickerImpl editDp;
	
	public CompetMenu(Inscriptions ins)
	{
		super(ins);
	}
	
	public JPanel getPanel()
	{
		panel.add(addCompet());
		panel.add(selectCompet());
		panel.add(editCompet());
		panel.add(addAndRemove());
		panel.add(removeCompet());
//		panel.add(placeholder());
		return panel;	
	}
	
	private JPanel addCompet()
	{
		JPanel panel = this.getSubPanel("Ajouter une compétition");
		this.addLabelledComponent(panel, "Nom :", nom);
		this.addLabelledComponent(panel, "Durée :", duree);
		dp = getDatePicker();
		panel.add(dp);
		panel.add(enTeam);
		panel.add(this.getButton("Ajouter", addBtnListener()));
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}
	
	private ActionListener addBtnListener()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Competition compet = inscriptions.createCompetition(
					nom.getText(), 
					(Calendar) dp.getModel().getValue(),
					Integer.parseInt(duree.getText()), 
					enTeam.isSelected()
				);
				competList.addItem(compet);
			}
		};
	}
	
	private JPanel selectCompet()
	{
		JPanel panel = this.getSubPanel("Selectionner une compétition");
		System.out.println(inscriptions.getCompetitions());
		inscriptions.getCompetitions().forEach(compet -> competList.addItem(compet));
		competList.addActionListener(competListListener());
		competList.setPreferredSize(new Dimension(200,20));
		panel.add(Box.createHorizontalStrut(100));
		panel.add(competList);
		this.addLabelledComponent(panel, "Selectionnez une compétition :", competList);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}
	
	private ActionListener competListListener()
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Competition selected = (Competition) competList.getSelectedItem();
                inscriptions.refresh(selected);
                editNom.setText(selected.getNom());
                editDuree.setText(Integer.toString(selected.getDuree()));
                editEnTeam.setSelected(selected.getEnEquipe());
                editDp.getModel().setDate(
                	selected.getDate().get(Calendar.YEAR), 
                	selected.getDate().get(Calendar.MONTH), 
                	selected.getDate().get(Calendar.DAY_OF_MONTH)
                );
                editDp.getModel().setSelected(true);
                competition = selected;
                makeCandidatsList();
			}
		};
	}
	
	private void makeCandidatsList()
	{
    	candidatsList.removeAllItems();
    	candidatsRemList.removeAllItems();
    	inscriptions.getCandidats().forEach((c) -> {
            for(Candidat uc : competition.getCandidats()) {
                if(uc.getId() ==  c.getId()) {
                	candidatsRemList.addItem(c);
                	return;
                }
            }
            if(competition.getEnEquipe() == c.isTeam())
            	candidatsList.addItem(c);
        });
	}
	
	private JPanel editCompet()
	{
		JPanel panel = this.getSubPanel("Editer une compétition");
		this.addLabelledComponent(panel, "Nom :", editNom);
		this.addLabelledComponent(panel, "Durée :", editDuree);
		editDp = this.getDatePicker();
		panel.add(editDp);
		panel.add(editEnTeam);
		panel.add(this.getButton("Editer", this.editBtnListener(this)));
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
				compet.setDate((Calendar) editDp.getModel().getValue());
				compet.setDuree(Integer.parseInt(menu.editDuree.getText()));
				compet.setEnEquipe(menu.editEnTeam.isSelected());
				menu.inscriptions.edit(compet);
			}
		};
	}
	
	private JPanel addAndRemove()
	{
		JPanel panel = this.getSubPanel("Modifier candidats");
		panel.add(new JLabel("Ajouter candidat :"));
		candidatsList.setPreferredSize(new Dimension(200,20));
		panel.add(candidatsList);
		panel.add(this.getButton("Ajouter", addCandidatListener(this)));
		panel.add(Box.createHorizontalStrut(100));
		panel.add(new JLabel("Enlever Candidat :"));
		candidatsRemList.setPreferredSize(new Dimension(200,20));
		panel.add(this.getButton("Enlever", this.remCandidatListener(this)));
		panel.add(candidatsRemList);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}
	
	private ActionListener addCandidatListener(CompetMenu menu)
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Candidat candidat = (Candidat) menu.candidatsList.getSelectedItem();
				menu.competition.addCandidat(candidat); 
				menu.candidatsList.removeItem(candidat);
				menu.candidatsRemList.addItem(candidat);
			}
		};
	}
	
	private ActionListener remCandidatListener(CompetMenu menu)
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Candidat candidat = (Candidat) menu.candidatsRemList.getSelectedItem();
				menu.competition.removeCandidat(candidat); 
				menu.candidatsList.addItem(candidat);
				menu.candidatsRemList.removeItem(candidat);
			}
		};
	}
	
    private JPanel removeCompet() {
        JPanel panel = getSubPanel("Effacer la compétition");
        panel.add(getButton("Effacer", deleteBtnListener()));
        return panel;
    }

    private ActionListener deleteBtnListener() {
        return (ActionEvent e) -> {
            Competition compet = (Competition) competList.getSelectedItem();
            inscriptions.remove(compet);
            competList.removeItem(compet);
        };
    }
    
	private JPanel placeholder()
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,150));
		return panel;
	}
}
