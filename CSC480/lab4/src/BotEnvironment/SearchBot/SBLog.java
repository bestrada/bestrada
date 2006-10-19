package BotEnvironment.SearchBot;

import java.awt.*;
import java.beans.*;
import javax.swing.*;

public class SBLog extends JInternalFrame implements PropertyChangeListener
{
    Bot bot;
    JTextArea logArea;
    JScrollPane mainPane;

    public SBLog (Bot bot) {

        super("Bot Log", true);
        this.bot = bot;
        bot.addPropertyChangeListener(this);

        logArea = new JTextArea();
        logArea.setEditable(false);

        mainPane = new JScrollPane(logArea);
        mainPane.setWheelScrollingEnabled(true);
        mainPane.setPreferredSize(new Dimension(640, 150));
        getContentPane().add(mainPane);
        pack();
    }

    /**
     * Evaluates PropertyChangeEvents and updates the log accordingly.
     *
     * @param e The PropertyChangeEvent to evaluate.
     */
    public void propertyChange (PropertyChangeEvent e) {

        String change = e.getPropertyName();

        if (change.equals(SBConstants.BOT_LOGGED)) {

            String logLine = (String) e.getNewValue();
            logArea.append(logLine + "\r\n");
            mainPane.getVerticalScrollBar().setValue(mainPane.getVerticalScrollBar().getMaximum());
        }
        else if (change.equals(SBConstants.RESET)) {

            logArea.setText(null);
        }
    }
}