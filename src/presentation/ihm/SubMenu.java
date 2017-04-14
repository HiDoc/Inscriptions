package presentation.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.inscriptions.Inscriptions;

public abstract class SubMenu implements Comparable{

	protected JPanel panel = new JPanel();
	protected Inscriptions inscriptions;
	
	protected SubMenu(Inscriptions ins) {
		this.inscriptions = ins;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.decode("#EEEEEE"), 5));
	}
	
	protected void addLabelledComponent(JPanel panel, String label, JComponent field) {
		panel.add(new JLabel(label));
		panel.add(field);
	}
	
	protected JPanel getSubPanel(String borderText) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(borderText));
		return panel;
	}
	
	protected JButton getButton(String name, ActionListener al) {
		JButton button = new JButton(name);
		button.addActionListener(al);
		return button;
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected Calendar parseDate (String strDate)
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try
		{
			date = sdf.parse(strDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		return Calendar.getInstance();
		
	}
}
