package BotEnvironment.SearchBot;

import java.awt.*;
import java.beans.*;
import javax.swing.*;

/**
 * Tile is the abstract base class for all tiles,
 * containing the basic information for graphically
 * representing a node.  A Tile object is dynamically
 * updated by listening to property changes in its
 * node.
 *
 * @author Matt Colón
 */

public abstract class Tile extends JPanel implements PropertyChangeListener
{
    /** The node that the tile represents. */
    private Node node;
    /** The background image to draw in the background of the tile. */
    private Image backgroundImage;
    /** The image to draw in the foreground of the tile. */
    private Image image;
    /** The image to draw in above the foreground if the search is evaluating the tile. */
    private Image searchImage;

    /**
     * True if the tile is in a {@link BotEnvironment.SearchBot.TileMap TileMap} and thus
     * the tile should be displayed regardless if it has been viewed, or
     * false if the tile is in a {@link BotEnvironment.SearchBot.TileAgentMap TileAgentMap}
     * and thus the tile should not be displayed until it has been viewed.
     */
    private boolean showUnseen;

    /**
     * Creates a new Tile object to represent a node.
     *
     * @param node The node that the tile will represent.
     * @param showUnseen Whether or not to show the tile if is has not been viewed.
     */
    protected Tile (Node node, boolean showUnseen) {

        this.node = node;
        this.showUnseen = showUnseen;
        node.addPropertyChangeListener(this);

        setBackground(SBConstants.COLOR_DEFAULT);
        setBackgroundImage(SBConstants.IMAGE_DEFAULT);
        setSearchImage(SBConstants.IMAGE_SEARCH);
        setPreferredSize(new Dimension(SBConstants.TILE_WIDTH, SBConstants.TILE_HEIGHT));
    }

    /** Returns the node that the tile is representing. */
    protected Node getNode() {

        return node;
    }

    /** Returns the background image. */
    protected Image getBackgroundImage() {

        return backgroundImage;
    }

    /** Returns the foreground image. */
    protected Image getImage() {

        return image;
    }

    /** Returns the search image. */
    protected Image getSearchImage() {

        return searchImage;
    }

    /**
     * Sets the background image to a new image.
     *
     * @param backgroundImage The new background image.
     */
    protected void setBackgroundImage (Image backgroundImage) {

        this.backgroundImage = backgroundImage;
    }

    /**
     * Sets the foreground image to a new image.
     *
     * @param image The new foreground image.
     */
    protected void setImage (Image image) {

        this.image = image;
    }

    /**
     * Sets the search image to a new image.
     *
     * @param searchImage The new search image.
     */
    protected void setSearchImage (Image searchImage) {

        this.searchImage = searchImage;
    }

    /**
     * Evaluates PropertyChangeEvents and updates the tile accordingly.
     *
     * @param e The PropertyChangeEvent to evaluate.
     */
    public void propertyChange (PropertyChangeEvent e) {

        String change = e.getPropertyName();

        if (change.equals(SBConstants.VIEWED)) {

            setBackground(SBConstants.COLOR_VIEWED);
            setBackgroundImage(SBConstants.IMAGE_VIEWED);
        }
        else if (change.equals(SBConstants.EVALUATED)) {

            setBackground(SBConstants.COLOR_EVALUATED);
            setBackgroundImage(SBConstants.IMAGE_EVALUATED);
        }
        else if (change.equals(SBConstants.SEARCH_IS_HERE)) {

            boolean searchIsHere = ((Boolean) e.getNewValue()).booleanValue();
            if (searchIsHere) {

                setBackground(SBConstants.COLOR_SEARCH);
            }
            else {

                if (node.getIsTraveled()) {

                    setBackground(SBConstants.COLOR_TRAVELED);
                }
                else {

                    setBackground(SBConstants.COLOR_EVALUATED);
                }
            }
        }

        repaint();
    }

    /**
     * Extends the paintComponent method and graphically displays
     * the tile according to the properties of the tile type and
     * the node.
     *
     * @param graphics The graphics object used in the parent paintComponent method.
     */
    public void paintComponent (Graphics graphics) {

        super.paintComponent(graphics);

        if (showUnseen || node.getIsViewed()) {

            graphics.drawImage(backgroundImage, 0, 0, null);
            graphics.drawImage(image, 0, 0, null);
            if (node.getSearchIsHere()) {

                graphics.drawImage(searchImage, 0, 0, null);
            }
        }
        else {

            setBackground(SBConstants.COLOR_UNSEEN);
            graphics.drawImage(SBConstants.IMAGE_UNSEEN, 0, 0, null);
        }
    }

    /**
     * Resets the node to being unviewed, unevaluated, untraveled,
     * and the bot and search not occupying or evaluating it.
     */
    protected void reset() {

        node.reset();
    }

    /** Returns the string representation of the node. */
    public String toString() {

        return node.toString();
    }
}