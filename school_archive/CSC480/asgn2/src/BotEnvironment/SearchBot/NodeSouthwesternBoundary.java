package BotEnvironment.SearchBot;

/**
 * NodeSouthwesternBoundary is an extension of Node,
 * representing the southwestern boundary of a map.
 *
 * @author Matt Colón
 */

public class NodeSouthwesternBoundary extends Node
{
    /**
     * Creates a new southwestern boundary node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeSouthwesternBoundary (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of a
     * southwestern boundary, as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants},
     * as a string.
     */
    public String toString() {

        return SBConstants.CHAR_SOUTHWESTERN_BOUNDARY;
    }
}