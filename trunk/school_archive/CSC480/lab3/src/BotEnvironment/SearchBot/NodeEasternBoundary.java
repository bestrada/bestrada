package BotEnvironment.SearchBot;

/**
 * NodeEasternBoundary is an extension of Node,
 * representing the eastern boundary of a map.
 *
 * @author Matt Colón
 */

public class NodeEasternBoundary extends Node
{
    /**
     * Creates a new eastern boundary node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeEasternBoundary (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of a
     * eastern boundary, as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants},
     * as a string.
     */
    public String toString() {

        return SBConstants.CHAR_EASTERN_BOUNDARY;
    }
}