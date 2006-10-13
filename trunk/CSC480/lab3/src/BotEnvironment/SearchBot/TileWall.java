package BotEnvironment.SearchBot;

import java.beans.*;

/**
 * TileWall is an extension of Tile, representing
 * a wall node in a map.
 *
 * @author Matt Colón
 */

public class TileWall extends Tile
{
    /**
     * Creates a new tile representing a wall node.
     *
     * @param node The node that the tile will represent.
     * @param showUnseen Whether or not to show the tile if is has not been viewed.
     */
    protected TileWall (Node node, boolean showUnseen) {

        super(node, showUnseen);
        setImage(SBConstants.IMAGE_WALL);
    }

    /**
     * Evaluates PropertyChangeEvents and updates the tile accordingly.
     *
     * @param e The PropertyChangeEvent to evaluate.
     */
    public void propertyChange (PropertyChangeEvent e) {

        super.propertyChange(e);
        String change = e.getPropertyName();

        if (change.equals(SBConstants.RESET)) {

            setBackground(SBConstants.COLOR_DEFAULT);
            setBackgroundImage(SBConstants.IMAGE_DEFAULT);
        }
    }
}