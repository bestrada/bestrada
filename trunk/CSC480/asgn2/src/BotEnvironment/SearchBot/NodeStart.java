package BotEnvironment.SearchBot;

/**
 * NodeStart is an extension of Node, representing
 * the starting node in a map.
 *
 * @author Matt Colón
 */

public class NodeStart extends Node
{
    /**
     * Creates a new starting node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeStart (int x, int y) {

        super();
        setCost(0);
        setX(x);
        setY(y);
    }

    /**
     * Returns the character representation of the start,
     * as defined in {@link BotEnvironment.SearchBot.SBConstants
     * SBConstants}, as a string.
     */
    public String toString() {

        return SBConstants.CHAR_START;
    }
}