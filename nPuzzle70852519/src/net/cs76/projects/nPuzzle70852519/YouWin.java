/**
 * Reagan Williams
 * reagan.williams@gmail.com
 * 70852519
 */

package net.cs76.projects.nPuzzle70852519;

import net.cs76.projects.nPuzzle70852519.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * YouWin Class Activity
 * 
 * Loads the activity when the game is complete to show the player a You Win
 * screen!
 * 
 * @author rwilliams
 * @extends Activity
 */
public class YouWin extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("nPuzzle", "onCreate YouWin!");
        TextView tv = new TextView(this);
        
        int turns = getIntent().getExtras().getInt("turns");
        tv.setText("It took you " + turns + " moves to complete the game.");
        
        setContentView(tv);
    }
}
