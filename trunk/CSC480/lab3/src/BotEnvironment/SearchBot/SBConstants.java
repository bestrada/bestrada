package BotEnvironment.SearchBot;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;

/**
 * SBConstants is a collection of constants used for holding
 * images and colors used in the GUI as well as some frequently
 * used constants.  The properties for the environment are
 * read from a file and parsed to allow for easy changes to
 * be made to colors and graphics.
 *
 * @author Matt Colón
 */

public class SBConstants
{
    /** The file used to find which properties to use. */
    protected static final File SBCONSTANTS_PROPERTIES = new File("sbcproperties.dat");
    /** The token for finding the tile width. */
    protected static final String TOKEN_TILE_WIDTH = "TILE_WIDTH";
    /** The token for finding the tile height. */
    protected static final String TOKEN_TILE_HEIGHT = "TILE_HEIGHT";
    /** The token for finding the maximum additional cost. */
    protected static final String TOKEN_MAXIMUM_COST = "MAXIMUM_COST";
    /** The token for finding the searching cost. */
    protected static final String TOKEN_SEARCH_COST = "SEARCH_COST";
    /** The token for finding the turning cost. */
    protected static final String TOKEN_TURN_COST = "TURN_COST";
    /** The token for finding the movement cost. */
    protected static final String TOKEN_MOVE_COST = "MOVE_COST";
    /** The token for finding the number of searches available. */
    protected static final String TOKEN_NUMBER_OF_SEARCHES = "NUMBER_OF_SEARCHES";
    /** The token for finding the names of the available searches. */
    protected static final String TOKEN_SEARCH = "SEARCH";
    /** The token for finding the character that represents the starting location in the map. */
    protected static final String TOKEN_CHAR_START = "CHAR_START";
    /** The token for finding the character that represents the goal location in the map. */
    protected static final String TOKEN_CHAR_GOAL = "CHAR_GOAL";
    /** The token for finding the character that represents a wall in the map. */
    protected static final String TOKEN_CHAR_WALL = "CHAR_WALL";
    /** The token for finding the character that represents a blank node in the map. */
    protected static final String TOKEN_CHAR_SPACE = "CHAR_SPACE";
    /** The token for finding the character that represents the northwestern boundary of the map. */
    protected static final String TOKEN_CHAR_NORTHWESTERN_BOUNDARY = "CHAR_NORTHWESTERN_BOUNDARY";
    /** The token for finding the character that represents the northern boundary of the map. */
    protected static final String TOKEN_CHAR_NORTHERN_BOUNDARY = "CHAR_NORTHERN_BOUNDARY";
    /** The token for finding the character that represents the northeastern boundary of the map. */
    protected static final String TOKEN_CHAR_NORTHEASTERN_BOUNDARY = "CHAR_NORTHEASTERN_BOUNDARY";
    /** The token for finding the character that represents the western boundary of the map. */
    protected static final String TOKEN_CHAR_WESTERN_BOUNDARY = "CHAR_WESTERN_BOUNDARY";
    /** The token for finding the character that represents the eastern boundary of the map. */
    protected static final String TOKEN_CHAR_EASTERN_BOUNDARY = "CHAR_EASTERN_BOUNDARY";
    /** The token for finding the character that represents the southwestern boundary of the map. */
    protected static final String TOKEN_CHAR_SOUTHWESTERN_BOUNDARY = "CHAR_SOUTHWESTERN_BOUNDARY";
    /** The token for finding the character that represents the southern boundary of the map. */
    protected static final String TOKEN_CHAR_SOUTHERN_BOUNDARY = "CHAR_SOUTHERN_BOUNDARY";
    /** The token for finding the character that represents the southeastern boundary of the map. */
    protected static final String TOKEN_CHAR_SOUTHEASTERN_BOUNDARY = "CHAR_SOUTHEASTERN_BOUNDARY";
    /** The token for finding the color to use for an unviewed, unevaluated, untraveled node. */
    protected static final String TOKEN_COLOR_DEFAULT = "COLOR_DEFAULT";
    /** The token for finding the color to use for a viewed node. */
    protected static final String TOKEN_COLOR_VIEWED = "COLOR_VIEWED";
    /** The token for finding the color to use for an evaluated node. */
    protected static final String TOKEN_COLOR_EVALUATED = "COLOR_EVALUATED";
    /** The token for finding the color to use for an unseen node in the agent map. */
    protected static final String TOKEN_COLOR_UNSEEN = "COLOR_UNSEEN";
    /** The token for finding the color to use for a traveled node. */
    protected static final String TOKEN_COLOR_TRAVELED = "COLOR_TRAVELED";
    /** The token for finding the color to use for a node currently being evaluated by the search. */
    protected static final String TOKEN_COLOR_SEARCH = "COLOR_SEARCH";
    /** The token for finding the image to use for an unviewed, unevaluated, untraveled node. */
    protected static final String TOKEN_IMAGE_DEFAULT = "IMAGE_DEFAULT";
    /** The token for finding the image to use for a viewed node. */
    protected static final String TOKEN_IMAGE_VIEWED = "IMAGE_VIEWED";
    /** The token for finding the image to use for an evaluated node. */
    protected static final String TOKEN_IMAGE_EVALUATED = "IMAGE_EVALUATED";
    /** The token for finding the image to use for an unseen node in the agent map. */
    protected static final String TOKEN_IMAGE_UNSEEN = "IMAGE_UNSEEN";
    /** The token for finding the image to use for a traveled node. */
    protected static final String TOKEN_IMAGE_TRAVELED = "IMAGE_TRAVELED";
    /** The token for finding the image to use for the bot heading north. */
    protected static final String TOKEN_IMAGE_BOT_NORTH = "IMAGE_BOT_NORTH";
    /** The token for finding the image to use for the bot heading east. */
    protected static final String TOKEN_IMAGE_BOT_EAST = "IMAGE_BOT_EAST";
    /** The token for finding the image to use for the bot heading south. */
    protected static final String TOKEN_IMAGE_BOT_SOUTH = "IMAGE_BOT_SOUTH";
    /** The token for finding the image to use for the bot heading west. */
    protected static final String TOKEN_IMAGE_BOT_WEST = "IMAGE_BOT_WEST";
    /** The token for finding the image to use for a node currently being evaluated by the search. */
    protected static final String TOKEN_IMAGE_SEARCH = "IMAGE_SEARCH";
    /** The token for finding the image to use for the starting node. */
    protected static final String TOKEN_IMAGE_START = "IMAGE_START";
    /** The token for finding the image to use for the goal node. */
    protected static final String TOKEN_IMAGE_GOAL = "IMAGE_GOAL";
    /** The token for finding the image to use for a wall node. */
    protected static final String TOKEN_IMAGE_WALL = "IMAGE_WALL";
    /** The token for finding the image to use for a blank node. */
    protected static final String TOKEN_IMAGE_SPACE = "IMAGE_SPACE";
    /** The token for finding the image to use for a blank node after being traveled through. */
    protected static final String TOKEN_IMAGE_AFTER_SPACE = "IMAGE_AFTER_SPACE";
    /** The token for finding the image to use for a node with additional movement cost. */
    protected static final String TOKEN_IMAGE_COST = "IMAGE_COST";
    /** The token for finding the image to use for a node with additional movement cost after being traveled through. */
    protected static final String TOKEN_IMAGE_AFTER_COST = "IMAGE_AFTER_COST";
    /** The token for finding the image to use for a northwestern boundary node. */
    protected static final String TOKEN_IMAGE_NORTHWESTERN_BOUNDARY = "IMAGE_NORTHWESTERN_BOUNDARY";
    /** The token for finding the image to use for a northern boundary node. */
    protected static final String TOKEN_IMAGE_NORTHERN_BOUNDARY = "IMAGE_NORTHERN_BOUNDARY";
    /** The token for finding the image to use for a northeastern boundary node. */
    protected static final String TOKEN_IMAGE_NORTHEASTERN_BOUNDARY = "IMAGE_NORTHEASTERN_BOUNDARY";
    /** The token for finding the image to use for a western boundary node. */
    protected static final String TOKEN_IMAGE_WESTERN_BOUNDARY = "IMAGE_WESTERN_BOUNDARY";
    /** The token for finding the image to use for a eastern boundary node. */
    protected static final String TOKEN_IMAGE_EASTERN_BOUNDARY = "IMAGE_EASTERN_BOUNDARY";
    /** The token for finding the image to use for a southwestern boundary node. */
    protected static final String TOKEN_IMAGE_SOUTHWESTERN_BOUNDARY = "IMAGE_SOUTHWESTERN_BOUNDARY";
    /** The token for finding the image to use for a southern boundary node. */
    protected static final String TOKEN_IMAGE_SOUTHERN_BOUNDARY = "IMAGE_SOUTHERN_BOUNDARY";
    /** The token for finding the image to use for a southeastern boundary node. */
    protected static final String TOKEN_IMAGE_SOUTHEASTERN_BOUNDARY = "IMAGE_SOUTHEASTERN_BOUNDARY";

