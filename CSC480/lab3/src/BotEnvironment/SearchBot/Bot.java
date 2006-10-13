package BotEnvironment.SearchBot;

import java.io.*;
import java.util.*;
import java.beans.*;

/**
 * Bot is the abstract base class for different
 * types of bots.  From this class, a bot can
 * be a reflex agent, model-based agent, search
 * agent, etc.
 *
 * @author Matt Colón
 */

public abstract class Bot extends Thread
{
    /** The current direction the bot is facing. */
    protected static int HEADING = SBConstants.BOT_HEADING_DEFAULT;

    /** True if autostepping is enabled, false otherwise. */
    private boolean autoStep;
    /** True if the goal has been reached by the bot, false otherwise. */
    private boolean goalReached;

    /** The starting location of the map. */
    private Node startingLocation;
    /** The current location of the bot. */
    private Node botLocation;

    /** The total cost of moving to the goal. */
    private int movementCost;

    /** Used to fire PropertyChangeEvents. */
    private PropertyChangeSupport changes;

    /** The name of the agent developer */
    private String developerName;

    /** Creates a new Bot object and opens the log file. */
    protected Bot() {

        startingLocation = null;
        botLocation = null;

        autoStep = false;
        goalReached = false;

        developerName = "Developer";

        movementCost = 0;

        changes = new PropertyChangeSupport(this);
    }

    /** Returns true if the goal has been reached by the bot, false otherwise. */
    public boolean getGoalReached() {

        return goalReached;
    }

    /** Returns the starting location of the bot. */
    public Node getStartingLocation() {

        return startingLocation;
    }

    /** Returns the current location of the bot. */
    public Node getBotLocation() {

        return botLocation;
    }

    /** Returns the total movement cost. */
    public int getMovementCost() {

        return movementCost;
    }

    /** Returns the name of the developer. */
    public String getDeveloperName() {

        return developerName;
    }

    /** Sets the goal reached variable to true. */
    public void reachedGoal() {

        goalReached = true;
    }

    /** Sets the developer name. */
    public void setDeveloperName(String developerName) {

        this.developerName = developerName;
    }

    /**
     * Sets the starting location for the bot in the map.  This is only
     * be called when a map is opened.
     *
     * @param startingLocation The starting location for the bot.
     */
    protected void setStartingLocation (Node startingLocation) {

        this.startingLocation = startingLocation;
        botLocation = startingLocation;

        startingLocation.setIsViewed();
        startingLocation.setBotIsHere(true);
        startingLocation.setSearchIsHere(true);
    }

    /**
     * Enables and disables autostepping.
     *
     * @param autoStep True if autostepping is being enabled, false if not.
     */
    public void enableAutoStepping (boolean autoStep) {

        this.autoStep = autoStep;
    }

    /**
     * Adds to the movement cost.
     *
     * @param cost The cost to add to the movement cost.
     */
    protected void addToMovementCost (int cost) {

        movementCost += cost;
    }

    /**
     * Turns the bot to the left at a right angle and adds the cost of
     * turning to the total movement cost.
     */
    public void turnLeft() {

        addToMovementCost(SBConstants.TURN_COST);
        if (HEADING == SBConstants.NORTH) HEADING = SBConstants.WEST;
        else if (HEADING == SBConstants.EAST) HEADING = SBConstants.NORTH;
        else if (HEADING == SBConstants.SOUTH) HEADING = SBConstants.EAST;
        else if (HEADING == SBConstants.WEST) HEADING = SBConstants.SOUTH;
        botLocation.botHasTurned();

        try {
            sleep(SBConstants.DELAY);
        }
        catch (InterruptedException e) {System.out.println(e);}
    }

    /**
     * Turns the bot to the right at a right angle and adds the cost of
     * turning to the total movement cost.
     */
    public void turnRight() {

        addToMovementCost(SBConstants.TURN_COST);
        if (HEADING == SBConstants.NORTH) HEADING = SBConstants.EAST;
        else if (HEADING == SBConstants.EAST) HEADING = SBConstants.SOUTH;
        else if (HEADING == SBConstants.SOUTH) HEADING = SBConstants.WEST;
        else if (HEADING == SBConstants.WEST) HEADING = SBConstants.NORTH;
        botLocation.botHasTurned();

        try {
            sleep(SBConstants.DELAY);
        }
        catch (InterruptedException e) {System.out.println(e);}
    }

