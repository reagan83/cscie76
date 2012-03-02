package net.cs76.projects.nPuzzle70852519;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.Toast;

/**
 * ImageSelection Class Activity
 * 
 * This class loads a pre-defined set of image files and displays them to the user in
 * an Image Gallery to select.
 * 
 * @author rwilliams
 * @extends Activity
 * @implements onItemClickListener
 */
public class ImageSelection extends ListActivity implements AdapterView.OnItemClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retrieve defined image filenames from res/values/strings.xml
        String[] images = this.getResources().getStringArray(R.array.images);

        // create a new ArrayAdapter to store data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, images);
        
//        for (int i = 0; i < images.length - 1; i++) {
             
//        }
        
        // set adapter as this adapter
        setListAdapter(adapter);
        
                
        // setup the listView event listener
        ListView l = new ListView(this);
        l.setOnItemClickListener(this);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // start new Game activity!!!
        
    }
}