    /** The width of a {@link BotEnvironment.SearchBot.Tile Tile}. */
    protected static int TILE_WIDTH;
    /** The height of a {@link BotEnvironment.SearchBot.Tile Tile}. */
    protected static int TILE_HEIGHT;
    /** The maximum additional cost a node can have. */
    public static int MAXIMUM_COST;

    /** The cost for the search to evaluate a node. */
    public static int SEARCH_COST;
    /** The cost for the bot to turn at a right angle. */
    public static int TURN_COST;
    /** The cost for the bot to move to an adjacent node. */
    public static int MOVE_COST;

    /** The number of searches available. */
    public static int NUMBER_OF_SEARCHES;
    /** The collection of searches available. */
    public static String[] SEARCHES;
    /** The current search being used. */
    public static String SEARCH;

    /** The character that represents the starting location in the map. */
    public static String CHAR_START;
    /** The character that represents the goal location in the map. */
    public static String CHAR_GOAL;
    /** The character that represents a wall in the map. */
    public static String CHAR_WALL;
    /** The character that represents a blank node in the map. */
    public static String CHAR_SPACE;
    /** The character that represents a northwestern boundary node. */
    protected static String CHAR_NORTHWESTERN_BOUNDARY;
    /** The character that represents a northern boundary node. */
    protected static String CHAR_NORTHERN_BOUNDARY;
    /** The character that represents a northeastern boundary node. */
    protected static String CHAR_NORTHEASTERN_BOUNDARY;
    /** The character that represents a western boundary node. */
    protected static String CHAR_WESTERN_BOUNDARY;
    /** The character that represents a eastern boundary node. */
    protected static String CHAR_EASTERN_BOUNDARY;
    /** The character that represents a southwestern boundary node. */
    protected static String CHAR_SOUTHWESTERN_BOUNDARY;
    /** The character that represents a southern boundary node. */
    protected static String CHAR_SOUTHERN_BOUNDARY;
    /** The character that represents a southeastern boundary node. */
    protected static String CHAR_SOUTHEASTERN_BOUNDARY;

