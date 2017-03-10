package presentation.ihm;

import java.awt.*;


import javax.swing.*;


public class MainIhm {

	private JFrame frame = new JFrame();
	private JPanel panel;
	private JTextField field = new JTextField();
	private JLabel name = new JLabel("boop");
	private JPanel body;
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
		frame.setContentPane(setFrame());
		//frame.setSize(WIDTH, HEIGHT);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private JPanel setFrame()
	{
		panel = new JPanel();
		panel.setFont(new Font("Serif", Font.PLAIN, 40));
		JPanel userMenu = new UserMenu().getPanel();
		JPanel teamMenu = new TeamMenu().getPanel();
		JPanel competMenu = new CompetMenu().getPanel();
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
		tabs.addTab("Personnes", userMenu);
		tabs.addTab("Personnes", teamMenu);
		tabs.addTab("Equipes", competMenu);
		panel.add(tabs);
		return panel;
	}
	
	
//	private JPanel getPanel(String name)
//	{
//		JPanel panel = new JPanel();
//		panel.setLayout(new FlowLayout());
//		JButton listBtn = new JButton(name);
//		listBtn.addActionListener(listListener(name));
//		listBtn.setPreferredSize(new Dimension(300,50));
//		listBtn.setBorder(BorderFactory.createLineBorder(Color.black, 0));
//		listBtn.setBackground(Color.black);
//		listBtn.setForeground(Color.white);
//		panel.add(listBtn);
//		panel.setBackground(Color.black);
//		return panel;
//	}
	
//	private ActionListener listListener(String name)
//	{
//		return new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//				switch(name) {
//				case "Personnes" : repaintFrame(new UserMenu()); break;
//				case "Équipes" : repaintFrame(new TeamMenu()); break;
//				case "Compétitions" : repaintFrame(new CompetMenu()); break;
//				}
//			}
//		};
//	}
//	
//	private void repaintFrame(SubMenu menu)
//	{
//		body.removeAll();
//		body.add(menu.getPanel());
//		body.add(backButton());
//		body.setVisible(true);
//		body.validate();
//		body.repaint();
//	}
//	
//	protected JButton backButton()
//	{
//		JButton button = new JButton("Go back, Bitch");
//		button.addActionListener(backListener());
//		return button;
//	}
//	
//	private ActionListener backListener()
//	{
//		return new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//				init();
//			}
//		};
//	}
}
