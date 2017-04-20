package presentation.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;

import application.inscriptions.Inscriptions;

/**
 * Classe abstraite composées de différents helpers 
 * 
 * @author Flo
 */
public abstract class SubMenu {

    protected JPanel panel = new JPanel();
    protected Inscriptions inscriptions;

    protected SubMenu(Inscriptions ins) {
        this.inscriptions = ins;
        panel.setFont(new Font("Serif", Font.PLAIN, FrameParams.FONT_SIZE));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.decode("#EEEEEE"), 5));
    }

    protected void addLabelledComponent(JPanel panel, String label, JComponent field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    /**
     * Crée un sous-panel 
     * @param borderText le titre de la bordure
     * @return jPanel
     */
    protected JPanel getSubPanel(String borderText) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(borderText));
        return panel;
    }

    /**
     * Fonction permettant de lier directement un nom et un actionListener sur un boutton 
     * 
     * @param name le nom du boutton
     * @param al l'action à effectuer lors de l'appui sur le boutton
     * @return
     */
    protected JButton getButton(String name, ActionListener al) {
        JButton button = new JButton(name);
        button.addActionListener(al);
        return button;
    }

    /**
     * Crée une date à partir d'un String
     * @param strDate
     * @return un objet de type Calendar
     */
    protected Calendar parseDate(String strDate) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date = sdf.parse(strDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance();

    }

    /**
     * Construit un panel qui permet de sélectionner une date avec des propriétés pré-définies
     * 
     * @return un objet de type JDatePicker
     */
    protected JDatePickerImpl getDatePicker() {
        UtilCalendarModel model = new UtilCalendarModel();
        Properties p = new Properties();
        p.put("text.today", "Aujourd'hui");
        p.put("text.month", "mois");
        p.put("text.year", "année");
        JDatePanelImpl date = new JDatePanelImpl(model, p);
        date.setPreferredSize(new Dimension(300, 200));
        JDatePickerImpl dp = new JDatePickerImpl(date, new DateComponentFormatter());
        date.setBackground(Color.decode("#000000"));

        return dp;
    }
}
