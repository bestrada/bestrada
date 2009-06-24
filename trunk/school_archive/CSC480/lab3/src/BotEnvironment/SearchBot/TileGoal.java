package BotEnvironment.SearchBot;

import java.beans.*;

/**
 * TileGoal is an extension of Tile, representing
 * the goal node in the map.
 *
 * @author Matt Colón
 */

public class TileGoal extends Tile
{
    /**
     * Creates a new tile representing the goal node.
     *
     * @param node The node that the tile will represent.
     * @param showUnseen Whether or not to show the tile if is has not been viewed.
     */
    protected TileGoal (Node node, boolean showUnseen) {

        super(node, showUnseen);
        setImage(SBConstants.IMAGE_GOAL);
    }

    /**
     * Evaluates PropertyChangeEvents and updates the tile accordingly.
     *
     * @param e The PropertyChangeEvent to evaluate.
     */
    public void propertyChange (PropertyChangeEvent e) {

        String change = e.getPropertyName();

        if (change.equals(SBConstants.EVALUATED)) {

            setBackground(SBConstants.COLOR_EVALUATED);
            setBackgroundImage(SBConstants.IMAGE_EVALUATED);
        }
        else if (change.equals(SBConstants.TRAVELED)) {

            setBackground(SBConstants.COLOR_TRAVELED);
            setBackgroundImage(SBConstants.IMAGE_TRAVELED);
        }
        else if (change.equals(SBConstants.RESET)) {

            setBackground(SBConstants.COLOR_DEFAULT);
            setBackgroundImage(SBConstants.IMAGE_DEFAULT);
        }

        repaint();
    }
}