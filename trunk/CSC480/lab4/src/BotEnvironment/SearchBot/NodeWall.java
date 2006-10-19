package BotEnvironment.SearchBot;

/**
 * NodeSpace is an extension of Node, representing
 * a wall node in a map.
 *
 * @author Matt Colón
 */

public class NodeWall extends Node
{
    /**
     * Creates a new wall node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeWall (int x, int y) {

        super();
        setCost(SBConstants.INFINITE);
        setX(x);
        setY(y);
        setIsWall();
    }

    /**
     * Returns the character representation of the wall,
     * as defined in {@link BotEnvironment.SearchBot.SBConstants
     * SBConstants}, as a string.
     */
    public String toString() {

        return SBConstants.CHAR_WALL;
    }
}