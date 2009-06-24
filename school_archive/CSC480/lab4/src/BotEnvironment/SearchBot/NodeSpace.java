package BotEnvironment.SearchBot;

/**
 * NodeSpace is an extension of Node, representing
 * a basic blank node in a map.
 *
 * @author Matt Colón
 */

public class NodeSpace extends Node
{
    /**
     * Creates a new blank node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeSpace (int x, int y) {

        super();
        setCost(0);
        setX(x);
        setY(y);
    }

    /**
     * Returns the character representation of the space,
     * as defined in {@link BotEnvironment.SearchBot.SBConstants
     * SBConstants}, as a string.
     */
    public String toString() {

        return SBConstants.CHAR_SPACE;
    }
}