    /**
     * Turns the bot to the given direction.
     *
     * @param direction The direction to turn the bot towards in integer form.
     */
    public void turnTo (int direction) {

        if (direction == SBConstants.NORTH) {

            if (HEADING == SBConstants.EAST) {

                turnLeft();
            }
            else if (HEADING == SBConstants.WEST) {

                turnRight();
            }
            else if (HEADING == SBConstants.SOUTH) {

                turnRight();
                turnRight();
            }
        }
        else if (direction == SBConstants.EAST) {

            if (HEADING == SBConstants.SOUTH) {

                turnLeft();
            }
            else if (HEADING == SBConstants.NORTH) {

                turnRight();
            }
            else if (HEADING == SBConstants.WEST) {

                turnRight();
                turnRight();
            }
        }
        else if (direction == SBConstants.SOUTH) {

            if (HEADING == SBConstants.WEST) {

                turnLeft();
            }
            else if (HEADING == SBConstants.EAST) {

                turnRight();
            }
            else if (HEADING == SBConstants.NORTH) {

                turnRight();
                turnRight();
            }
        }
        else if (direction == SBConstants.WEST) {

            if (HEADING == SBConstants.NORTH) {

                turnLeft();
            }
            else if (HEADING == SBConstants.SOUTH) {

                turnRight();
            }
            else if (HEADING == SBConstants.EAST) {

                turnRight();
                turnRight();
            }
        }
    }

    /**
     * Moves the bot forward in the direction it is facing and adds the cost
     * of moving to the total movement cost.
     */
    public void moveForward() {

        try {
            sleep(SBConstants.DELAY);
        }
        catch (InterruptedException e) {System.out.println(e);}

        addToMovementCost(SBConstants.MOVE_COST);
        botLocation.setBotIsHere(false);
        if (HEADING == SBConstants.NORTH && !botLocation.getNorth().getIsWall())
            botLocation = botLocation.getNorth();
        else if (HEADING == SBConstants.EAST && !botLocation.getEast().getIsWall())
            botLocation = botLocation.getEast();
        else if (HEADING == SBConstants.SOUTH && !botLocation.getSouth().getIsWall())
            botLocation = botLocation.getSouth();
        else if (HEADING == SBConstants.WEST && !botLocation.getWest().getIsWall())
            botLocation = botLocation.getWest();
        addToMovementCost(botLocation.getCost());

        if (botLocation.getIsGoal()) {

            goalReached = true;
        }
        else {

            botLocation.setBotIsHere(true);
        }
    }

    /**
     * Adds a line to the bot log.
     *
     * @param line The line to add to the bot log.
     */
    public void log (String line) {

        changes.firePropertyChange(SBConstants.BOT_LOGGED, null, line);
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

    /** Resets the bot to the starting location and sets the movement cost to zero. */
    protected void reset() {

        HEADING = SBConstants.BOT_HEADING_DEFAULT;

        autoStep = false;
        goalReached = false;

        botLocation = startingLocation;

        startingLocation.setIsViewed();
        startingLocation.setSearchIsHere(true);
        startingLocation.setBotIsHere(true);

        movementCost = 0;

        changes.firePropertyChange(SBConstants.RESET, null, null);
    }

    /** The method to override with what to do at each bot step. */
    public abstract void step();

    /**
     * Calls the step method every amount of milliseconds as defined in
     * {@link BotEnvironment.SearchBot.SBConstants SBConstants.DELAY} as
     * long as autostepping is enabled.
     */
    public void run() {

        while (true) {

            if (autoStep) {

                step();
                try {
                    sleep(SBConstants.DELAY);
                }
                catch (InterruptedException e) {System.out.println(e);}
            }
        }
    }
}