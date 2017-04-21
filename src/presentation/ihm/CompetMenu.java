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

/**
 * Classe qui permet de contruire le panneau principal des compétitions. Il est composé de 
 * plusieurs panneaux qui correspondent aux fonctionnalités de contrôle des compétitions.
 * 
 * @author Georges
 */
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

    /**
     * Contructeur de la classe CompetMenu qui initialise un objet Inscription
     * qui permet de gérer les entrées et sorties dans la base de donnée
     *
     * @param ins - un objet Inscription
     */
    public CompetMenu(Inscriptions ins) {
        super(ins);
    }

    /**
     * Contruit le panel composé d'un panel qui permet d'ajouter une compétition,
     * de sélectionner une compétition, d'éditer une compétition et de supprimer
     * une compétition.
     * 
     * @return un objet JPanel
     */
    public JPanel getPanel() {
        panel.add(addCompet());
        panel.add(selectCompet());
        panel.add(editCompet());
        panel.add(addAndRemove());
        panel.add(removeCompet());
        return panel;
    }

    private JPanel addCompet() {
        JPanel competPanel = this.getSubPanel("Ajouter une compétition");
        this.addLabelledComponent(competPanel, "Nom :", nom);
        this.addLabelledComponent(competPanel, "Durée :", duree);
        dp = getDatePicker();
        competPanel.add(dp);
        competPanel.add(enTeam);
        competPanel.add(this.getButton("Ajouter", addBtnListener()));
        competPanel.add(Box.createVerticalStrut(50));
        return competPanel;
    }

    private ActionListener addBtnListener() {
        return (ActionEvent e) -> {
            Competition compet = inscriptions.createCompetition(
                    nom.getText(),
                    (Calendar) dp.getModel().getValue(),
                    Integer.parseInt(duree.getText()),
                    enTeam.isSelected()
            );
            competList.addItem(compet);
        };
    }

    private JPanel selectCompet() {
        JPanel competSelectPanel = this.getSubPanel("Selectionner une compétition");
        System.out.println(inscriptions.getCompetitions());
        inscriptions.getCompetitions().forEach(compet -> competList.addItem(compet));
        competList.addActionListener(competListListener());
        competList.setPreferredSize(new Dimension(200, 20));
        competSelectPanel.add(Box.createHorizontalStrut(100));
        competSelectPanel.add(competList);
        this.addLabelledComponent(competSelectPanel, "Selectionnez une compétition :", competList);
        competSelectPanel.add(Box.createVerticalStrut(50));
        return competSelectPanel;
    }

    private ActionListener competListListener() {
        return (ActionEvent e) -> {
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
        };
    }

    private void makeCandidatsList() {
        candidatsList.removeAllItems();
        candidatsRemList.removeAllItems();
        inscriptions.getCandidats().forEach((c) -> {
            for (Candidat uc : competition.getCandidats()) {
                if (uc.getId() == c.getId()) {
                    candidatsRemList.addItem(c);
                    return;
                }
            }
            if (competition.getEnEquipe() == c.isTeam()) {
                candidatsList.addItem(c);
            }
        });
    }

    private JPanel editCompet() {
        JPanel editCompetPanel = this.getSubPanel("Editer une compétition");
        this.addLabelledComponent(editCompetPanel, "Nom :", editNom);
        this.addLabelledComponent(editCompetPanel, "Durée :", editDuree);
        editDp = this.getDatePicker();
        editCompetPanel.add(editDp);
        editCompetPanel.add(editEnTeam);
        editCompetPanel.add(this.getButton("Editer", this.editBtnListener(this)));
        editCompetPanel.add(Box.createVerticalStrut(50));
        return editCompetPanel;

    }

    private ActionListener editBtnListener(CompetMenu menu) {
        return (ActionEvent e) -> {
            Competition compet = (Competition) competList.getSelectedItem();
            compet.setNom(menu.editNom.getText());
            compet.setDate((Calendar) editDp.getModel().getValue());
            compet.setDuree(Integer.parseInt(menu.editDuree.getText()));
            compet.setEnEquipe(menu.editEnTeam.isSelected());
            menu.inscriptions.edit(compet);
        };
    }

    private JPanel addAndRemove() {
        JPanel refreshPanel = this.getSubPanel("Modifier candidats");
        refreshPanel.add(new JLabel("Ajouter candidat :"));
        candidatsList.setPreferredSize(new Dimension(200, 20));
        refreshPanel.add(candidatsList);
        refreshPanel.add(this.getButton("Ajouter", addCandidatListener(this)));
        refreshPanel.add(Box.createHorizontalStrut(100));
        refreshPanel.add(new JLabel("Enlever Candidat :"));
        candidatsRemList.setPreferredSize(new Dimension(200, 20));
        refreshPanel.add(this.getButton("Enlever", this.remCandidatListener(this)));
        refreshPanel.add(candidatsRemList);
        refreshPanel.add(Box.createVerticalStrut(50));
        return refreshPanel;
    }

    private ActionListener addCandidatListener(CompetMenu menu) {
        return (ActionEvent arg0) -> {
            Candidat candidat = (Candidat) menu.candidatsList.getSelectedItem();
            menu.competition.addCandidat(candidat);
            menu.candidatsList.removeItem(candidat);
            menu.candidatsRemList.addItem(candidat);
        };
    }

    private ActionListener remCandidatListener(CompetMenu menu) {
        return (ActionEvent arg0) -> {
            Candidat candidat = (Candidat) menu.candidatsRemList.getSelectedItem();
            menu.competition.removeCandidat(candidat);
            menu.candidatsList.addItem(candidat);
            menu.candidatsRemList.removeItem(candidat);
        };
    }

    private JPanel removeCompet() {
        JPanel removePanel = getSubPanel("Effacer la compétition");
        removePanel.add(getButton("Effacer", deleteBtnListener()));
        return removePanel;
    }

    private ActionListener deleteBtnListener() {
        return (ActionEvent e) -> {
            Competition compet = (Competition) competList.getSelectedItem();
            inscriptions.remove(compet);
            competList.removeItem(compet);
        };
    }

    private JPanel placeholder() {
        JPanel placeholderPanel = new JPanel();
        placeholderPanel.setPreferredSize(new Dimension(0, 150));
        return placeholderPanel;
    }
}
