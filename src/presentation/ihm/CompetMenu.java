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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CompetMenu extends SubMenu {
	
	private JTextField nom = new JTextField(20);
	private JTextField date = new JTextField(20);
	private JTextField duree = new JTextField(20);
	private JCheckBox enTeam = new JCheckBox("En équipe");
	
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
		panel.add(enTeam);
		panel.setBorder(BorderFactory.createTitledBorder("Ajouter une personne"));
		panel.add(new JButton("Ajouter"));
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}
	
	private JPanel selectCompet()
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel("Selectionner une compétition : "));
		panel.setBorder(BorderFactory.createTitledBorder("Selectionner une compétition"));
		JComboBox box = new JComboBox();
		box.addItem("toto");
		box.addItem("riri");
		box.addItem("fifi");
		box.addItem("loulou");
		box.addItem("yolo");
		box.setPreferredSize(new Dimension(200,20));
		panel.add(Box.createHorizontalStrut(100));
		panel.add(box);
		panel.add(Box.createVerticalStrut(50));
		return panel;
	}
	
	private JPanel editCompet()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Editer placeholder"));
		panel.add(new JLabel("Nom :"));
		panel.add(new JTextField(20));
		panel.add(new JLabel("Date :"));
		panel.add(new JTextField(20));
		panel.add(new JLabel("Durée :"));
		panel.add(new JTextField(20));
		panel.add(new JButton("Editer"));
		panel.add(Box.createVerticalStrut(50));
		return panel;
		
	}
	
	private JPanel addAndRemove()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Modifier candidats"));
		panel.add(new JLabel("Ajouter candidat :"));
		JComboBox boxAdd = new JComboBox();
		boxAdd.addItem("toto");
		boxAdd.addItem("riri");
		boxAdd.addItem("fifi");
		boxAdd.addItem("loulou");
		boxAdd.addItem("yolo");
		boxAdd.setPreferredSize(new Dimension(200,20));
		boxAdd.addActionListener(addTeamAL(boxAdd));
		panel.add(boxAdd);
		panel.add(Box.createHorizontalStrut(100));
		panel.add(new JLabel("Enlever Candidat :"));
		JComboBox boxRemove = new JComboBox();
		boxRemove.addItem("toto");
		boxRemove.addItem("riri");
		boxRemove.addItem("fifi");
		boxRemove.addItem("loulou");
		boxRemove.addItem("yolo");
		boxRemove.setPreferredSize(new Dimension(200,20));
		boxRemove.addActionListener(addTeamAL(boxRemove));
		panel.add(boxRemove);
		panel.add(Box.createVerticalStrut(50));
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

	
	private JPanel placeholder()
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,150));
		return panel;
	}
}
