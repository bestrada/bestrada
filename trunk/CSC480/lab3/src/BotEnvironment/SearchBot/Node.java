package BotEnvironment.SearchBot;

import java.beans.*;

/**
 * Node is the abstract base class for all nodes,
 * containing the basic information about a node.
 * A Node object contains information about the
 * basic type of a node it is, how the search bot
 * has interacted with it, and its location and
 * relation to other nodes in a Map object.
 *
 * @author Matt Colón
 */

public abstract class Node
{
    /** True if the node is a wall node or a boundary node. */
    private boolean isWall;
    /** True if the node is the goal node. */
    private boolean isGoal;

    /** True if the node has been placed into the search fringe. */
    private boolean isViewed;
    /** True if the node has been taken out of the fringe and evaluated. */
    private boolean isEvaluated;
    /** True if the bot has traveled onto the node. */
    private boolean isTraveled;
    /** True if the bot is currently on the node. */
    private boolean botIsHere;
    /** True if the search is currently viewing this node. */
    private boolean searchIsHere;

    /** The x location of the node in a map. */
    private int x;
    /** The y location of the node in a map. */
    private int y;
    /** The additional cost (if any) to travel the node. */
    private int cost;

    /** The northern adjacent node to this node. */
    private Node north;
    /** The eastern adjacent node to this node. */
    private Node east;
    /** The southern adjacent node to this node. */
    private Node south;
    /** The western adjacent node to this node. */
    private Node west;

    /** Used to fire PropertyChangeEvents. */
    private PropertyChangeSupport changes;

    /** Creates a new Node object. */
    protected Node() {

        isWall = false;
        isGoal = false;

        isViewed = false;
        isEvaluated = false;
        isTraveled = false;
        botIsHere = false;
        searchIsHere = false;

        x = SBConstants.NULL;
        y = SBConstants.NULL;
        cost = SBConstants.INFINITE;

        north = null;
        east = null;
        south = null;
        west = null;

        changes = new PropertyChangeSupport(this);
    }

    /** Returns true if the node is a wall. */
    public boolean getIsWall() {

        return isWall;
    }

    /** Returns true if the node is a goal. */
    public boolean getIsGoal() {

        return isGoal;
    }

    /** Returns true if the node has been viewed. */
    public boolean getIsViewed() {

        return isViewed;
    }

    /** Returns true if the node has been evaluated. */
    public boolean getIsEvaluated() {

        return isEvaluated;
    }

    /** Returns true if the node has been traveled. */
    public boolean getIsTraveled() {

        return isTraveled;
    }

    /** Returns true if the bot is currently on this node. */
    public boolean getBotIsHere() {

        return botIsHere;
    }

    /** Returns true if the search is currently on this node. */
    public boolean getSearchIsHere() {

        return searchIsHere;
    }

    /** Returns the x location of the node in the map. */
    public int getX() {

        return x;
    }

    /** Returns the y location of the node in the map. */
    public int getY() {

        return y;
    }

    /** Returns the additional cost to travel the node. */
    public int getCost() {

        return cost;
    }

    /** Returns the northern adjacent node. */
    public Node getNorth() {

        if (north != null) {

            north.setIsViewed();
        }
        return north;
    }

    /** Returns the eastern adjacent node. */
    public Node getEast() {

        if (east != null) {

            east.setIsViewed();
        }
        return east;
    }

    /** Returns the southern adjacent node. */
    public Node getSouth() {

        if (south != null) {

            south.setIsViewed();
        }
        return south;
    }

    /** Returns the western adjacent node. */
    public Node getWest() {

        if (west != null) {

            west.setIsViewed();
        }
        return west;
    }

    /** Sets the node to be a wall or boundary. */
    protected void setIsWall() {

        isWall = true;
    }

    /** Sets the node to be the goal. */
    protected void setIsGoal() {

        isGoal = true;
    }

    /** Sets the node to have been viewed and fires a PropertyChangeEvent. */
    protected void setIsViewed() {

        if (!isViewed) {

            isViewed = true;
            changes.firePropertyChange(SBConstants.VIEWED, new Boolean(!isViewed), new Boolean(isViewed));
        }
    }

