/**
 * Reagan Williams
 * reagan.williams@gmail.com
 * 70852519
 */

package net.cs76.projects.nPuzzle70852519;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;

public class GameBoard {
    Bitmap boardImage = null;
    ArrayList<GameTile[]> gameBoard = null;
    int gameDifficulty = 0;
    GameTile blankTile = null;
    
    public GameBoard(Bitmap b, int difficulty) {
        boardImage = b;
        gameBoard = new ArrayList<GameTile[]>();
        gameDifficulty = difficulty;
       
        generateGameBoard();
        generateBlankTile();
    }
    
    public ArrayList<GameTile[]> getGameBoard() {
        return gameBoard;
    }
    
    private void generateGameBoard() {
        
        // Bitmap slicing
        int boardColumns = (int)Math.sqrt(gameDifficulty);
        int boardRows = (int)Math.sqrt(gameDifficulty);

        Log.i("nPuzzle", "rowsXcolumns: " + boardRows + "X" + boardColumns);
        
        int imageHeight = boardImage.getHeight();
        int imageWidth = boardImage.getWidth();

        Log.i("nPuzzle", "Board Image Height: " + imageHeight + ", Width: " + imageWidth);
        
        int tileWidth = imageWidth / boardColumns;
        int tileHeight = imageHeight / boardRows;
        
        Log.i("nPuzzle", "Tile height: " + tileHeight + ", width: " + tileWidth);

        GameTile[] gt = null;
        int tilePosition = 0;
        Bitmap tileBitmap = null;
      
        for (int i = 0; i < boardRows; i++) {

            gt = new GameTile[boardColumns];

            for (int j = 0; j < boardColumns; j++) {
                // Generate new bitmap tile
                int x = j * tileWidth;
                int y = i * tileHeight;
                
                
                Log.i("nPuzzle", "Debug: i: " + i + ", j: " + j + ", x: " + x + ", y: " + y + ", tilePosition: " + tilePosition);

                if (tilePosition != (gameDifficulty - 1)) {
                    tileBitmap = Bitmap.createBitmap(boardImage, x, y, tileWidth, tileHeight);

                    gt[j] = new GameTile(tileBitmap, tilePosition, false);
                } else {
                    // last tile (blank)
                    tileBitmap = Bitmap.createBitmap(tileWidth, tileHeight, Config.ALPHA_8);
                    
                    gt[j] = new GameTile(tileBitmap, tilePosition, true);
                }

                tilePosition++;
            }
            
            Log.i("nPuzzle", "Debug: adding new row.");

            gameBoard.add(gt);
        }
        
        Log.i("nPuzzle", "Completed generating gameboard.");

    }
    
    public void generateBlankTile() {
        //blankTile = new GameTile(new Bitmap(), 0, true);
    }
    
    
    // The shuffling here might be a bad idea - the items on the table view might need to be re-shuffled instead
    public void shuffleBoard() {
//        ArrayList<GameTile> tmp = new ArrayList<GameTile>(gt.size());

        // reverse pieces
 //       for (int i = gt.size() - 1; i > 0 ; i++) {
 //           tmp.add(gt.get(i));
 //       }
        
        // perform last 2 tile swap check
    }
    
    public void moveTile(int oldPosition, int newPosition) {
        if (validMove(oldPosition, newPosition) == true) {
            
        }
    }

    private boolean validMove(int oldPosition, int newPosition) {
        if (newPosition == blankTile.getTilePosition()) {
            return true;
        }

        return false;
    }
    
}
