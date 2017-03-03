package presentation.ihm;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class MainIhm {

	private JFrame frame = new JFrame();
	private JPanel panel;
	private JTextField field = new JTextField();
	private JLabel name = new JLabel("boop");
	
	public static void main(String[] args) {
		new MainIhm();

	}
	
	public MainIhm() {
		frame.setTitle("Inscriptions sportives");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setContentPane(setFrame());
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	
	private JPanel setFrame()
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		panel.add(new JLabel("Personnes"));
		panel.add(new JButton("test"));
		panel.add(new JButton("test"));
		panel.add(new JLabel("Équipes"));
		panel.add(new JButton("test"));
		panel.add(new JButton("test"));
		panel.add(new JLabel("Compétitions"));
		panel.add(new JButton("test"));
		panel.add(new JButton("test"));
		
		return panel;
	}
//	private JPanel setFrame()
//	{
//		panel = new JPanel();
//		panel.setLayout(new FlowLayout());
//		panel.add(new JLabel("bla"));
//		field = new JTextField();
//		field.setPreferredSize(new Dimension(200,24));
//		field.addKeyListener(getKeyListener());
//		panel.add(field);
//		panel.add(name);
//		return panel;	
//	}
//	
//	private KeyListener getKeyListener()
//	{
//		return new KeyAdapter()
//		{
//			@Override
//			public void keyPressed(KeyEvent k)
//			{
//				if(k.getKeyCode() == KeyEvent.VK_ENTER)
//				{
//					affName();
//				}
//			}
//		};
//	}
//	
//	private void affName()
//	{
//		name.setText(field.getText());
//	}
}
