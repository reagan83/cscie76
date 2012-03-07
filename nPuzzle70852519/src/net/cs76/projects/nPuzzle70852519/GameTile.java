/**
 * Reagan Williams
 * reagan.williams@gmail.com
 * 70852519
 */

package net.cs76.projects.nPuzzle70852519;

import android.graphics.Bitmap;

/**
 * GameTile Class
 * 
 * This class stores tile specific information such as the image, position and
 * if it is a blank tile.
 * 
 * @author rwilliams
 * 
 */
public class GameTile {
    private Bitmap bitmap;
    private int tilePosition;

    private final boolean blankTile;

    /**
     * GameTile Constructor that configures the class variables.
     * 
     * @param b
     * @param pos
     * @param blank
     */
    public GameTile(int pos, boolean blank) {
        tilePosition = pos;
        blankTile = blank;
    }

    /**
     * Get method for returning the object's Bitmap.
     * 
     * @return bitmap
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Set method for updating the object's Bitmap.
     * 
     */
    public void setBitmap(Bitmap b) {
        bitmap = b;
    }

    /**
     * Get method for returning the object's position.
     * 
     * @return tilePosition
     */
    public int getPosition() {
        return tilePosition;
    }

    /**
     * Set method for updating the object's position.
     * 
     * @param p
     */
    public void setPosition(int p) {
        tilePosition = p;
    }

    /**
     * Test method for determining if a tile is blank or not.
     * 
     * @return blankTile
     */
    public boolean isBlankTile() {
        return blankTile;
    }
}
