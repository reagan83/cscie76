package net.cs76.projects.nPuzzle70852519;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.Toast;

public class ImageSelection extends Activity implements onItemClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Gallery g = (Gallery)findByViewId(R.id.imageGallery);
        
        g.setAdapter(new ImageAdapter(this));
        
        g.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick() {
                Toast.makeText(ImageSelection.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        }));
        
        
    }
}