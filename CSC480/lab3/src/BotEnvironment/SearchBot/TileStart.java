package BotEnvironment.SearchBot;

import java.beans.*;

/**
 * TileStart is an extension of Tile, representing
 * the starting node in the map.
 *
 * @author Matt Colón
 */

public class TileStart extends Tile
{
    /**
     * Creates a new tile representing the starting node.
     *
     * @param node The node that the tile will represent.
     * @param showUnseen Whether or not to show the tile if is has not been viewed.
     */
    protected TileStart (Node node, boolean showUnseen) {

        super(node, showUnseen);
        setBackgroundImage(SBConstants.IMAGE_START);
    }

    /**
     * Evaluates PropertyChangeEvents and updates the tile accordingly.
     *
     * @param e The PropertyChangeEvent to evaluate.
     */
    public void propertyChange (PropertyChangeEvent e) {

        super.propertyChange(e);
        String change = e.getPropertyName();

        if (change.equals(SBConstants.TRAVELED)) {

            setBackground(SBConstants.COLOR_TRAVELED);
        }
        else if (change.equals(SBConstants.BOT_IS_HERE)) {

            boolean botIsHere = ((Boolean) e.getNewValue()).booleanValue();

            if (botIsHere) {

                if (Bot.HEADING == SBConstants.NORTH) {

                    setImage(SBConstants.IMAGE_BOT_NORTH);
                }
                else if (Bot.HEADING == SBConstants.EAST) {

                    setImage(SBConstants.IMAGE_BOT_EAST);
                }
                else if (Bot.HEADING == SBConstants.SOUTH) {

                    setImage(SBConstants.IMAGE_BOT_SOUTH);
                }
                else if (Bot.HEADING == SBConstants.WEST) {

                    setImage(SBConstants.IMAGE_BOT_WEST);
                }
            }
            else {

                setImage(SBConstants.IMAGE_AFTER_SPACE);
            }
        }
        else if (change.equals(SBConstants.BOT_HAS_TURNED)) {

            if (Bot.HEADING == SBConstants.NORTH) {

                setImage(SBConstants.IMAGE_BOT_NORTH);
            }
            else if (Bot.HEADING == SBConstants.EAST) {

                setImage(SBConstants.IMAGE_BOT_EAST);
            }
            else if (Bot.HEADING == SBConstants.SOUTH) {

                setImage(SBConstants.IMAGE_BOT_SOUTH);
            }
            else if (Bot.HEADING == SBConstants.WEST) {

                setImage(SBConstants.IMAGE_BOT_WEST);
            }
        }
        else if (change.equals(SBConstants.RESET)) {

            setBackground(SBConstants.COLOR_DEFAULT);
            setBackgroundImage(SBConstants.IMAGE_START);
        }

        repaint();
    }
}