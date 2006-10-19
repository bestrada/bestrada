package BotEnvironment;

import BotEnvironment.SearchBot.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class BotEnvironment extends JFrame implements ActionListener, ChangeListener
{
    private static final File CURRENT_DIRECTORY = new File(".");

    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem fileNewSearchBotSession;
    private JMenuItem fileNewQuickStart;
    private JMenuItem fileNewWumpusWorldSession;
    private JFileChooser botChooser;

    private SBSession sbSession;

    private JMenu searchBotMap;
    private JMenuItem searchBotMapCreateMap;
    private JMenuItem searchBotMapNewRandomMap;
    private JMenuItem searchBotMapOpenMap;
    private JMenuItem searchBotMapCloseMap;
    private JMenuItem searchBotMapResetMap;
    private JFileChooser mapChooser;

    private JMenu searchBotBot;
    private JMenuItem searchBotBotStep;
    private JMenuItem searchBotBotAutoStep;
    private JMenuItem searchBotBotStop;
    private JRadioButtonMenuItem[] searchBotBotSearch;
    private ButtonGroup searchBotBotSearches;

    private JMenu searchBotView;
    private JCheckBoxMenuItem searchBotViewMap;
    private JCheckBoxMenuItem searchBotViewAgentMap;
    private JCheckBoxMenuItem searchBotViewLegend;
    private JCheckBoxMenuItem searchBotViewLog;

    private JMenu searchBotHelp;

    private JButton stopButton;
    private JButton stepButton;
    private JButton autoStepButton;
    private JSlider delaySlider;
    private SBLogger logger;
    private JPanel mainWindow;

    public static void main (String[] args) {

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {}

        if (args.length == 0)
        {
            JFrame frame = new BotEnvironment();
            frame.setVisible(true);
        }
        else if (args.length == 3)
        {
            JFrame frame = new BotEnvironment(args[0], args[1], args[2]);
            frame.setVisible(true);
        }
        else
        {
            System.out.println("usage: java -cp .;BotEnvironment.jar; BotEnvironment.BotEnvironment [agent_name.class map_name.sbm delay]");
        }
    }

    public BotEnvironment() {

        super("Bot Environment");
        //setResizable(false);
        //setExtendedState(Frame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SBVerification.THIS = this;

        file = new JMenu("File");
        fileNewSearchBotSession = new JMenuItem("New Bot Environment Session...");
        fileNewQuickStart = new JMenuItem("New Quick Start Session...");
        fileNewWumpusWorldSession = new JMenuItem("New Wumpus World Session...");
        fileNewSearchBotSession.addActionListener(this);
        fileNewQuickStart.addActionListener(this);
        fileNewWumpusWorldSession.addActionListener(this);

        file.add(fileNewSearchBotSession);
        file.add(fileNewQuickStart);
        //file.add(fileNewWumpusWorldSession);

        menuBar = new JMenuBar();
        menuBar.add(file);

        setJMenuBar(menuBar);
    }

    public BotEnvironment(String botFileName, String mapFileName, String delayString) {

        super("Bot Environment");
        //setResizable(false);
        //setExtendedState(Frame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try
        {
            int delay = Integer.parseInt(delayString);
            SBConstants.DELAY = delay;
        }
        catch (Exception e)
        {
            System.out.println("Error: Non-integer delay");
        }

        SBVerification.THIS = this;

        file = new JMenu("File");
        fileNewSearchBotSession = new JMenuItem("New Bot Environment Session...");
        fileNewQuickStart = new JMenuItem("New Quick Start Session...");
        fileNewWumpusWorldSession = new JMenuItem("New Wumpus World Session...");
        fileNewSearchBotSession.addActionListener(this);
        fileNewQuickStart.addActionListener(this);
        fileNewWumpusWorldSession.addActionListener(this);

        file.add(fileNewSearchBotSession);
        file.add(fileNewQuickStart);
        //file.add(fileNewWumpusWorldSession);

        menuBar = new JMenuBar();
        menuBar.add(file);

        setJMenuBar(menuBar);

        File botFile = new File(botFileName);
        Bot bot = SBVerification.verifyBotFile(botFile);
        if (bot != null) {

            try
            {
                prepareSearchBotSession(bot);
            }
            catch (Exception e)
            {
                System.out.println("Error: Invalid agent file");
                System.exit(-1);
            }
        }
        else
        {
            System.out.println("Error: Invalid agent file");
            System.exit(-1);
        }

        File mapFile = new File(mapFileName);
        try
        {
            sbSession.openMap(mapFile);
        }
        catch (Exception e)
        {
            System.out.println("Error: Invalid map file");
        }

        searchBotMapCloseMap.setEnabled(true);
        searchBotMapResetMap.setEnabled(true);
        searchBotBotStep.setEnabled(true);
        searchBotBotAutoStep.setEnabled(true);
        searchBotBotStop.setEnabled(false);
        searchBotViewMap.setEnabled(true);
        searchBotViewAgentMap.setEnabled(true);
        searchBotViewLegend.setEnabled(true);
        //searchBotViewLog.setEnabled(true);
        searchBotViewMap.setSelected(true);
        searchBotViewAgentMap.setSelected(true);
        //searchBotViewLegend.setSelected(true);
        //searchBotViewLog.setSelected(true);

        stopButton.setEnabled(false);
        stepButton.setEnabled(true);
        autoStepButton.setEnabled(true);
    }

    private void prepareSearchBotSession (Bot bot) {

        SBConstants.SEARCH = SBConstants.SEARCHES[0];

        if (sbSession != null) {

            getContentPane().remove(mainWindow);
            sbSession = null;
        }

        menuBar.removeAll();

        searchBotMap = new JMenu("Map");
        searchBotMapCreateMap = new JMenuItem("Create Map...");
        searchBotMapNewRandomMap = new JMenuItem("New Random Map...");
        searchBotMapOpenMap = new JMenuItem("Open Map...");
        searchBotMapCloseMap = new JMenuItem("Close Map");
        searchBotMapResetMap = new JMenuItem("Reset Map");

        searchBotMapCreateMap.addActionListener(this);
        searchBotMapNewRandomMap.addActionListener(this);
        searchBotMapOpenMap.addActionListener(this);
        searchBotMapCloseMap.addActionListener(this);
        searchBotMapResetMap.addActionListener(this);

        searchBotMapCreateMap.setEnabled(false);
        searchBotMapNewRandomMap.setEnabled(false);
        searchBotMapCloseMap.setEnabled(false);
        searchBotMapResetMap.setEnabled(false);

        searchBotBot = new JMenu("Bot");
        searchBotBotStep = new JMenuItem("Step");
        searchBotBotAutoStep = new JMenuItem("Auto Step");
        searchBotBotStop = new JMenuItem("Stop");
        searchBotBotSearch = new JRadioButtonMenuItem[SBConstants.NUMBER_OF_SEARCHES];
        searchBotBotSearches = new ButtonGroup();
        for (int i = 0; i < SBConstants.NUMBER_OF_SEARCHES; i++) {

            searchBotBotSearch[i] = new JRadioButtonMenuItem(SBConstants.SEARCHES[i]);
            searchBotBotSearches.add(searchBotBotSearch[i]);
        }

        searchBotBotStep.addActionListener(this);
        searchBotBotAutoStep.addActionListener(this);
        searchBotBotStop.addActionListener(this);
        for (int i = 0; i < SBConstants.NUMBER_OF_SEARCHES; i++) {

            searchBotBotSearch[i].addActionListener(this);
        }

        searchBotBotStep.setEnabled(false);
        searchBotBotAutoStep.setEnabled(false);
        searchBotBotStop.setEnabled(false);
        searchBotBotSearch[0].doClick();

        searchBotView = new JMenu("View");
        searchBotViewMap = new JCheckBoxMenuItem("Map");
        searchBotViewAgentMap = new JCheckBoxMenuItem("Agent Map");
        searchBotViewLegend = new JCheckBoxMenuItem("Legend");
        searchBotViewLog = new JCheckBoxMenuItem("Log");

        searchBotViewMap.addActionListener(this);
        searchBotViewAgentMap.addActionListener(this);
        searchBotViewLegend.addActionListener(this);
        //searchBotViewLog.addActionListener(this);

        searchBotViewMap.doClick();
        searchBotViewAgentMap.doClick();
        //searchBotViewLegend.doClick();
        //searchBotViewLog.doClick();

        searchBotViewMap.setEnabled(false);
        searchBotViewAgentMap.setEnabled(false);
        searchBotViewLegend.setEnabled(false);
        searchBotViewLog.setEnabled(false);

        searchBotHelp = new JMenu("Help");

        searchBotMap.add(searchBotMapCreateMap);
        searchBotMap.add(searchBotMapNewRandomMap);
        searchBotMap.addSeparator();
        searchBotMap.add(searchBotMapOpenMap);
        searchBotMap.add(searchBotMapCloseMap);
        searchBotMap.addSeparator();
        searchBotMap.add(searchBotMapResetMap);

        searchBotBot.add(searchBotBotStep);
        searchBotBot.add(searchBotBotAutoStep);
        searchBotBot.add(searchBotBotStop);
        //searchBotBot.addSeparator();
        //for (int i = 0; i < SBConstants.NUMBER_OF_SEARCHES; i++) {

        //    searchBotBot.add(searchBotBotSearch[i]);
        //}

        searchBotView.add(searchBotViewMap);
        searchBotView.add(searchBotViewAgentMap);
        searchBotView.addSeparator();
        searchBotView.add(searchBotViewLegend);
        //searchBotView.addSeparator();
        //searchBotView.add(searchBotViewLog);

        menuBar.add(file);
        menuBar.add(searchBotMap);
        menuBar.add(searchBotBot);
        menuBar.add(searchBotView);
        menuBar.add(searchBotHelp);

        menuBar.validate();

        sbSession = new SBSession(bot);

        stopButton = new JButton("STOP");
        stepButton = new JButton("STEP");
        autoStepButton = new JButton("AUTO STEP");
        stopButton.setEnabled(false);
        stepButton.setEnabled(false);
        autoStepButton.setEnabled(false);
        stopButton.addActionListener(this);
        stepButton.addActionListener(this);
        autoStepButton.addActionListener(this);

        delaySlider = new JSlider(JSlider.HORIZONTAL,
                                  SBConstants.DELAY_MIN,
                                  SBConstants.DELAY_MAX,
                                  SBConstants.DELAY);
        delaySlider.setMajorTickSpacing(SBConstants.DELAY_MAJOR_TICK);
        delaySlider.setMinorTickSpacing(SBConstants.DELAY_MINOR_TICK);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.addChangeListener(this);
        delaySlider.setPreferredSize(new Dimension(400, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Delay (ms)"));
        buttonPanel.add(delaySlider);
        buttonPanel.add(stopButton);
        buttonPanel.add(stepButton);
        buttonPanel.add(autoStepButton);

        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BorderLayout());
        buttonRow.add(buttonPanel, BorderLayout.EAST);

        logger = new SBLogger(bot);
        JSplitPane splitPane =
            new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sbSession, logger);
        splitPane.setResizeWeight(1);
        splitPane.setOneTouchExpandable(false);
        //splitPane.setDividerLocation(0.80);

        mainWindow = new JPanel();
        mainWindow.setLayout(new BorderLayout());
        mainWindow.add(buttonRow, BorderLayout.NORTH);
        mainWindow.add(splitPane, BorderLayout.CENTER);

        getContentPane().add(mainWindow);
        getContentPane().validate();
    }

    public void actionPerformed (ActionEvent e) {

        Object source = e.getSource();

        if (source == fileNewSearchBotSession) {

            Bot bot = null;

            botChooser = new JFileChooser("./Agents");
            botChooser.setFileFilter(new SBBotFilter());
            int accepted = botChooser.showOpenDialog(this);

            while (accepted != JFileChooser.CANCEL_OPTION && bot == null) {

                if (accepted == JFileChooser.APPROVE_OPTION) {

                    bot = SBVerification.verifyBotFile(botChooser.getSelectedFile());

                    if (bot != null) {

                        prepareSearchBotSession(bot);
                    }
                    else {

                        accepted = botChooser.showOpenDialog(this);
                    }
                }
            }

            SBConstants.DELAY = SBConstants.DELAY_INIT;
        }
        else if (source == fileNewQuickStart) {

            Bot bot = null;

            botChooser = new JFileChooser("./Agents");
            botChooser.setFileFilter(new SBBotFilter());
            int accepted = botChooser.showOpenDialog(this);

            while (accepted != JFileChooser.CANCEL_OPTION && bot == null) {

                if (accepted == JFileChooser.APPROVE_OPTION) {

                    bot = SBVerification.verifyBotFile(botChooser.getSelectedFile());

                    if (bot != null) {

                        prepareSearchBotSession(bot);
                    }
                    else {

                        accepted = botChooser.showOpenDialog(this);
                    }
                }
            }

            SBConstants.DELAY = SBConstants.DELAY_INIT;

            File mapFile = new File("./testmaps/SearchMap.sbm");
            sbSession.openMap(mapFile);

            searchBotMapCloseMap.setEnabled(true);
            searchBotMapResetMap.setEnabled(true);
            searchBotBotStep.setEnabled(true);
            searchBotBotAutoStep.setEnabled(true);
            searchBotBotStop.setEnabled(false);
            searchBotViewMap.setEnabled(true);
            searchBotViewAgentMap.setEnabled(true);
            searchBotViewLegend.setEnabled(true);
            //searchBotViewLog.setEnabled(true);
            searchBotViewMap.setSelected(true);
            searchBotViewAgentMap.setSelected(true);
            //searchBotViewLegend.setSelected(true);
            //searchBotViewLog.setSelected(true);

            stopButton.setEnabled(false);
            stepButton.setEnabled(true);
            autoStepButton.setEnabled(true);
        }
        else if (source == fileNewWumpusWorldSession) {

            System.out.println("NOT YET!");
        }
        else if (source == searchBotMapOpenMap) {

            File mapFile = null;

            mapChooser = new JFileChooser("./testmaps");
            mapChooser.setFileFilter(new SBMapFilter());
            int accepted = mapChooser.showOpenDialog(this);

            while (accepted != JFileChooser.CANCEL_OPTION && mapFile == null) {

                if (accepted == JFileChooser.APPROVE_OPTION) {

                    mapFile = SBVerification.verifyMapFile(mapChooser.getSelectedFile());

                    if (mapFile != null) {

                        sbSession.closeMap();
                        sbSession.openMap(mapFile);

                        searchBotMapCloseMap.setEnabled(true);
                        searchBotMapResetMap.setEnabled(true);
                        searchBotBotStep.setEnabled(true);
                        searchBotBotAutoStep.setEnabled(true);
                        searchBotBotStop.setEnabled(false);
                        searchBotViewMap.setEnabled(true);
                        searchBotViewAgentMap.setEnabled(true);
                        searchBotViewLegend.setEnabled(true);
                        //searchBotViewLog.setEnabled(true);
                        searchBotViewMap.setSelected(true);
                        searchBotViewAgentMap.setSelected(true);
                        //searchBotViewLegend.setSelected(true);
                        //searchBotViewLog.setSelected(true);

                        stopButton.setEnabled(false);
                        stepButton.setEnabled(true);
                        autoStepButton.setEnabled(true);
                    }
                    else {

                        accepted = mapChooser.showOpenDialog(this);
                    }
                }
            }
        }
        else if (source == searchBotMapCloseMap) {

            sbSession.closeMap();
            logger.resetText();

            searchBotMapCloseMap.setEnabled(false);
            searchBotMapResetMap.setEnabled(false);
            searchBotBotStep.setEnabled(false);
            searchBotBotAutoStep.setEnabled(false);
            searchBotBotStop.setEnabled(false);
            for (int i = 0; i < SBConstants.NUMBER_OF_SEARCHES; i++) {

                searchBotBotSearch[i].setEnabled(true);
            }
            searchBotViewMap.setEnabled(false);
            searchBotViewAgentMap.setEnabled(false);
            searchBotViewLegend.setEnabled(false);
            searchBotViewLog.setEnabled(false);

            stopButton.setEnabled(false);
            stepButton.setEnabled(false);
            autoStepButton.setEnabled(false);
        }
        else if (source == searchBotMapResetMap) {

            sbSession.stopBot();
            sbSession.resetMap();
            logger.resetText();

            searchBotBotStep.setEnabled(true);
            searchBotBotAutoStep.setEnabled(true);
            searchBotBotStop.setEnabled(false);
            for (int i = 0; i < SBConstants.NUMBER_OF_SEARCHES; i++) {

                searchBotBotSearch[i].setEnabled(true);
            }

            stopButton.setEnabled(false);
            stepButton.setEnabled(true);
            autoStepButton.setEnabled(true);
        }
        else if (source == searchBotBotStep || source == stepButton) {

            sbSession.stepBot();

            for (int i = 0; i < SBConstants.NUMBER_OF_SEARCHES; i++) {

                searchBotBotSearch[i].setEnabled(false);
            }
        }
        else if (source == searchBotBotAutoStep || source == autoStepButton) {

            sbSession.autoStepBot();

            searchBotBotStep.setEnabled(false);
            searchBotBotAutoStep.setEnabled(false);
            searchBotBotStop.setEnabled(true);
            for (int i = 0; i < SBConstants.NUMBER_OF_SEARCHES; i++) {

                searchBotBotSearch[i].setEnabled(false);
            }

            stopButton.setEnabled(true);
            stepButton.setEnabled(false);
            autoStepButton.setEnabled(false);
        }
        else if (source == searchBotBotStop || source == stopButton) {

            sbSession.stopBot();

            searchBotBotStep.setEnabled(true);
            searchBotBotAutoStep.setEnabled(true);
            searchBotBotStop.setEnabled(false);

            stopButton.setEnabled(false);
            stepButton.setEnabled(true);
            autoStepButton.setEnabled(true);
        }
        else if (source == searchBotViewMap) {

            if (sbSession != null) {

                sbSession.showMap();
            }
        }
        else if (source == searchBotViewAgentMap) {

            if (sbSession != null) {

                sbSession.showAgentMap();
            }
        }
        else if (source == searchBotViewLegend) {

            if (sbSession != null) {

                sbSession.showLegend();
            }
        }
        else if (source == searchBotViewLog) {

            if (sbSession != null) {

                sbSession.showLog();
            }
        }
        else {

            for (int i = 0; i < SBConstants.NUMBER_OF_SEARCHES; i++) {

                if (source == searchBotBotSearch[i]) {

                    SBConstants.SEARCH = SBConstants.SEARCHES[i];
                }
            }
        }
    }

    public void stateChanged(ChangeEvent e)
    {
        JSlider source = (JSlider) e.getSource();
        int delay = (int) source.getValue();
        SBConstants.DELAY = delay;
    }
}
