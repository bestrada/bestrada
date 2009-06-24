package BotEnvironment.SearchBot;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SBControls extends JPanel implements ActionListener, ChangeListener, InternalFrameListener
{
    JButton step;
    JButton autoStep;
    JButton stop;
    JSlider delaySlider;

    JPanel buttonPanel;

    Bot bot;

    public SBControls (Bot bot) {

        super();
        this.bot = bot;

        step = new JButton("S");
        step.addActionListener(this);
        autoStep = new JButton("AS");
        autoStep.addActionListener(this);
        stop = new JButton("ST");
        stop.addActionListener(this);
        step.setEnabled(false);
        autoStep.setEnabled(false);
        stop.setEnabled(false);

        delaySlider = new JSlider(JSlider.HORIZONTAL, SBConstants.DELAY_MIN,
                                                      SBConstants.DELAY_MAX,
                                                      SBConstants.DELAY_INIT);
        delaySlider.setMajorTickSpacing(SBConstants.DELAY_MAJOR_TICK);
        delaySlider.setMinorTickSpacing(SBConstants.DELAY_MINOR_TICK);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.addChangeListener(this);

        Hashtable labels = new Hashtable();
        labels.put(new Integer(SBConstants.DELAY_MIN), new JLabel(SBConstants.DELAY_MIN + ""));
        labels.put(new Integer(SBConstants.DELAY_MAX / 2), new JLabel(SBConstants.DELAY_MAX / 2 + ""));
        labels.put(new Integer(SBConstants.DELAY_MAX), new JLabel(SBConstants.DELAY_MAX + ""));
        delaySlider.setLabelTable(labels);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(step);
        buttonPanel.add(autoStep);
        buttonPanel.add(stop);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.WEST);
        add(delaySlider, BorderLayout.EAST);
    }

    public void enableStepButton (boolean enable) {

        step.setEnabled(enable);
    }

    public void enableAutoStepButton (boolean enable) {

        autoStep.setEnabled(enable);
    }

    public void enableStopButton (boolean enable) {

        stop.setEnabled(enable);
    }

    public void stateChanged (ChangeEvent e) {

        JSlider source = (JSlider) e.getSource();
        SBConstants.DELAY = source.getValue();
    }

    public void actionPerformed (ActionEvent e) {

        Object source = e.getSource();

        if (source == autoStep) {

            bot.enableAutoStepping(true);
            step.setEnabled(false);
            autoStep.setEnabled(false);
            stop.setEnabled(true);
        }
        else if (source == step) {

            bot.step();
        }
        else if (source == stop) {

            bot.enableAutoStepping(false);
            step.setEnabled(true);
            autoStep.setEnabled(true);
            stop.setEnabled(false);
        }
    }

    public void internalFrameOpened (InternalFrameEvent e) {

        bot.enableAutoStepping(false);
        autoStep.setEnabled(true);
        step.setEnabled(true);
        stop.setEnabled(false);
    }

    public void internalFrameClosed (InternalFrameEvent e) {

        bot.enableAutoStepping(false);
        autoStep.setEnabled(false);
        step.setEnabled(false);
        stop.setEnabled(false);
    }

    public void internalFrameClosing (InternalFrameEvent e) {}
    public void internalFrameIconified (InternalFrameEvent e) {}
    public void internalFrameDeiconified (InternalFrameEvent e) {}
    public void internalFrameActivated (InternalFrameEvent e) {}
    public void internalFrameDeactivated (InternalFrameEvent e) {}
}