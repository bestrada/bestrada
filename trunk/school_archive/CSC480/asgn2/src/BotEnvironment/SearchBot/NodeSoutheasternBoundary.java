package BotEnvironment.SearchBot;

/**
 * NodeSoutheasternBoundary is an extension of Node,
 * representing the southeastern boundary of a map.
 *
 * @author Matt Colón
 */

public class NodeSoutheasternBoundary extends Node
{
    /**
     * Creates a new southeastern boundary node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeSoutheasternBoundary (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of a
     * southeastern boundary, as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants},
     * as a string.
     */
    public String toString() {

        return SBConstants.CHAR_SOUTHEASTERN_BOUNDARY;
    }
}