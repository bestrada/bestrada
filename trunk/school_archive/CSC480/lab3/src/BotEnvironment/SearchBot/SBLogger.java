package BotEnvironment.SearchBot;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import java.io.*;

public class SBLogger extends JPanel implements PropertyChangeListener, ActionListener
{
    Bot bot;
    JTextArea logArea;
    JScrollPane mainPane;
    JButton saveLog;

    public SBLogger (Bot bot) {

        //super("Bot Log");
        this.bot = bot;
        bot.addPropertyChangeListener(this);

        //setPreferredSize(new Dimension(250, 400));

        logArea = new JTextArea();
        //logArea.setPreferredSize(new Dimension(250, 400));
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setEditable(false);

        saveLog = new JButton("Save log...");
        saveLog.addActionListener(this);

        mainPane = new JScrollPane(logArea);
        mainPane.setWheelScrollingEnabled(true);
        mainPane.setPreferredSize(new Dimension(250, 400));
        setLayout(new BorderLayout());
        add(new JLabel("Agent Log", SwingConstants.CENTER), BorderLayout.NORTH);
        add(mainPane, BorderLayout.CENTER);
        add(saveLog, BorderLayout.SOUTH);
        validate();
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

    public void actionPerformed (ActionEvent e)
    {
        Object source = e.getSource();

        if (source == saveLog)
        {
            JFileChooser saveChooser = new JFileChooser(new File("."));
            saveChooser.setFileFilter(new SBLogFilter());
            int accepted = saveChooser.showOpenDialog(this);

            if (accepted == JFileChooser.APPROVE_OPTION) {

                String logFilename = saveChooser.getSelectedFile().getName();
                if (logFilename.indexOf('.') < 0)
                {
                    logFilename = logFilename + ".txt";
                }
                File logFile = new File(logFilename);
                String logString = logArea.getText();

                try
                {
                    FileWriter fw = new FileWriter(logFile);
                    PrintWriter pw = new PrintWriter(fw);

                    pw.println(logString);
                    pw.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void resetText()
    {
        logArea.setText("");
    }
}