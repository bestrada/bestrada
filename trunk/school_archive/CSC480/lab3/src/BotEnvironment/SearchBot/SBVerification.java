package BotEnvironment.SearchBot;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import javax.swing.*;

public class SBVerification
{
    public static final String PACKAGE = "Agents.";
    public static final String SBM = "sbm";
    public static JFrame THIS;

    public static Bot verifyBotFile (File botFile) {

        Bot bot = null;
        String botFileName = botFile.getName();
        String parentDirectory = botFile.getParentFile().getName();

        int index = botFileName.lastIndexOf('.');
        String botClassName = botFileName.substring(0, index);

        Object object = null;

        try {

            Class botDefinition = null;
            if (parentDirectory.equals("Agents"))
            {
                botDefinition = Class.forName(PACKAGE + botClassName);
            }
            else
            {
                botDefinition = Class.forName(PACKAGE + parentDirectory + "." + botClassName);
            }
            object = botDefinition.newInstance();

            bot = (Bot) object;
        }
        catch (InstantiationException e) {

            JOptionPane.showMessageDialog(THIS,
                "The chosen class must not be an interface or be abstract, " +
                "and it must not have any arguments in its constructor.");
        }
        catch (IllegalAccessException e) {

            JOptionPane.showMessageDialog(THIS,
                "The chosen class's constructor must be public.");
        }
        catch (ClassNotFoundException e) {

            JOptionPane.showMessageDialog(THIS,
                "The chosen class cannot be found.  Make sure the .java file is compiled.");
        }
        catch (ClassCastException e) {

            JOptionPane.showMessageDialog(THIS,
                "The chosen class does not extend from the Bot class.");
        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(THIS,
                "The chosen class does not conform to the \"Bot\" class.");
        }

        return bot;
    }

    public static File verifyMapFile (File mapFile) {

        String mapFileName = mapFile.getName();
        int index = mapFileName.lastIndexOf('.');
        String mapExtension = mapFileName.substring(index + 1);

        try {

            if (!mapExtension.equals(SBM)) {

                throw new MapExtensionException(SBM);
            }
        }
        catch (MapExtensionException e) {

            JOptionPane.showMessageDialog(THIS, e.getMessage());
            return null;
        }

        int mapSizeX = -1;
        int mapSizeY = -1;

        int startingLocations = 0;
        int goalLocations = 0;

        BufferedReader inFile = null;
        String line = null;

        try {

            FileReader fileReader = new FileReader(mapFile);
            inFile = new BufferedReader(fileReader);
        }
        catch (IOException e) {

            JOptionPane.showMessageDialog(THIS,
                "Map file could not be found.");
            return null;
        }

        try {

            line = inFile.readLine();
            StringTokenizer tokenizer = new StringTokenizer(line);

            if (line != null) {

                String mapSizeXString = tokenizer.nextToken();
                String mapSizeYString = tokenizer.nextToken();
                mapSizeX = Integer.parseInt(mapSizeXString);
                mapSizeY = Integer.parseInt(mapSizeYString);

                if (mapSizeX <= 1) {

                    throw new IllegalMapSizeException("Map Size X: " + mapSizeX);
                }
                else if (mapSizeY <= 1) {

                    throw new IllegalMapSizeException("Map Size Y: " + mapSizeY);
                }
            }
        }
        catch (IOException e) {

            JOptionPane.showMessageDialog(THIS,
                "Map file is not able to be read.");
            return null;
        }
        catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(THIS,
                "Non-integer map size.  Both the horizontal and vertical size should " +
                "be an integer value greater than 1.");
            return null;
        }
        catch (IllegalMapSizeException e) {

            JOptionPane.showMessageDialog(THIS, e.getMessage());
            return null;
        }

        int x = 0;
        int y = 0;
        String symbol = null;

