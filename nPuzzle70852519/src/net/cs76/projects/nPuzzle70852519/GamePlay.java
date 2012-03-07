/**
 * Reagan Williams
 * reagan.williams@gmail.com
 * 70852519
 */

package net.cs76.projects.nPuzzle70852519;

import java.util.ArrayList;
import java.util.List;

import net.cs76.projects.nPuzzle70852519.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Field;

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
// test sending the app to the back of the stack, and bringing it back
// re-read doc to make sure i have everything
// create a menu dialog w/different difficulty levels
// create 3 second countdown text showing the solved puzzle
// create "You win" scenario! track # of moves

/**
 * GamePlay Class Activity
 * 
 * This loads the Android activity for the nPuzzle gameplay.
 * 
 * @author rwilliams
 * @extends Activity
 */
public class GamePlay extends Activity {
    
    GameBoard gb = null;
    TableLayout tl = null;
    TableRow tr = null;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("nPuzzle", "onCreate GamePlay");
        setContentView(R.layout.gameboard);

        tl = (TableLayout) findViewById(R.id.GameTable);
        
        int GameImageId = getIntent().getExtras().getInt("ImageResourceId");
        
        Bitmap icon = BitmapFactory.decodeResource(getResources(), GameImageId);
        
        ImageView image = new ImageView(this);
        image.setTag(GameImageId);
        image.setClickable(true);
        
        Log.i("nPuzzle", "imageId: " + GameImageId);
        
        int imageHeight = icon.getHeight();
        int imageWidth = icon.getWidth();
        
        int windowHeight = getWindowManager().getDefaultDisplay().getHeight();
        int windowWidth = getWindowManager().getDefaultDisplay().getWidth();
        
        double scalex = (double) windowWidth / imageWidth;
        double scaley = (double) windowHeight / imageHeight;
        double imageScale = Math.min(scalex, scaley);
        
        Log.i("nPuzzle", "Image Scale: " + imageScale);

        Bitmap scaledIcon = Bitmap.createScaledBitmap(icon, (int)(imageWidth * imageScale), (int)(imageHeight * imageScale), false);
        
        tr = new TableRow(this);
        
        ImageView image2 = new ImageView(this);
        Bitmap icon2 = Bitmap.createBitmap(scaledIcon, 0, 0, 50, 50);
        
        image2.setImageBitmap(icon2);
        
        gb = new GameBoard(scaledIcon, 9);
        
        ArrayList<GameTile[]> board = gb.getGameBoard();
        
        GameTile[] tiles = null;
        int tilesLength = 0;
        
        Log.i("nPuzzle", "Gameboard Size: " + board.size());
        
        // build the game board table (rows x columns)        
        for (int i = 0; i < board.size(); i++) {
            tiles = board.get(i);
            tilesLength = tiles.length;
            
            tr = new TableRow(this);

            for (int j = 0; j < tilesLength; j++) {
                ImageButton ib = new ImageButton(this);

                ib.setImageBitmap(tiles[j].getBitmap());
                ib.setPadding(1, 1, 1, 1);

                ib.setTag(tiles[j].getPosition());
                Log.i("nPuzzle", "Set tag: " + tiles[j].getPosition());

                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int clickPosition = (Integer)v.getTag();
                        int blankPosition = gb.getBlankTile().getPosition();

                        Log.i("nPuzzle", "Image click position: " + clickPosition);
                        Log.i("nPuzzle", "Blank tile position: " + blankPosition);

                        if (gb.move(gb.getTileByPosition(clickPosition)) == true) {
                            swapImageButtonTiles(clickPosition, blankPosition);
                        }
                    }
                });

                tr.addView(ib);
            }
           
            tl.addView(tr);
        }

        Log.i("nPuzzle", "onCreate GamePlay complete.");

    }
    
    private void swapImageButtonTiles(int clickPosition, int blankPosition) {
        ImageButton trSwap1 = (ImageButton)tl.findViewWithTag(clickPosition);
        ImageButton trSwap2 = (ImageButton)tl.findViewWithTag(blankPosition);
        
        if (trSwap1 == null) {
            Log.i("nPuzzle", "trSwap1 is null");
        }
        if (trSwap2 == null) {
            Log.i("nPuzzle", "trSwap2 is null");
        }
        
        Drawable temp = trSwap1.getDrawable();
        
        trSwap1.setImageDrawable(trSwap2.getDrawable());
        trSwap2.setImageDrawable(temp);
    }

}
