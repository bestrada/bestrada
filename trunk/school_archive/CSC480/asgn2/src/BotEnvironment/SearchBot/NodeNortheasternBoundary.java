package BotEnvironment.SearchBot;

/**
 * NodeNortheasternBoundary is an extension of Node,
 * representing the northeastern boundary of a map.
 *
 * @author Matt Colón
 */

public class NodeNortheasternBoundary extends Node
{
    /**
     * Creates a new northeastern boundary node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeNortheasternBoundary (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of a
     * northeastern boundary, as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants},
     * as a string.
     */
    public String toString() {

        return SBConstants.CHAR_NORTHEASTERN_BOUNDARY;
    }
}