package presentation.ihm;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CompetMenu implements SubMenu {
	
	private JTextField nom = new JTextField(20);
	private JTextField date = new JTextField(20);
	private JTextField duree = new JTextField(20);
	private JCheckBox enTeam = new JCheckBox("En équipe");
	
	public JPanel getPanel()
	{
		CompetMenu menu = new CompetMenu();
		JPanel panel = new JPanel();
		panel.add(new JLabel("Nom :"));
		panel.add(menu.nom);
		panel.add(new JLabel("Date :"));
		panel.add(menu.date);
		panel.add(new JLabel("Duree :"));
		panel.add(menu.enTeam);
		return panel;	
	}
}