        try {

            for (y = 0; y < mapSizeY; y++) {

                line = inFile.readLine();

                if (line == null) {

                    throw new InvalidVerticalMapSizeException();
                }

                char[] mapSymbols = line.toCharArray();

                if (mapSymbols.length < mapSizeX || mapSymbols.length > mapSizeX) {
System.out.println("Map Symbols length: " + mapSymbols.length + "mapSizeX: " + mapSizeX);
                    throw new InvalidHorizontalMapSizeException();
                }

                for (x = 0; x < mapSizeX; x++) {

                    symbol = Character.toString(mapSymbols[x]);

                    if (symbol.equals(SBConstants.CHAR_START)) {

                        startingLocations++;
                        if (startingLocations > 1) {

                            throw new TooManyStartingLocationsException();
                        }
                    }
                    else if (symbol.equals(SBConstants.CHAR_GOAL)) {

                        goalLocations++;
                        if (goalLocations > 1) {

                            throw new TooManyGoalLocationsException();
                        }
                    }
                    else if (!symbol.equals(SBConstants.CHAR_WALL) &&
                             !symbol.equals(SBConstants.CHAR_SPACE)) {

                        int cost = Integer.parseInt(symbol);

                        if (cost > SBConstants.MAXIMUM_COST || cost == 0) {

                            throw new IllegalCostException(
                                "Cost at (" + x + "," + y + ") is " + cost + ".\r\n" +
                                "Maximum cost allowed is " + SBConstants.MAXIMUM_COST);
                        }
                    }
                }
            }

            line = inFile.readLine();

            if (line != null) {

                char[] check = line.toCharArray();

                for (int i = 0; i < check.length; i++) {

                    if (!Character.isWhitespace(check[i])) {

                        throw new InvalidVerticalMapSizeException();
                    }
                }
            }

            if (startingLocations == 0) {

                throw new NoStartingLocationException();
            }
            if (goalLocations == 0) {

                throw new NoGoalLocationException();
            }
        }
        catch (IOException e) {

            JOptionPane.showMessageDialog(THIS,
                "Map file is not able to be read.");
            return null;
        }
        catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(THIS,
                "Invalid map symbol '" + symbol + "' at (" + x + "," + y + "). "+
                "Valid map symbols are the following:\r\n\r\n" +
                "Starting Location: " + SBConstants.CHAR_START + "\r\n" +
                "Goal Location: " + SBConstants.CHAR_GOAL + "\r\n" +
                "Wall Location: " + SBConstants.CHAR_WALL + "\r\n" +
                "Blank Space: " + SBConstants.CHAR_SPACE + "\r\n" +
                "Additional Cost Space: Any integer between 1 and " + SBConstants.MAXIMUM_COST + ".");
            return null;
        }
        catch (InvalidHorizontalMapSizeException e) {

            JOptionPane.showMessageDialog(THIS,
                e.getMessage() + "\r\n" +
                "Row " + y + " in the map is not the correct width.\r\n" +
                "The horizontal map size should be " + mapSizeX + ".");
            return null;
        }
        catch (InvalidVerticalMapSizeException e) {

            JOptionPane.showMessageDialog(THIS,
                e.getMessage() + "\r\n" +
                "The map is not the correct height.\r\n" +
                "The vertical map size should be " + mapSizeY + ".");
            return null;
        }
        catch (IllegalCostException e) {

            JOptionPane.showMessageDialog(THIS, e.getMessage());
            return null;
        }
        catch (TooManyStartingLocationsException e) {

            JOptionPane.showMessageDialog(THIS, e.getMessage());
            return null;
        }
        catch (TooManyGoalLocationsException e) {

            JOptionPane.showMessageDialog(THIS, e.getMessage());
            return null;
        }
        catch (NoStartingLocationException e) {

            JOptionPane.showMessageDialog(THIS, e.getMessage());
            return null;
        }
        catch (NoGoalLocationException e) {

            JOptionPane.showMessageDialog(THIS, e.getMessage());
            return null;
        }

        return mapFile;
    }
}