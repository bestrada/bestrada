package BotEnvironment.SearchBot;

import java.beans.*;

/**
 * TileSouthernBoundary is an extension of Tile, representing
 * the southern boundary of the map.
 *
 * @author Matt Colón
 */

public class TileSouthernBoundary extends Tile
{
    /**
     * Creates a new tile representing the southern boundary of the map.
     *
     * @param node The node that the tile will represent.
     * @param showUnseen Whether or not to show the tile if is has not been viewed.
     */
    protected TileSouthernBoundary (Node node, boolean showUnseen) {

        super(node, showUnseen);
        setImage(SBConstants.IMAGE_SOUTHERN_BOUNDARY);
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