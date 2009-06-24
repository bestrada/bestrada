package BotEnvironment.SearchBot;

import javax.swing.*;

public class SBMapFrame extends JInternalFrame
{
    JScrollPane mapPane;
    TileMap tileMap;

    public SBMapFrame (TileMap tileMap, String developerName) {

        super(developerName + ": Overview", true);
        this.tileMap = tileMap;

        mapPane = new JScrollPane(tileMap);
        getContentPane().add(mapPane);
        pack();
    }
}