/**
 * Reagan Williams
 * reagan.williams@gmail.com
 * 70852519
 */

package net.cs76.projects.nPuzzle70852519;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class GameBoard {
    Bitmap gameboard = null;
    ArrayList<GameTile> gt = null;
    GameTile blankTile = null;
    
    public GameBoard(Bitmap b, int difficulty) {
        gameboard = b;
        gt = new ArrayList<GameTile>(difficulty);
        
        generateTiles();
        generateBlankTile();
    }
    
    public Bitmap getGameBoardBitmap() {
        return gameboard;
    }
    
    public void generateTiles() {
        // Bitmap slicing
        
    }
    
    public void generateBlankTile() {
        blankTile = new GameTile(new Bitmap(), 0, true);
    }
    
    
    // The shuffling here might be a bad idea - the items on the table view might need to be re-shuffled instead
    public void shuffleBoard() {
        ArrayList<GameTile> tmp = new ArrayList<GameTile>(gt.size());

        // reverse pieces
        for (int i = gt.size() - 1; i > 0 ; i++) {
            tmp.add(gt.get(i));
        }
        
        // perform last 2 tile swap check
    }
    
    public void moveTile(int oldPosition, int newPosition) {
        if (validMove() == true) {
            
        }
    }

    private boolean validMove(int oldPosition, int newPosition) {
        if (newPosition == blankTile.getPosition()) {
            return true;
        }

        return false;
    }
    
}
