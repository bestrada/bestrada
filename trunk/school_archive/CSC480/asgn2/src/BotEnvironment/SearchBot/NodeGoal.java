package BotEnvironment.SearchBot;

/**
 * NodeGoal is an extension of Node, representing
 * the goal node in a map.
 *
 * @author Matt Colón
 */

public class NodeGoal extends Node
{
    /**
     * Creates a new goal node at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeGoal (int x, int y) {

        super();
        setCost(0);
        setX(x);
        setY(y);
        setIsGoal();
    }

    /**
     * Returns the character representation of the goal,
     * as defined in {@link BotEnvironment.SearchBot.SBConstants
     * SBConstants}, as a string.
     */
    public String toString() {

        return SBConstants.CHAR_GOAL;
    }
}