package BotEnvironment.SearchBot;

/**
 * NodeNorthwesternBoundary is an extension of Node,
 * representing the northwestern boundary of a map.
 *
 * @author Matt Colón
 */

public class NodeNorthwesternBoundary extends Node
{
    /**
     * Creates a new northwestern boundary node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeNorthwesternBoundary (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of a
     * northwestern boundary, as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants},
     * as a string.
     */
    public String toString() {

        return SBConstants.CHAR_NORTHWESTERN_BOUNDARY;
    }
}