    /** Sets the node to have been evaluated and fires a PropertyChangeEvent. */
    protected void setIsEvaluated() {

        if (!isEvaluated) {

            isEvaluated = true;
            changes.firePropertyChange(SBConstants.EVALUATED, new Boolean(!isEvaluated), new Boolean(isEvaluated));
        }
    }

    /** Sets the node to have been traveled and fires a PropertyChangeEvent. */
    protected void setIsTraveled() {

        if (!isTraveled) {

            isTraveled = true;
            changes.firePropertyChange(SBConstants.TRAVELED, new Boolean(!isTraveled), new Boolean(isTraveled));
        }
    }

    /**
     * Changes if the search is currently at the node and fires a PropertyChangeEvent.
     *
     * @param searchIsHere True if the search is at this node, false if not.
     */
    protected void setSearchIsHere (boolean searchIsHere) {

        setIsEvaluated();
        this.searchIsHere = searchIsHere;
        changes.firePropertyChange(SBConstants.SEARCH_IS_HERE, new Boolean(!searchIsHere), new Boolean(searchIsHere));
    }

    /**
     * Changes if the bot is currently at the node and fires a PropertyChangeEvent.
     *
     * @param botIsHere True if the bot is at this node, false if not.
     */
    protected void setBotIsHere (boolean botIsHere) {

        setIsTraveled();
        this.botIsHere = botIsHere;
        changes.firePropertyChange(SBConstants.BOT_IS_HERE, new Boolean(!botIsHere), new Boolean(botIsHere));
    }

    /** Fires a PropertyChangeEvent saying the bot has turned on this node. */
    protected void botHasTurned() {

        changes.firePropertyChange(SBConstants.BOT_HAS_TURNED, null, null);
    }

    /**
     * Sets the x location of the node in the map.
     *
     * @param x The x location of the node in the map.
     */
    protected void setX (int x) {

        this.x = x;
    }

    /**
     * Sets the y location of the node in the map.
     *
     * @param y The y location of the node in the map.
     */
    protected void setY (int y) {

        this.y = y;
    }

    /**
     * Sets the additional cost to travel the node.
     *
     * @param cost The additional cost to travel the node.
     */
    protected void setCost (int cost) {

        this.cost = cost;
    }

    /**
     * Sets the northern adjacent node.
     *
     * @param north The northern adjacent node.
     */
    protected void setNorth (Node north) {

        this.north = north;
    }

    /**
     * Sets the eastern adjacent node.
     *
     * @param east The eastern adjacent node.
     */
    protected void setEast (Node east) {

        this.east = east;
    }

    /**
     * Sets the southern adjacent node.
     *
     * @param south The southern adjacent node.
     */
    protected void setSouth (Node south) {

        this.south = south;
    }

    /**
     * Sets the western adjacent node.
     *
     * @param west The western adjacent node.
     */
    protected void setWest (Node west) {

        this.west = west;
    }

    /**
     * Resets the node to being unviewed, unevaluated, untraveled,
     * and the bot and search not occupying or evaluating it.
     */
    protected void reset() {

        isViewed = false;
        isEvaluated = false;
        isTraveled = false;
        botIsHere = false;
        searchIsHere = false;
        changes.firePropertyChange(SBConstants.RESET, null, null);
    }

    /**
     * Adds a PropertyChangeListener to this node.
     *
     * @param propertyChangeListener The PropertyChangeListener to add.
     */
    protected void addPropertyChangeListener (PropertyChangeListener propertyChangeListener) {

        changes.addPropertyChangeListener(propertyChangeListener);
    }

    /**
     * Removes a PropertyChangeListener from this node.
     *
     * @param propertyChangeListener The PropertyChangeListener to remove.
     */
    protected void removePropertyChangeListener (PropertyChangeListener propertyChangeListener) {

        changes.removePropertyChangeListener(propertyChangeListener);
    }
}