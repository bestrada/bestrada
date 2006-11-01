package BotEnvironment.SearchBot;

import java.io.*;
import java.util.*;

/**
 * NodeMap is the model representation of a map of nodes
 * constructed from a map file.  A text map is read in from
 * a file, and each character is constructed into a node,
 * given its properties, and adjoined to each other.
 *
 * @author Matt Colón
 */

public class NodeMap
{
    /** The double array of nodes for easy access to individual nodes. */
    private Node[][] nodes;
    /** The location of the starting node. */
    private Node startingLocation;
    /** The location of the goal node. */
    private Node goalLocation;

    /** The x location of the starting node. */
    private int startingX;
    /** The y location of the starting node. */
    private int startingY;
    /** The x location of the goal node. */
    private int goalX;
    /** The y location of the goal node. */
    private int goalY;
    /** The horizontal map size. */
    private int mapSizeX;
    /** The vertical map size. */
    private int mapSizeY;

    /** The file name of the map. */
    private String fileName;

    /**
     * Creates a new NodeMap object with an internal map generated
     * from a file.
     *
     * @param file The file of the map file.
     */
    protected NodeMap (File file) {

        fileName = file.getName();

        try {

            constructMap(file);
        }
        catch (FileNotFoundException e) {System.out.println(e);}
        catch (IOException e) {System.out.println(e);}
    }

    /**
     * Returns the node at the given x and y location.
     *
     * @param x The x location of the node.
     * @param y The y location of the node.
     */
    protected Node getNode (int x, int y) {

        return nodes[x][y];
    }

    /** Returns the location of the starting node. */
    protected Node getStartingLocation() {

        return startingLocation;
    }

    /** Returns the location of the goal node. */
    protected Node getGoalLocation() {

        return goalLocation;
    }

    /** Returns the x location of the starting node. */
    protected int getStartingX() {

        return startingX;
    }

    /** Returns the y location of the starting node. */
    protected int getStartingY() {

        return startingY;
    }

    /** Returns the x location of the goal node. */
    protected int getGoalX() {

        return goalX;
    }

    /** Returns the y location of the goal node. */
    protected int getGoalY() {

        return goalY;
    }

    /** Returns the horizontal map size. */
    protected int getMapSizeX() {

        return mapSizeX;
    }

    /** Returns the vertical map size. */
    protected int getMapSizeY() {

        return mapSizeY;
    }

    /** Returns the file name of the map. */
    protected String getFileName() {

        return fileName;
    }

    /**
     * Parses the map information from a file.
     *
     * @param file The file with the map information.
     */
    private void constructMap (File file) throws FileNotFoundException, IOException {

        FileReader fileReader = new FileReader(file);
        BufferedReader inFile = new BufferedReader(fileReader);
        String line = inFile.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line);

        if (line != null) {

            String mapSizeXString = tokenizer.nextToken();
            String mapSizeYString = tokenizer.nextToken();
            mapSizeX = Integer.parseInt(mapSizeXString) + 2;
            mapSizeY = Integer.parseInt(mapSizeYString) + 2;

            nodes = new Node[mapSizeX][mapSizeY];
        }
        else {

            System.out.println("Map information not available.");
        }

        for (int y = 0; y < mapSizeY; y++) {

            char[] mapSymbols = null;

            for (int x = 0; x < mapSizeX; x++) {

                if (x == 0 && y != 0 && y != mapSizeY - 1) {

                    line = inFile.readLine();
                    mapSymbols = line.toCharArray();
                }

                if (y == 0) {

                    if (x == 0) {

                        nodes[x][y] = new NodeNorthwesternBoundary(x, y);
                    }
                    else if (x == mapSizeX - 1) {

                        nodes[x][y] = new NodeNortheasternBoundary(x, y);
                    }
                    else {

                        nodes[x][y] = new NodeNorthernBoundary(x, y);
                    }
                }
                else if (y == mapSizeY - 1) {

                    if (x == 0) {

                        nodes[x][y] = new NodeSouthwesternBoundary(x, y);
                    }
                    else if (x == mapSizeX - 1) {

                        nodes[x][y] = new NodeSoutheasternBoundary(x, y);
                    }
                    else {

                        nodes[x][y] = new NodeSouthernBoundary(x, y);
                    }
                }
                else if (x == 0) {

                    nodes[x][y] = new NodeWesternBoundary(x, y);
                }
                else if (x == mapSizeX - 1) {

                    nodes[x][y] = new NodeEasternBoundary(x, y);
                }
                else {

                    char charSymbol = mapSymbols[x - 1];
                    String mapSymbol = Character.toString(charSymbol);

                    if (mapSymbol.equals(SBConstants.CHAR_START)) {

                        nodes[x][y] = new NodeStart(x, y);
                        startingX = x;
                        startingY = y;
                        startingLocation = nodes[x][y];
                    }
                    else if (mapSymbol.equals(SBConstants.CHAR_GOAL)) {

                        nodes[x][y] = new NodeGoal(x, y);
                        goalX = x;
                        goalY = y;
                        goalLocation = nodes[x][y];
                    }
                    else if (mapSymbol.equals(SBConstants.CHAR_WALL)) {

                        nodes[x][y] = new NodeWall(x, y);
                    }
                    else if (mapSymbol.equals(SBConstants.CHAR_SPACE)) {

                        nodes[x][y] = new NodeSpace(x, y);
                    }
                    else {

                        nodes[x][y] = new NodeCost(x, y, Integer.parseInt(mapSymbol));
                    }
                }
            }
        }

        for (int y = 0; y < mapSizeY; y++) {

            for (int x = 0; x < mapSizeX; x++) {

                Node node = nodes[x][y];

                if (x > 0) {

                    if (x < mapSizeX - 1) {

                        node.setEast(nodes[x + 1][y]);
                        node.setWest(nodes[x - 1][y]);
                    }
                    else {

                        node.setWest(nodes[x - 1][y]);
                    }
                }
                else {

                    node.setEast(nodes[x + 1][y]);
                }

                if (y > 0) {

                    if (y < mapSizeY - 1) {

                        node.setNorth(nodes[x][y - 1]);
                        node.setSouth(nodes[x][y + 1]);
                    }
                    else {

                        node.setNorth(nodes[x][y - 1]);
                    }
                }
                else {

                    node.setSouth(nodes[x][y + 1]);
                }
            }
        }
    }

    /**
     * Resets all nodes to being unviewed, unevaluated, untraveled,
     * and the bot and search not occupying or evaluating them.
     */
    protected void reset() {

        for (int y = 0; y < mapSizeY; y++) {

            for (int x = 0; x < mapSizeX; x++) {

                nodes[x][y].reset();
            }
        }
    }

    /**
     * Returns the character-based representation of the map,
     * with characters as defined in {@link BotEnvironment.SearchBot.SBConstants
     * SBConstants}, as a string.
     */
    public String toString() {

        String output = "";

        for (int y = 0; y < mapSizeY; y++) {

            for (int x = 0; x < mapSizeX; x++) {

                output += nodes[x][y].toString();
            }

            output += "\r\n";
        }

        return output;
    }
}