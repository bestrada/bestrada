package BotEnvironment.SearchBot;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SBSession extends JPanel
{
    Bot bot;

    TileMap tileMap;
    TileAgentMap tileAgentMap;

    boolean tileMapVisible;
    boolean tileAgentMapVisible;
    boolean legendVisible;
    boolean logVisible;

    JPanel omniPanel;
    SBMapFrame mapFrame;
    SBAgentMapFrame agentMapFrame;
    SBLegend legendFrame;
    SBLog logFrame;
    JDesktopPane mainPane;

    public SBSession (Bot bot) {

        this.bot = bot;
        bot.enableAutoStepping(false);
        bot.setPriority(Thread.MIN_PRIORITY);
        bot.start();

        tileMapVisible = false;
        tileAgentMapVisible = false;
        legendVisible = false;

        mainPane = new JDesktopPane();

        legendFrame = new SBLegend();

        setLayout(new BorderLayout());
        add(mainPane, BorderLayout.CENTER);
    }

    public void openMap (File mapFile) {

        bot.enableAutoStepping(false);

        tileMap = new TileMap(mapFile);
        tileAgentMap = new TileAgentMap(tileMap.getNodeMap());

        bot.setStartingLocation(tileMap.getNodeMap().getStartingLocation());

        mapFrame = new SBMapFrame(tileMap, bot.getDeveloperName());
        mapFrame.setVisible(true);
        tileMapVisible = true;

        agentMapFrame = new SBAgentMapFrame(tileAgentMap, bot.getDeveloperName());
        agentMapFrame.setVisible(true);
        tileAgentMapVisible = true;

        //legendFrame.setVisible(true);
        //legendVisible = true;

        logFrame = new SBLog(bot);
        //logFrame.setVisible(true);
        //logVisible = true;

        resetMap();

        mainPane.add(mapFrame);
        mainPane.add(agentMapFrame);
        mainPane.add(legendFrame);
        //mainPane.add(logFrame);

        mapFrame.repaint();

        SBFunctions.setGoalLocation(tileMap.getNodeMap().getGoalLocation());
    }

    public void closeMap() {

        bot.enableAutoStepping(false);

        tileMap = null;
        tileAgentMap = null;

        tileMapVisible = false;
        tileAgentMapVisible = false;
        legendVisible = false;

        if (mapFrame != null) {

            mainPane.remove(mapFrame);
            mapFrame = null;
        }
        if (agentMapFrame != null) {

            mainPane.remove(agentMapFrame);
            agentMapFrame = null;
        }
        if (legendFrame != null) {

            mainPane.remove(legendFrame);
        }
        if (logFrame != null) {

            mainPane.remove(logFrame);
            logFrame = null;
        }

        mainPane.validate();
        mainPane.repaint();

        SBFunctions.setGoalLocation(null);
    }

    public void resetMap() {

        tileMap.reset();
        tileAgentMap.reset();
        bot.reset();

        mapFrame.repaint();
    }

    public void stepBot() {

        bot.step();
    }

    public void autoStepBot() {

        bot.enableAutoStepping(true);
    }

    public void stopBot() {

        bot.enableAutoStepping(false);
    }

    public void showMap() {

        if (tileMapVisible) {

            tileMapVisible = false;
            if (mapFrame != null) {

                mapFrame.setVisible(false);
            }
        }
        else {

            tileMapVisible = true;
            if (mapFrame != null) {

                mapFrame.setVisible(true);
            }
        }
    }

    public void showAgentMap() {

        if (tileAgentMapVisible) {

            tileAgentMapVisible = false;
            agentMapFrame.setVisible(false);
        }
        else {

            tileAgentMapVisible = true;
            agentMapFrame.setVisible(true);
        }
    }

    public void showLegend() {

        if (legendVisible) {

            legendVisible = false;
            legendFrame.setVisible(false);
        }
        else {

            legendVisible = true;
            legendFrame.setVisible(true);
        }
    }

    public void showLog() {

        if (logVisible) {

            logVisible = false;
            logFrame.setVisible(false);
        }
        else {

            logVisible = true;
            logFrame.setVisible(true);
        }
    }
}