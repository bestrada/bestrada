package BotEnvironment.SearchBot;

import javax.swing.*;

public class SBAgentMapFrame extends JInternalFrame
{
    JScrollPane mapPane;
    TileAgentMap tileAgentMap;

    public SBAgentMapFrame (TileAgentMap tileAgentMap, String developerName) {

        super(developerName + ": Agent View", true);
        this.tileAgentMap = tileAgentMap;

        mapPane = new JScrollPane(tileAgentMap);
        getContentPane().add(mapPane);
        pack();
    }
}