    /** The color to use for an unviewed, unevaluated, untraveled node. */
    protected static Color COLOR_DEFAULT;
    /** The color to use for a viewed node. */
    protected static Color COLOR_VIEWED;
    /** The color to use for an evaluated node. */
    protected static Color COLOR_EVALUATED;
    /** The color to use for an unseen node in the agent map. */
    protected static Color COLOR_UNSEEN;
    /** The color to use for a traveled node. */
    protected static Color COLOR_TRAVELED;
    /** The color to use for a node currently being evaluated by the search. */
    protected static Color COLOR_SEARCH;

    /** The image to use for an unviewed, unevaluated, untraveled node. */
    protected static Image IMAGE_DEFAULT;
    /** The image to use for a viewed node. */
    protected static Image IMAGE_VIEWED;
    /** The image to use for an evaluated node. */
    protected static Image IMAGE_EVALUATED;
    /** The image to use for an unseen node in the agent map. */
    protected static Image IMAGE_UNSEEN;
    /** The image to use for a traveled node. */
    protected static Image IMAGE_TRAVELED;
    /** The image to use for the bot heading north. */
    protected static Image IMAGE_BOT_NORTH;
    /** The image to use for the bot heading east. */
    protected static Image IMAGE_BOT_EAST;
    /** The image to use for the bot heading south. */
    protected static Image IMAGE_BOT_SOUTH;
    /** The image to use for the bot heading west. */
    protected static Image IMAGE_BOT_WEST;
    /** The image to use for a node currently being evaluated by the search. */
    protected static Image IMAGE_SEARCH;
    /** The image to use for the starting node. */
    protected static Image IMAGE_START;
    /** The image to use for the goal node. */
    protected static Image IMAGE_GOAL;
    /** The image to use for the wall node. */
    protected static Image IMAGE_WALL;
    /** The image to use for a blank node. */
    protected static Image IMAGE_SPACE;
    /** The image to use for a blank node after being traveled through. */
    protected static Image IMAGE_AFTER_SPACE;
    /** The image to use for a node with additional movement cost. */
    protected static Image[] IMAGE_COST;
    /** The image to use for a node with additional movement cost after being traveled through. */
    protected static Image[] IMAGE_AFTER_COST;
    /** The image to use for a northwestern boundary node. */
    protected static Image IMAGE_NORTHWESTERN_BOUNDARY;
    /** The image to use for a northern boundary node. */
    protected static Image IMAGE_NORTHERN_BOUNDARY;
    /** The image to use for a northeastern boundary node. */
    protected static Image IMAGE_NORTHEASTERN_BOUNDARY;
    /** The image to use for a western boundary node. */
    protected static Image IMAGE_WESTERN_BOUNDARY;
    /** The image to use for a eastern boundary node. */
    protected static Image IMAGE_EASTERN_BOUNDARY;
    /** The image to use for a southwestern boundary node. */
    protected static Image IMAGE_SOUTHWESTERN_BOUNDARY;
    /** The image to use for a southern boundary node. */
    protected static Image IMAGE_SOUTHERN_BOUNDARY;
    /** The image to use for a southeastern boundary node. */
    protected static Image IMAGE_SOUTHEASTERN_BOUNDARY;

