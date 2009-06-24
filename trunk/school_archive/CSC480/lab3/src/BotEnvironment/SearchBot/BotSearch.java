package BotEnvironment.SearchBot;

import java.util.*;

/**
 * BotSearch is the abstract base class bots
 * that will search through and traverse maps
 * using a number of searching algorithms.
 * Searching and movement are abstracted for
 * extensions of this class to take care of
 * managing node changes that will update the
 * GUI.  This way, the programmer of a Bot
 * extension can work on implementing search
 * and movement steps without worrying about
 * correctly modifying the nodes when they
 * should be modified.
 *
 * @author Matt Colón
 */

public abstract class BotSearch extends Bot
{
    /** True if the goal has been found by the search, false otherwise. */
    private boolean goalFound;

    /** The current location of the search. */
    private Node searchLocation;

    /** The fringe that queues nodes to be evaluated. */
    private LinkedList fringe;

    /** The total cost of searching for the goal. */
    private int searchCost;

    /** Creates a new BotSearch object and opens the log file. */
    protected BotSearch() {

        super();

        searchLocation = null;

        fringe = new LinkedList();

        goalFound = false;

        searchCost = 0;
    }

    /** Returns true if the goal has been found by the search, false otherwise. */
    public boolean getGoalFound() {

        return goalFound;
    }

    /** Returns the current location of the search. */
    public Node getSearchLocation() {

        return searchLocation;
    }

    /** Returns the fringe used in the search. */
    public LinkedList getFringe() {

        return fringe;
    }

    /** Returns the total search cost. */
    public int getSearchCost() {

        return searchCost;
    }

    /**
     * Returns the node to the north of the current location of the search
     * and adds the cost of querying a tile to the total search cost.
     */
    public Node getNorthOfSearchLocation() {

        addToSearchCost(SBConstants.SEARCH_COST);
        Node north = searchLocation.getNorth();
        north.setIsViewed();
        return north;
    }

    /**
     * Returns the node to the east of the current location of the search.
     * and adds the cost of querying a tile to the total search cost.
     */
    public Node getEastOfSearchLocation() {

        addToSearchCost(SBConstants.SEARCH_COST);
        Node east = searchLocation.getEast();
        east.setIsViewed();
        return east;
    }

    /**
     * Returns the node to the south of the current location of the search.
     * and adds the cost of querying a tile to the total search cost.
     */
    public Node getSouthOfSearchLocation() {

        addToSearchCost(SBConstants.SEARCH_COST);
        Node south = searchLocation.getSouth();
        south.setIsViewed();
        return south;
    }

    /**
     * Returns the node to the west of the current location of the search.
     * and adds the cost of querying a tile to the total search cost.
     */
    public Node getWestOfSearchLocation() {

        addToSearchCost(SBConstants.SEARCH_COST);
        Node west = searchLocation.getWest();
        west.setIsViewed();
        return west;
    }

    /** Returns a string representation of the fringe. */
    public String getFringeString() {

        if (fringe.isEmpty())
        {
            return "{}";
        }

        String output = "{";

        for (int i = 0; i < fringe.size() - 1; i++) {

            Node node = (Node) fringe.get(i);
            int x = node.getX();
            int y = node.getY();

            output += "[" + x + "," + y + "], ";
        }

        Node node = (Node) fringe.get(fringe.size() - 1);
        int x = node.getX();
        int y = node.getY();

        output += "[" + x + "," + y + "]}";

        return output;
    }

    /** Returns the next node at the beginning of the fringe. */
    public Node getNextFringeNode() {

        return (Node) fringe.remove(0);
    }

    /**
     * Returns true if the fringe contains the given node.
     *
     * @param node The node to see if it is in the fringe.
     */
    public boolean fringeContains (Node node) {

        return fringe.contains(node);
    }

    /**
     * Sets the starting location for the bot in the map.  This is only
     * be called when a map is opened.
     *
     * @param startingLocation The starting location for the bot.
     */
    protected void setStartingLocation (Node startingLocation) {

        super.setStartingLocation(startingLocation);
        searchLocation = startingLocation;
    }

    /**
     * Adds to the search cost.
     *
     * @param cost The cost to add to the search cost.
     */
    protected void addToSearchCost (int cost) {

        searchCost += cost;
    }

    /**
     * Adds a node to end of the fringe.
     *
     * @param node The node to add to the fringe.
     */
    public void addToFringe (Node node) {

        fringe.add(node);
    }

    /**
     * Adds a node to the fringe at a certain index.
     *
     * @param node The node to add to the fringe.
     * @param index The index of where to add the node in the fringe.
     */
    public void addToFringe (Node node, int index) {

        fringe.add(index, node);
    }

    /**
     * Sorts the fringe according to a Comparator.
     *
     * @param comparator The Comparator to sort the fringe by.
     */
    public void sortFringe (Comparator comparator) {

        Collections.sort(fringe, comparator);
    }

    /**
     * Moves the location of the search to another location and adds the
     * cost of updating the environment to the total search cost.
     *
     * @param location The new location for the search.
     */
    public void moveSearchLocation (Node location) {

        searchLocation.setSearchIsHere(false);
        addToSearchCost(SBConstants.SEARCH_COST);
        searchLocation = location;

        if (searchLocation.getIsGoal()) {

            goalFound = true;
        }
        else {

            searchLocation.setSearchIsHere(true);
        }
    }

    /**
     * Resets the search to the starting location, empties the fringe,
     * and sets the search cost to zero, and calls the super reset method.
     */
    protected void reset() {

        super.reset();

        goalFound = false;
        searchLocation = getStartingLocation();
        fringe = new LinkedList();

        searchCost = 0;
    }

    /**
     * Calls the search step until the goal has been found by the search,
     * then calls the movement step until the goal has been reached by the bot.
     */
    public void step() {

        if (!goalFound) {

            searchStep();
        }
        else if (!getGoalReached()) {

            movementStep();
        }
    }

    /** The step to do while searching for the goal. */
    abstract public void searchStep();
    /** The step to do while moving towards the goal. */
    abstract public void movementStep();
}