/**
 * Reagan Williams
 * reagan.williams@gmail.com
 * 70852519
 */

package net.cs76.projects.nPuzzle70852519;

import android.graphics.Bitmap;

/**
 * Comments here!
 * 
 * @author reagan
 *
 */
public class GameTile {
    Bitmap bitmap = null;
    int tilePosition = 0;
    boolean blankTile = false;
    
    public GameTile(Bitmap b, int pos, boolean blank) {
        bitmap = b;
        tilePosition = pos;
        blankTile = blank;
    }
    
    // comments
    public Bitmap getBitmap() {
        return bitmap;
    }
    
    // comments
    public int getTilePosition() {
        return tilePosition;
    }
    
    public boolean isBlankTile() {
        return blankTile;
    }
}
