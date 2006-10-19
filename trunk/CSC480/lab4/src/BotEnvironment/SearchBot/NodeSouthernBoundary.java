package BotEnvironment.SearchBot;

/**
 * NodeSouthernBoundary is an extension of Node,
 * representing the southern boundary of a map.
 *
 * @author Matt Colón
 */

public class NodeSouthernBoundary extends Node
{
    /**
     * Creates a new southern boundary node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeSouthernBoundary (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of a
     * southern boundary, as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants},
     * as a string.
     */
    public String toString() {

        return SBConstants.CHAR_SOUTHERN_BOUNDARY;
    }
}