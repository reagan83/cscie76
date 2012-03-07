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
    public static final int EASY_DIFFICULTY = 9;
    public static final int MEDIUM_DIFFICULTY = 16;
    public static final int HARD_DIFFICULTY = 25;

    private Bitmap boardImage;
    private ArrayList<GameTile[]> gameBoard;
    private ArrayList<GameTile> gameTiles;
    
    private GameTile blankTile;
    private int numberOfMoves;
    
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
        numberOfMoves = 0;

        GAME_DIFFICULTY = difficulty;
        BOARD_ROWS = (int)Math.sqrt(GAME_DIFFICULTY);
        BOARD_COLUMNS = (int)Math.sqrt(GAME_DIFFICULTY);
        
        newBoard();
    }
        
    /**
     * Returns the blank GameTile object
     * 
     * @return blank tile object
     */
    public GameTile getBlankTile() {
        return blankTile;
    }

    /**
     * Create a NxN board with empty tiles
     * 
     * @return arraylist of gametiles
     */
    public void newBoard() {
        Log.i("nPuzzle", "Generating " + BOARD_ROWS + "X" + BOARD_COLUMNS + " puzzle.");

        // create board w/empty tiles
        for (int i = 0; i < GAME_DIFFICULTY; i++) {

            if (i == GAME_DIFFICULTY) {
                // last tile (blank)
                blankTile = new GameTile(null, i, true);
                gameTiles.add(blankTile);
            } else {
                // regular tile
                gameTiles.add(new GameTile(null, i, false));
            }

        }
    }
    /**
     * Sets the game board image.  This can be called if needed during a device rotation because the screen height and width change.
     * 
     * Once the game board image changes the tiles will be regenerated.
     * 
     * @param b
     */
    public void setBoardImage(Bitmap b) {
        boardImage = b;

        generateBoardTiles();
    }

    /**
     * Generate tile images for the game board
     * 
     */
    public void generateBoardTiles() {
        int imageHeight = boardImage.getHeight();
        int imageWidth = boardImage.getWidth();

        int tileWidth = imageWidth / BOARD_COLUMNS;
        int tileHeight = imageHeight / BOARD_ROWS;

        Log.i("nPuzzle", "Board Image Height: " + imageHeight + ", Width: " + imageWidth);
        Log.i("nPuzzle", "Tile height: " + tileHeight + ", width: " + tileWidth);

        int tilePosition = 0;
        Bitmap tileBitmap = null;
        GameTile t;
      
        // Bitmap slicing
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                t = gameTiles.get(tilePosition);
                
                // Generate new bitmap tile
                int x = j * tileWidth;
                int y = i * tileHeight;

                if (tilePosition == GAME_DIFFICULTY) {
                    // last tile (blank)
                    tileBitmap = Bitmap.createBitmap(tileWidth, tileHeight, Config.ALPHA_8);
                } else {
                    // regular tile
                    tileBitmap = Bitmap.createBitmap(boardImage, x, y, tileWidth, tileHeight);
                }

                t.setBitmap(tileBitmap);
                tilePosition++;
            }
        }
        
        Log.i("nPuzzle", "Completed generating tile bitmaps.");        
    }

    public ArrayList<GameTile[]> generateNewGameBoard(int deviceHeight, int deviceWidth) {

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

                if (tilePosition == GAME_DIFFICULTY) {
                    // last tile (blank)
                    tileBitmap = Bitmap.createBitmap(tileWidth, tileHeight, Config.ALPHA_8);
                    gt[j] = new GameTile(tileBitmap, tilePosition, true);

                    blankTile = gt[j];
                } else {
                    // regular tile
                    tileBitmap = Bitmap.createBitmap(boardImage, x, y, tileWidth, tileHeight);
                    gt[j] = new GameTile(tileBitmap, tilePosition, false);
                }

                tilePosition++;
            }

            Log.i("nPuzzle", "Adding new gameboard row.");

            gameBoard.add(gt);
        }
        
        Log.i("nPuzzle", "Completed generating gameboard.");

        return gameBoard;
    }
    
    /**
     * Shuffle the game board
     * 
     * This shuffle will perform a reverse-tile shuffle (with consideration for even numbered difficulty levels)
     */
    public void shuffleBoard() {
        GameTile tile;
        GameTile swap;

        int newPosition = 1;

        // define the amount of iterations needed to swap all tiles on the board
        int swapIterations = (GAME_DIFFICULTY - 1) / 2;

        // perform the tile swap/shuffle by flipping the order
        // Example: in a 3x3 grid tile 8->1 and 1->8, 7->2 and 2->7, etc.
        for (int i = 0; i < swapIterations; i++) {             
            tile = getTileByPosition(i);
            swap = getTileByPosition(newPosition);
            
            // only move the non-blank tiles
            if ( (tile.isBlankTile() == false) && (swap.isBlankTile() == false) ) {
                tile.setPosition(newPosition);
                swap.setPosition(i);
                newPosition++;
            }
        }

        // perform last 2 tile swap check    
        if ((GAME_DIFFICULTY % 2) == 0) {
            
            // swap the last 2 "Real" tiles (original tiles #1 and #2) for solvability
            tile = getTileByPosition(GAME_DIFFICULTY - 1);
            swap = getTileByPosition(GAME_DIFFICULTY - 2);

            tile.setPosition(GAME_DIFFICULTY - 2);
            swap.setPosition(GAME_DIFFICULTY - 1);
        }
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
     * Returns the number of moves in a game.
     * 
     * @return number of moves
     */
    public int getNumberOfMoves() {
        return numberOfMoves;
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

        numberOfMoves++;

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
