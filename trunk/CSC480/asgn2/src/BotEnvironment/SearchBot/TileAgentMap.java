package BotEnvironment.SearchBot;

import java.awt.*;
import javax.swing.*;

/**
 * TileAgentMap is the graphic representation of a map of nodes
 * from the agent's point of view.  As the model is modified,
 * the tile map dynamically updates accordingly.  The map begins
 * in darkness, and as the search evaluates nodes, the tiles
 * become viewable.
 *
 * @author Matt Colón
 */

public class TileAgentMap extends JPanel
{
    /** The map of nodes to graphically represent. */
    private NodeMap nodeMap;
    /** The horizontal map size. */
    private int mapSizeX;
    /** The vertical map size. */
    private int mapSizeY;
    /** The double array of tiles for easy access to individual tiles. */
    private Tile[][] tiles;

    /**
     * Creates a new TileAgentMap object representing a node map from
     * the agent's point of view.
     *
     * @param nodeMap The map of nodes to represent.
     */
    protected TileAgentMap (NodeMap nodeMap) {

        super();

        this.nodeMap = nodeMap;

        mapSizeX = nodeMap.getMapSizeX();
        mapSizeY = nodeMap.getMapSizeY();
        tiles = new Tile[mapSizeX][mapSizeY];

        setLayout(new GridLayout(mapSizeY, mapSizeX, 0, 0));
        setPreferredSize(new Dimension(SBConstants.TILE_WIDTH * mapSizeX,
                                       SBConstants.TILE_HEIGHT * mapSizeY));

        constructMap();
    }

    /** Returns the node map. */
    protected NodeMap getNodeMap() {

        return nodeMap;
    }

    /** Returns the horizontal map size. */
    protected int getMapSizeX() {

        return mapSizeX;
    }

    /** Returns the vertical map size. */
    protected int getMapSizeY() {

        return mapSizeY;
    }

    /**
     * Returns the node at the given x and y location.
     *
     * @param x The x location of the node.
     * @param y The y location of the node.
     */
    protected Node getNode (int x, int y) {

        return nodeMap.getNode(x, y);
    }

    /**
     * Returns the tile at the given x and y location.
     *
     * @param x The x location of the tile.
     * @param y The y location of the tile.
     */
    protected Tile getTile (int x, int y) {

        return tiles[x][y];
    }

    /** Creates the tile map, mapping tiles to the nodes in the node map. */
    private void constructMap() {

        for (int y = 0; y < mapSizeY; y++) {

            for (int x = 0; x < mapSizeX; x++) {

                Node node = getNode(x, y);

                if (node instanceof NodeStart) {

                    tiles[x][y] = new TileStart(node, false);
                }
                else if (node instanceof NodeGoal) {

                    tiles[x][y] = new TileGoal(node, false);
                }
                else if (node instanceof NodeWall) {

                    tiles[x][y] = new TileWall(node, false);
                }
                else if (node instanceof NodeSpace) {

                    tiles[x][y] = new TileSpace(node, false);
                }
                else if (node instanceof NodeCost) {

                    tiles[x][y] = new TileCost(node, false);
                }
                else if (node instanceof NodeNorthwesternBoundary) {

                    tiles[x][y] = new TileNorthwesternBoundary(node, false);
                }
                else if (node instanceof NodeNorthernBoundary) {

                    tiles[x][y] = new TileNorthernBoundary(node, false);
                }
                else if (node instanceof NodeNortheasternBoundary) {

                    tiles[x][y] = new TileNortheasternBoundary(node, false);
                }
                else if (node instanceof NodeWesternBoundary) {

                    tiles[x][y] = new TileWesternBoundary(node, false);
                }
                else if (node instanceof NodeEasternBoundary) {

                    tiles[x][y] = new TileEasternBoundary(node, false);
                }
                else if (node instanceof NodeSouthwesternBoundary) {

                    tiles[x][y] = new TileSouthwesternBoundary(node, false);
                }
                else if (node instanceof NodeSouthernBoundary) {

                    tiles[x][y] = new TileSouthernBoundary(node, false);
                }
                else if (node instanceof NodeSoutheasternBoundary) {

                    tiles[x][y] = new TileSoutheasternBoundary(node, false);
                }

                add(tiles[x][y]);
            }
        }
    }

    /**
     * Resets all nodes to being unviewed, unevaluated, untraveled,
     * and the bot and search not occupying or evaluating them.
     */
    protected void reset() {

        nodeMap.reset();
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

                output += tiles[x][y].toString();
            }

            output += "\r\n";
        }

        return output;
    }
}