    /** A value representing 'null' for integers. */
    public static final int NULL = -1;
    /** A value representing infinity for integers. */
    public static final int INFINITE = -1;

    /** A value representing north. */
    public static final int NORTH = 0;
    /** A value representing east. */
    public static final int EAST = 1;
    /** A value representing south. */
    public static final int SOUTH = 2;
    /** A value representing west. */
    public static final int WEST = 3;
    /** The default starting heading for the bot. */
    public static final int BOT_HEADING_DEFAULT = EAST;

    /** The property name for a PropertyChangeEvent in which a node becomes viewed. */
    protected static final String VIEWED = "Viewed";
    /** The property name for a PropertyChangeEvent in which a node becomes evaluated. */
    protected static final String EVALUATED = "Evaluated";
    /** The property name for a PropertyChangeEvent in which a node becomes traveled. */
    protected static final String TRAVELED = "Traveled";
    /** The property name for a PropertyChangeEvent in which the bot occupies a node. */
    protected static final String BOT_IS_HERE = "Bot is here";
    /** The property name for a PropertyChangeEvent in which the search is evaluating a node. */
    protected static final String SEARCH_IS_HERE = "Search is here";
    /** The property name for a PropertyChangeEvent in which the bot turns on a node. */
    protected static final String BOT_HAS_TURNED = "Bot has turned";
    /** The property name for a PropertyChangeEvent in which the bot has made a log. */
    protected static final String BOT_LOGGED = "Bot logged";
    /** The property name for a PropertyChangeEvent in which a node is reset. */
    protected static final String RESET = "Reset";

    /** The delay in milliseconds between search and movement steps. */
    public static int DELAY = 100;
    /** The minimum delay in milliseconds. */
    public static final int DELAY_MIN = 0;
    /** The maximum delay in milliseconds. */
    public static final int DELAY_MAX = 1000;
    /** The starting delay in milliseconds. */
    public static final int DELAY_INIT = 100;
    /** The major ticks for the delay JSlider. */
    public static final int DELAY_MAJOR_TICK = 100;
    /** The minor ticks for the delay JSlider. */
    public static final int DELAY_MINOR_TICK = 50;

