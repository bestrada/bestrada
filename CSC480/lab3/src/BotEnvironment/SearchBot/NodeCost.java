package BotEnvironment.SearchBot;

/**
 * NodeCost is an extension of Node, representing
 * a node that has an additional cost to travel
 * through it.
 *
 * @author Matt Colón
 */

public class NodeCost extends Node
{
    /**
     * Creates a new node with an additional cost at the given x, y location.
     *
     * @param x The x location of the node in the map.
     * @param y The y location of the node in the map.
     */
    protected NodeCost (int x, int y, int cost) {

        super();
        setCost(cost);
        setX(x);
        setY(y);
    }

    /** Returns the additional cost as a string. */
    public String toString() {

        return getCost() + "";
    }
}