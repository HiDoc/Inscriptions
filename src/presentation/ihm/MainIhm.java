package presentation.ihm;

import java.awt.*;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import application.inscriptions.Inscriptions;

public class MainIhm {

    private JFrame frame = new JFrame();
    private JPanel panel;
    private final Inscriptions inscriptions = new Inscriptions();
    
    /**
     * Fonction principale de l'application, initialise un objet mainIhm
     * 
     * @param args les paramètres de la fonction Main
     */
    public static void main(String[] args) {
        MainIhm mainIhm = new MainIhm();
    }

    /**
     * Constructeur de la classe mainMenu qui initialise les frames composantes de l'application
     */
    public MainIhm() {
        frame.setTitle("Inscriptions sportives");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFont(new Font("", Font.PLAIN, 200));
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(setFrame(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel setFrame() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        panel = new JPanel();
        panel.setFont(new Font("Serif", Font.PLAIN, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JPanel userMenu = new UserMenu(inscriptions).getPanel();
        JPanel teamMenu = new TeamMenu(inscriptions).getPanel();
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
