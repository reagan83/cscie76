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
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Field;


/**
 * GamePlay Class Activity
 * 
 * This loads the Android activity for the nPuzzle gameplay.
 * 
 * @author rwilliams
 * @extends Activity
 */
public class GamePlay extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("nPuzzle", "onCreate GamePlay");
        setContentView(R.layout.gameboard);

        TableLayout tl = (TableLayout) findViewById(R.id.GameTable);
        TableRow tr = new TableRow(this);
        
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

        image.setImageBitmap(scaledIcon);

        tr.addView(image);
        tl.addView(tr);
        
        TableRow tr2 = new TableRow(this);
        
        ImageView image2 = new ImageView(this);
        Bitmap icon2 = Bitmap.createBitmap(scaledIcon, 0, 0, 50, 50);
        
        image2.setImageBitmap(icon2);

        tr2.addView(image2);
        tl.addView(tr2);

        Log.i("nPuzzle", "onCreate GamePlay complete.");

    }

}
