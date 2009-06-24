package BotEnvironment.SearchBot;

import java.beans.*;

/**
 * TileWesternBoundary is an extension of Tile, representing
 * the western boundary of the map.
 *
 * @author Matt Colón
 */

public class TileWesternBoundary extends Tile
{
    /**
     * Creates a new tile representing the western boundary of the map.
     *
     * @param node The node that the tile will represent.
     * @param showUnseen Whether or not to show the tile if is has not been viewed.
     */
    protected TileWesternBoundary (Node node, boolean showUnseen) {

        super(node, showUnseen);
        setImage(SBConstants.IMAGE_WESTERN_BOUNDARY);
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