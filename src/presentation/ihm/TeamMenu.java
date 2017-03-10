package presentation.ihm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TeamMenu implements SubMenu{
	
	private JTextField nom = new JTextField(20);
	private JPanel panel = new JPanel();
	
	public JPanel getPanel()
	{
		panel.add(new JLabel("Nom :"));
		panel.add(this.nom);
		return panel;
	}
}
