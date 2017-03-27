package presentation.ihm;

import java.awt.*;


import javax.swing.*;

import application.inscriptions.Inscriptions;


public class MainIhm {

	private JFrame frame = new JFrame();
	private JPanel panel;
	private Inscriptions inscriptions = new Inscriptions();
	public final static int HEIGHT = 600;
	public final static int WIDTH = 1200;
	
	public static void main(String[] args)
	{
		new MainIhm();

	}
	
	public MainIhm()
	{
		init();
	}
	
	protected void init()
	{
		frame.setTitle("Inscriptions sportives");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setFont(new Font("", Font.PLAIN, 200));
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(setFrame(), BorderLayout.CENTER);
		
		//frame.setSize(WIDTH, HEIGHT);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private JPanel setFrame()
	{
		panel = new JPanel();
		panel.setFont(new Font("Serif", Font.PLAIN, 40));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JPanel userMenu = new UserMenu(inscriptions).getPanel();
		JPanel teamMenu = new TeamMenu().getPanel();
		JPanel competMenu = new CompetMenu(inscriptions).getPanel();
		JTabbedPane tabs = new JTabbedPane();
		userMenu.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
		tabs.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
		tabs.addTab("Personnes", userMenu);
		tabs.addTab("Equipes", teamMenu);
		tabs.addTab("Compétitions", competMenu);
		panel.add(tabs);
		return panel;
	}
}
