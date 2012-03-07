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

/**
 * GameBoard class
 * 
 * This class establishes a gameboard from a bitmap, and handles tile slicing based on difficulty and
 * validates all game movements. 
 * 
 * @author rwilliams
 *
 */
public class GameBoard {
    private Bitmap boardImage = null;
    private ArrayList<GameTile[]> gameBoard = null;
    private GameTile blankTile = null;
    
    private final int GAME_DIFFICULTY;
    private final int BOARD_ROWS;
    private final int BOARD_COLUMNS;

    /**
     * GameBoard constructor
     * 
     * Establishes class variables and initiates the game board.
     * 
     * @param b
     * @param difficulty
     */
    public GameBoard(Bitmap b, int difficulty) {
        boardImage = b;
        gameBoard = new ArrayList<GameTile[]>();

        GAME_DIFFICULTY = difficulty;
        BOARD_ROWS = (int)Math.sqrt(GAME_DIFFICULTY);
        BOARD_COLUMNS = (int)Math.sqrt(GAME_DIFFICULTY);

        generateGameBoard();
    }
    
    /**
     * Provides the data structure for the game board in rows and columns
     * 
     * @return gameboard elements
     */
    public ArrayList<GameTile[]> getGameBoard() {
        return gameBoard;
    }
    
    /**
     * Returns the blank GameTile object
     * 
     * @return blank tile object
     */
    public GameTile getBlankTile() {
        return blankTile;
    }

    private void generateGameBoard() {
        
        // Bitmap slicing
        Log.i("nPuzzle", "Generating " + BOARD_ROWS + "X" + BOARD_COLUMNS + " puzzle.");
        
        int imageHeight = boardImage.getHeight();
        int imageWidth = boardImage.getWidth();

        Log.i("nPuzzle", "Board Image Height: " + imageHeight + ", Width: " + imageWidth);
        
        int tileWidth = imageWidth / BOARD_COLUMNS;
        int tileHeight = imageHeight / BOARD_ROWS;
        
        Log.i("nPuzzle", "Tile height: " + tileHeight + ", width: " + tileWidth);

        GameTile[] gt = null;
        int tilePosition = 0;
        Bitmap tileBitmap = null;
      
        for (int i = 0; i < BOARD_ROWS; i++) {

            gt = new GameTile[BOARD_COLUMNS];

            for (int j = 0; j < BOARD_COLUMNS; j++) {
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

                    blankTile = gt[j];
                }

                tilePosition++;
            }

            Log.i("nPuzzle", "Adding new gameboard row.");

            gameBoard.add(gt);
        }
        
        Log.i("nPuzzle", "Completed generating gameboard.");

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
    
    /**
     * Returns the GameTile object in the referenced position.
     * 
     * @param position
     * @return GameTile in the position
     */
    public GameTile getTileByPosition(int position) {
        GameTile[] gt;

        for (int i = 0; i < gameBoard.size(); i++) {
            gt = gameBoard.get(i);
            
            for (int j = 0; j < gt.length; j++) {
                if (position == gt[j].getPosition()) {
                    return gt[j];
                }
            }
        }

        return null;
    }

    /**
     * This method will attempt to move a tile by verifying if the blank tile is adjacent.
     * 
     * @param position
     * @return boolean based on the success of the move
     */
    public boolean move(int position) {
        // find the game tile to move
        GameTile tile = getTileByPosition(position);
        
        int currentPosition = position;
        int newPosition = blankTile.getPosition();
        
        // validate move
        if (validMove(currentPosition, newPosition) == false) {
            return false;
        }

        // swap positions of game tile and blank tile
        blankTile.setPosition(currentPosition);
        tile.setPosition(newPosition);

        return true;
    }
    
    /**
     * This method determines the validity of the move by performing blank square checks horizontally and vertically
     * 
     * @param currentPosition
     * @param newPosition
     * @return boolean based on if the move is valid or not
     */
    private boolean validMove(int currentPosition, int newPosition) {
        boolean validMove = false;
        
        // Convert the position variables to 1-base because the BOARD variables do not start at 0
        currentPosition++;
        newPosition++;

        // verify vertical movements
        if (currentPosition == (newPosition + BOARD_COLUMNS)) {
            validMove = true;
        } else if (currentPosition == (newPosition - BOARD_COLUMNS)) {
            validMove = true;
        }

        // verify horizontal movements
        if ((currentPosition % BOARD_COLUMNS) == 0) {
            // right column of the board
            Log.i("nPuzzle", "Right column of the board");
            if (currentPosition == (newPosition + 1)) {
                validMove = true;
            }
        } else if (((currentPosition - 1) % BOARD_COLUMNS) == 0) {
            // left column of the board
            if (currentPosition == (newPosition - 1)) {
                validMove = true;
            }
        } else {
            // elsewhere on the board
            if (currentPosition == (newPosition + 1)) {
                validMove = true;
            } else if (currentPosition == (newPosition - 1)) {
                validMove = true;
            }
        }

        return validMove;
    }
    
}