    /** The width of the legend frame in pixels. */
    protected static final int LEGEND_WIDTH = 100;

    /**
     * Reads the information from the property file and assigns the values
     * found there to the non-final static variables.
     */
    static {

        int searchIndex = 0;

        try {

            FileReader fileReader = new FileReader(SBCONSTANTS_PROPERTIES);
            BufferedReader inFile = new BufferedReader(fileReader);
            String line = inFile.readLine();
            StringTokenizer tokenizer;

            while (line != null) {

                tokenizer = new StringTokenizer(line);

                if (tokenizer.hasMoreTokens()) {

                    String temp = tokenizer.nextToken();

                    if (temp.equals(TOKEN_TILE_WIDTH)) {

                        TILE_WIDTH = Integer.parseInt(tokenizer.nextToken());
                    }
                    else if (temp.equals(TOKEN_TILE_HEIGHT)) {

                        TILE_HEIGHT = Integer.parseInt(tokenizer.nextToken());
                    }
                    else if (temp.equals(TOKEN_MAXIMUM_COST)) {

                        MAXIMUM_COST = Integer.parseInt(tokenizer.nextToken());
                        IMAGE_COST = new Image[MAXIMUM_COST];
                        IMAGE_AFTER_COST = new Image[MAXIMUM_COST];
                    }
                    else if (temp.equals(TOKEN_SEARCH_COST)) {

                        SEARCH_COST = Integer.parseInt(tokenizer.nextToken());
                    }
                    else if (temp.equals(TOKEN_TURN_COST)) {

                        TURN_COST = Integer.parseInt(tokenizer.nextToken());
                    }
                    else if (temp.equals(TOKEN_MOVE_COST)) {

                        MOVE_COST = Integer.parseInt(tokenizer.nextToken());
                    }
                    else if (temp.equals(TOKEN_NUMBER_OF_SEARCHES)) {

                        NUMBER_OF_SEARCHES = Integer.parseInt(tokenizer.nextToken());
                        SEARCHES = new String[NUMBER_OF_SEARCHES];
                    }
                    else if (temp.equals(TOKEN_SEARCH)) {

                        SEARCHES[searchIndex] = tokenizer.nextToken();

                        if (searchIndex == 0) {

                            SEARCH = SEARCHES[searchIndex];
                        }

                        searchIndex++;
                    }
                    else if (temp.equals(TOKEN_CHAR_START)) {

                        CHAR_START = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_GOAL)) {

                        CHAR_GOAL = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_WALL)) {

                        CHAR_WALL = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_SPACE)) {

                        CHAR_SPACE = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_NORTHEASTERN_BOUNDARY)) {

                        CHAR_NORTHEASTERN_BOUNDARY = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_NORTHERN_BOUNDARY)) {

                        CHAR_NORTHERN_BOUNDARY = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_NORTHWESTERN_BOUNDARY)) {

                        CHAR_NORTHWESTERN_BOUNDARY = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_WESTERN_BOUNDARY)) {

                        CHAR_WESTERN_BOUNDARY = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_EASTERN_BOUNDARY)) {

                        CHAR_EASTERN_BOUNDARY = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_SOUTHWESTERN_BOUNDARY)) {

                        CHAR_SOUTHWESTERN_BOUNDARY = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_SOUTHERN_BOUNDARY)) {

                        CHAR_SOUTHERN_BOUNDARY = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_CHAR_SOUTHEASTERN_BOUNDARY)) {

                        CHAR_SOUTHEASTERN_BOUNDARY = tokenizer.nextToken();
                    }
                    else if (temp.equals(TOKEN_COLOR_DEFAULT)) {

                        int red = Integer.parseInt(tokenizer.nextToken());
                        int green = Integer.parseInt(tokenizer.nextToken());
                        int blue = Integer.parseInt(tokenizer.nextToken());

                        COLOR_DEFAULT = new Color(red, green, blue);
                    }
                    else if (temp.equals(TOKEN_COLOR_VIEWED)) {

                        int red = Integer.parseInt(tokenizer.nextToken());
                        int green = Integer.parseInt(tokenizer.nextToken());
                        int blue = Integer.parseInt(tokenizer.nextToken());

                        COLOR_VIEWED = new Color(red, green, blue);
                    }
                    else if (temp.equals(TOKEN_COLOR_EVALUATED)) {

                        int red = Integer.parseInt(tokenizer.nextToken());
                        int green = Integer.parseInt(tokenizer.nextToken());
                        int blue = Integer.parseInt(tokenizer.nextToken());

                        COLOR_EVALUATED = new Color(red, green, blue);
                    }
                    else if (temp.equals(TOKEN_COLOR_UNSEEN)) {

                        int red = Integer.parseInt(tokenizer.nextToken());
                        int green = Integer.parseInt(tokenizer.nextToken());
                        int blue = Integer.parseInt(tokenizer.nextToken());

                        COLOR_UNSEEN = new Color(red, green, blue);
                    }
                    else if (temp.equals(TOKEN_COLOR_TRAVELED)) {

                        int red = Integer.parseInt(tokenizer.nextToken());
                        int green = Integer.parseInt(tokenizer.nextToken());
                        int blue = Integer.parseInt(tokenizer.nextToken());

                        COLOR_TRAVELED = new Color(red, green, blue);
                    }
                    else if (temp.equals(TOKEN_COLOR_SEARCH)) {

                        int red = Integer.parseInt(tokenizer.nextToken());
                        int green = Integer.parseInt(tokenizer.nextToken());
                        int blue = Integer.parseInt(tokenizer.nextToken());

                        COLOR_SEARCH = new Color(red, green, blue);
                    }
                    else if (temp.equals(TOKEN_IMAGE_DEFAULT)) {

                        IMAGE_DEFAULT = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_VIEWED)) {

                        IMAGE_VIEWED = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_EVALUATED)) {

                        IMAGE_EVALUATED = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_UNSEEN)) {

                        IMAGE_UNSEEN = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_TRAVELED)) {

                        IMAGE_TRAVELED = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_BOT_NORTH)) {

                        IMAGE_BOT_NORTH = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_BOT_EAST)) {

                        IMAGE_BOT_EAST = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_BOT_SOUTH)) {

                        IMAGE_BOT_SOUTH = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_BOT_WEST)) {

                        IMAGE_BOT_WEST = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_SEARCH)) {

                        IMAGE_SEARCH = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_START)) {

                        IMAGE_START = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_GOAL)) {

                        IMAGE_GOAL = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_WALL)) {

                        IMAGE_WALL = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_SPACE)) {

                        IMAGE_SPACE = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_AFTER_SPACE)) {

                        IMAGE_AFTER_SPACE = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_COST)) {

                        int cost = Integer.parseInt(tokenizer.nextToken());
                        IMAGE_COST[cost - 1] = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_AFTER_COST)) {

                        int cost = Integer.parseInt(tokenizer.nextToken());
                        IMAGE_AFTER_COST[cost - 1] = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_NORTHWESTERN_BOUNDARY)) {

                        IMAGE_NORTHWESTERN_BOUNDARY = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_NORTHERN_BOUNDARY)) {

                        IMAGE_NORTHERN_BOUNDARY = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_NORTHEASTERN_BOUNDARY)) {

                        IMAGE_NORTHEASTERN_BOUNDARY = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_WESTERN_BOUNDARY)) {

                        IMAGE_WESTERN_BOUNDARY = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_EASTERN_BOUNDARY)) {

                        IMAGE_EASTERN_BOUNDARY = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_SOUTHWESTERN_BOUNDARY)) {

                        IMAGE_SOUTHWESTERN_BOUNDARY = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_SOUTHERN_BOUNDARY)) {

                        IMAGE_SOUTHERN_BOUNDARY = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                    else if (temp.equals(TOKEN_IMAGE_SOUTHEASTERN_BOUNDARY)) {

                        IMAGE_SOUTHEASTERN_BOUNDARY = ImageIO.read(new File(tokenizer.nextToken()));
                    }
                }

                line = inFile.readLine();
            }
        }
        catch (FileNotFoundException e) {System.out.println(e);}
        catch (IOException e) {System.out.println(e);}
    }
}