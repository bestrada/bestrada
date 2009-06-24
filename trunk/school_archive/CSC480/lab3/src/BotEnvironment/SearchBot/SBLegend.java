package BotEnvironment.SearchBot;

import java.awt.*;
import javax.swing.*;

public class SBLegend extends JInternalFrame
{
    JPanel legendPanel;
    JPanel botPanel;
    JPanel spacePanel;
    JPanel wallPanel;
    JPanel startPanel;
    JPanel goalPanel;
    JPanel[] costPanels;

    JLabel botLabel;
    JLabel spaceLabel;
    JLabel wallLabel;
    JLabel startLabel;
    JLabel goalLabel;
    JLabel[] costLabels;

    JLabel botImage;
    JLabel spaceImage;
    JLabel wallImage;
    JLabel startImage;
    JLabel goalImage;
    JLabel[] costImages;

    public SBLegend() {

        super("Legend");

        legendPanel = new JPanel();
        legendPanel.setLayout(new GridLayout(SBConstants.MAXIMUM_COST + 5, 1, 0, 0));

        botImage = new JLabel(new ImageIcon(SBConstants.IMAGE_BOT_EAST));
        botImage.setPreferredSize(new Dimension(SBConstants.TILE_WIDTH * 2, SBConstants.TILE_HEIGHT * 2));
        botLabel = new JLabel("Bot", SwingConstants.CENTER);
        botLabel.setPreferredSize(new Dimension(SBConstants.LEGEND_WIDTH, SBConstants.TILE_HEIGHT * 2));
        botPanel = new JPanel();
        botPanel.setLayout(new BorderLayout());
        botPanel.setBackground(SBConstants.COLOR_DEFAULT);
        botPanel.add(botImage, BorderLayout.WEST);
        botPanel.add(botLabel, BorderLayout.CENTER);

        spaceImage = new JLabel(new ImageIcon(SBConstants.IMAGE_SPACE));
        spaceImage.setPreferredSize(new Dimension(SBConstants.TILE_WIDTH * 2, SBConstants.TILE_HEIGHT * 2));
        spaceLabel = new JLabel("Space", SwingConstants.CENTER);
        spaceLabel.setPreferredSize(new Dimension(SBConstants.LEGEND_WIDTH, SBConstants.TILE_HEIGHT * 2));
        spacePanel = new JPanel();
        spacePanel.setLayout(new BorderLayout());
        spacePanel.setBackground(SBConstants.COLOR_DEFAULT);
        spacePanel.add(spaceImage, BorderLayout.WEST);
        spacePanel.add(spaceLabel, BorderLayout.CENTER);

        wallImage = new JLabel(new ImageIcon(SBConstants.IMAGE_WALL));
        wallImage.setPreferredSize(new Dimension(SBConstants.TILE_WIDTH * 2, SBConstants.TILE_HEIGHT * 2));
        wallLabel = new JLabel("Wall", SwingConstants.CENTER);
        wallLabel.setPreferredSize(new Dimension(SBConstants.LEGEND_WIDTH, SBConstants.TILE_HEIGHT * 2));
        wallPanel = new JPanel();
        wallPanel.setLayout(new BorderLayout());
        wallPanel.setBackground(SBConstants.COLOR_DEFAULT);
        wallPanel.add(wallImage, BorderLayout.WEST);
        wallPanel.add(wallLabel, BorderLayout.CENTER);

        startImage = new JLabel(new ImageIcon(SBConstants.IMAGE_START));
        startImage.setPreferredSize(new Dimension(SBConstants.TILE_WIDTH * 2, SBConstants.TILE_HEIGHT * 2));
        startLabel = new JLabel("Start", SwingConstants.CENTER);
        startLabel.setPreferredSize(new Dimension(SBConstants.LEGEND_WIDTH, SBConstants.TILE_HEIGHT * 2));
        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        startPanel.setBackground(SBConstants.COLOR_DEFAULT);
        startPanel.add(startImage, BorderLayout.WEST);
        startPanel.add(startLabel, BorderLayout.CENTER);

        goalImage = new JLabel(new ImageIcon(SBConstants.IMAGE_GOAL));
        goalImage.setPreferredSize(new Dimension(SBConstants.TILE_WIDTH * 2, SBConstants.TILE_HEIGHT * 2));
        goalLabel = new JLabel("Goal", SwingConstants.CENTER);
        goalLabel.setPreferredSize(new Dimension(SBConstants.LEGEND_WIDTH, SBConstants.TILE_HEIGHT * 2));
        goalPanel = new JPanel();
        goalPanel.setLayout(new BorderLayout());
        goalPanel.setBackground(SBConstants.COLOR_DEFAULT);
        goalPanel.add(goalImage, BorderLayout.WEST);
        goalPanel.add(goalLabel, BorderLayout.CENTER);

        costImages = new JLabel[SBConstants.MAXIMUM_COST];
        costLabels = new JLabel[SBConstants.MAXIMUM_COST];
        costPanels = new JPanel[SBConstants.MAXIMUM_COST];
        for (int i = 0; i < SBConstants.MAXIMUM_COST; i++) {

            costImages[i] = new JLabel(new ImageIcon(SBConstants.IMAGE_COST[i]));
            costImages[i].setPreferredSize(new Dimension(SBConstants.TILE_WIDTH * 2, SBConstants.TILE_HEIGHT * 2));
            costLabels[i] = new JLabel("Cost " + (i + 1), SwingConstants.CENTER);
            costLabels[i].setPreferredSize(new Dimension(SBConstants.LEGEND_WIDTH, SBConstants.TILE_HEIGHT * 2));
            costPanels[i] = new JPanel();
            costPanels[i].setLayout(new BorderLayout());
            costPanels[i].setBackground(SBConstants.COLOR_DEFAULT);
            costPanels[i].add(costImages[i], BorderLayout.WEST);
            costPanels[i].add(costLabels[i], BorderLayout.CENTER);
        }

        legendPanel.add(botPanel);
        legendPanel.add(spacePanel);
        legendPanel.add(wallPanel);
        legendPanel.add(startPanel);
        legendPanel.add(goalPanel);
        for (int i = 0; i < SBConstants.MAXIMUM_COST; i++) {

            legendPanel.add(costPanels[i]);
        }

        repaint();

        getContentPane().add(legendPanel);
        pack();
    }
}