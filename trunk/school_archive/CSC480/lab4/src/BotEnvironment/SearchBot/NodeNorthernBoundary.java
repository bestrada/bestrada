package BotEnvironment.SearchBot;

/**
 * NodeNorthernBoundary is an extension of Node,
 * representing the northern boundary of a map.
 *
 * @author Matt Colón
 */

public class NodeNorthernBoundary extends Node
{
    /**
     * Creates a new northern boundary node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeNorthernBoundary (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of a
     * northern boundary, as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants},
     * as a string.
     */
    public String toString() {

        return SBConstants.CHAR_NORTHERN_BOUNDARY;
    }
}