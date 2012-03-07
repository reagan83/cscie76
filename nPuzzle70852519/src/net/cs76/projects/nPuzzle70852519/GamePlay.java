/**
 * Reagan Williams
 * reagan.williams@gmail.com
 * 70852519
 */

package net.cs76.projects.nPuzzle70852519;

import java.util.ArrayList;
import net.cs76.projects.nPuzzle70852519.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

// Item checks before submission:
//
//
// check all items are wrapped in appropriate try/catch blocks (no crashing!)
// test images with > 10 images
// test images with HUGE images
// test with very small images
// test with no images
// make sure everything is commented
// make sure curly braces align
// make sure indentation is accurate
// make sure images load dynamically (not i = 1 -> 10)
// test swapping the layout to portrait
///// failed - portrait layout is reporting too all
///// failed - game is being re-generated from scratch on switching to portrait
// test sending the app to the back of the stack, and bringing it back
// re-read doc to make sure i have everything
// create 3 second countdown text showing the solved puzzle
// create "You win" scenario! (w/num of moves)


/**
 * GamePlay Class Activity
 * 
 * This loads the Android activity for the nPuzzle gameplay.
 * 
 * @author rwilliams
 * @extends Activity
 */
public class GamePlay extends Activity {
    private GameBoard gb;
    private TableLayout tl;
    private TableRow tr;
    
    private int imageSelectionId;
    private int gameDifficulty;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("nPuzzle", "onCreate GamePlay");

        setContentView(R.layout.gameboard);
        
        // retrieve image resource id from ImageSelection class
        imageSelectionId = getIntent().getExtras().getInt("ImageResourceId");
        
        gameDifficulty = GameBoard.MEDIUM_DIFFICULTY;
        newGame(gameDifficulty);

        Log.i("nPuzzle", "onCreate GamePlay complete.");
    }
    
    /**
     * Create the game (or re-shuffle with existing settings)
     */
    private void newGame(int difficulty) {
        gameDifficulty = difficulty;

        // Create new Game Board
        Bitmap b = generateScaledGameBitmap();        
        gb = new GameBoard(b, gameDifficulty);

        // Get board tiles
        ArrayList<GameTile[]> gameBoard = gb.generateBoardTiles(windowHeight, windowWidth);

        
        // show solved solution
        drawGameBoard();
        
        // count down for 3 seconds
        new CountDownTimer(3000, 1000) {
            public void onTick(long ms) {
                TextView tv = new TextView(GamePlay.this);
                tv.setText(ms * 1000 + " seconds remaining");
                tl.addView(tv);
            }
            
            public void onFinish() {
                // shuffle board and redraw for player
                gb = gb.shuffleBoard();
                drawGameBoard();
            }
        }.start();
    }
    
    private void drawGameBoard(ArrayList<GameTile[]> gameBoard) {
        // Generate the game table UI
        tl = (TableLayout) findViewById(R.id.GameTable);
        
        // remove any existing elements
        tl.removeAllViews();
        
        // Retrieve the dimensions of the device
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        
        int windowHeight = metrics.heightPixels;
        int windowWidth = metrics.widthPixels;
        
        
        GameTile[] rowTiles = null;
        int tilesLength = 0;
        
        Log.i("nPuzzle", "Gameboard Size: " + gameBoard.size());

        // build the game board table (rows x columns)        
        for (int i = 0; i < gameBoard.size(); i++) {
            rowTiles = gameBoard.get(i);
            tilesLength = rowTiles.length;

            tr = new TableRow(this);

            for (int j = 0; j < tilesLength; j++) {
                ImageButton ib = new ImageButton(this);

                // configure tile settings
                ib.setPadding(1, 1, 1, 1);
                ib.setImageBitmap(rowTiles[j].getBitmap());
                ib.setTag(rowTiles[j].getPosition());
                ib.setBackgroundResource(0);
                
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int clickPosition = (Integer)v.getTag();
                        int blankPosition = gb.getBlankTile().getPosition();

                        Log.i("nPuzzle", "Image click position: " + clickPosition);
                        Log.i("nPuzzle", "Blank tile position: " + blankPosition);

                        // attempt to move tile
                        if (gb.move(clickPosition) == true) {
                            swapImageButtonTiles(clickPosition, blankPosition);
                        }
                    }
                });

                // add tile to table row
                tr.addView(ib);
            }
           
            // add row to table
            tl.addView(tr);
        }
    }
    
    /**
     * Generate game scaled bitmap
     * 
     * @return scaled bitmap to screen height/width 
     */
    private Bitmap generateScaledGameBitmap() {

        // create a bitmap icon of the image resource
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize = 2;
        
        Bitmap icon = BitmapFactory.decodeResource(getResources(), imageSelectionId, options);
        int imageHeight = icon.getHeight();
        int imageWidth = icon.getWidth();
        
        // Retrieve the dimensions of the device
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        
        int windowHeight = metrics.heightPixels;
        int windowWidth = metrics.widthPixels;
        
        // determine the ratio to scale the image at
        double scalex = (double) windowWidth / imageWidth;
        double scaley = (double) windowHeight / imageHeight;
        double imageScale = Math.min(scalex, scaley);

        // created a scaled version of the bitmap
        Bitmap scaledIcon = Bitmap.createScaledBitmap(icon, (int)(imageWidth * imageScale), (int)(imageHeight * imageScale), false);

        Log.i("nPuzzle", "Window Height: " + windowHeight + ", Width: " + windowWidth);
        Log.i("nPuzzle", "Image Height: " + imageHeight + ", Width: " + imageWidth);
        Log.i("nPuzzle", "Scaled Image Height: " + scaledIcon.getHeight() + ", Width: " + scaledIcon.getWidth());
        
        return scaledIcon;
    }

    /**
     * Perform the tile image swapping
     * 
     * @param clickPosition
     * @param blankPosition
     */
    private void swapImageButtonTiles(int clickPosition, int blankPosition) {
        ImageButton swap1 = (ImageButton)tl.findViewWithTag(clickPosition);
        ImageButton swap2 = (ImageButton)tl.findViewWithTag(blankPosition);
        
        if (swap1 == null) {
            Log.i("nPuzzle", "swap1 is null");
        }
        if (swap2 == null) {
            Log.i("nPuzzle", "swap2 is null");
        }
        
        Drawable tempSwap = swap1.getDrawable();
        
        swap1.setImageDrawable(swap2.getDrawable());
        swap2.setImageDrawable(tempSwap);
    }
    
    /**
     * Options menu generation
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.gamemenu, menu);
        return true;
    }
    
    /**
     * Options item selection
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.easy:
                newGame(GameBoard.EASY_DIFFICULTY);
                return true;
            case R.id.medium:
                newGame(GameBoard.MEDIUM_DIFFICULTY);
                return true;
            case R.id.hard:
                newGame(GameBoard.HARD_DIFFICULTY);
                return true;
            case R.id.shuffle:
                newGame(gameDifficulty);
                return true;
            case R.id.pickanother:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
