package BotEnvironment.SearchBot;

/**
 * SBConstants is a collection of functions used for giving
 * "hints" to the bot as it is traversing a map and managing
 * the search and movement costs of the bot.
 *
 * @author Matt Colón
 */

public class SBFunctions
{
    /** The location of the goal node. */
    private static Node goalLocation;

    /**
     * Returns the distance from a node in the map to the goal node in the map.
     *
     * @param location The node to check the distance from.
     * @return The distance in nodes to travel from the given node to the goal node,
     *         disregarding walls or additional costs.
     */
    public static int getDistanceToGoal (Node location) {

        int currentX = location.getX();
        int currentY = location.getY();
        int goalX = goalLocation.getX();
        int goalY = goalLocation.getY();

        int heuristic = Math.abs(currentX - goalX) + Math.abs(currentY - goalY);

        return heuristic;
    }

    /**
     * Returns the direction from a node in the map to the goal node in the map.
     *
     * @param location The node to check the direction from.
     * @return The direction from the given node to the goal node in integer form.
     *          This is represented as:<br>
     *          <br><blockquote>
     *          0: North<br>
     *          1: East<br>
     *          2: South<br>
     *          3: West<br>
     *              or<br>
     *          -1: The given node is the goal node.<br>
     *          <br></blockquote>
     *          These values can be compared to the {@link BotEnvironment.SearchBot.SBConstants
     *          SBConstants} values NORTH, EAST, SOUTH, and WEST.
     */
    public static int getDirectionOfGoal (Node location) {

        int currentX = location.getX();
        int currentY = location.getY();
        int goalX = goalLocation.getX();
        int goalY = goalLocation.getY();

        int dx = goalX - currentX;
        int dy = goalY - currentY;

        int direction = SBConstants.NULL;

        if (dx > 0) {

            if (dy >= -dx + 1 && dy <= dx) direction = SBConstants.EAST;
            else if (dy < -dx + 1) direction = SBConstants.NORTH;
            else if (dy > dx) direction = SBConstants.SOUTH;
        }
        else if (dx < 0) {

            if (dy >= dx && dy <= -dx - 1) direction = SBConstants.WEST;
            else if (dy < dx) direction = SBConstants.NORTH;
            else if (dy > -dx - 1) direction = SBConstants.SOUTH;
        }
        else if (dx == 0) {

            if (dy > 0) direction = SBConstants.SOUTH;
            else if (dy < 0) direction = SBConstants.NORTH;
            else if (dy == 0) direction = SBConstants.NULL;
        }

        return direction;
    }

    /**
     * Sets the goal location of the current map.
     *
     * @param location The goal location of the current map.
     */
    protected static void setGoalLocation (Node location) {

        goalLocation = location;
    }
}

