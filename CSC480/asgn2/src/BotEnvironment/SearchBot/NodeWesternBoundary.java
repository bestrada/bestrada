package BotEnvironment.SearchBot;

/**
 * NodeWesternBoundary is an extension of Node,
 * representing the western boundary of a map.
 *
 * @author Matt Colón
 */

public class NodeWesternBoundary extends Node
{
    /**
     * Creates a new western boundary node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeWesternBoundary (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of a
     * western boundary, as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants},
     * as a string.
     */
    public String toString() {

        return SBConstants.CHAR_WESTERN_BOUNDARY;
    }
}