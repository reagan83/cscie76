package net.cs76.projects.nPuzzle70852519;

import java.util.ArrayList;
import java.util.List;

import net.cs76.projects.nPuzzle70852519.R;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Field;


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
    private static List<Integer> ImageIdList = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
//        try {
            super.onCreate(savedInstanceState);
           
            ImageIdList = new ArrayList<Integer>();
            
            for (int i = 0; i < 10; i++) {
                String p = getResources().getString(R.string.ImagePrefix);
                int id = 0;
                
                id = getResources().getIdentifier("drawable/" + p + i, null, getPackageName());
                Log.i("nPuzzle", "Testing-" + i + ": " + id );
                
                if (id > 0) {
                    ImageIdList.add(id);                   
                }
                
            }
            Log.i("nPuzzle", "ImageIdList Size" + ImageIdList.size());

            setListAdapter(new ImageAdapter(this));

//        } catch (Exception e) {
            
//        }
    }
    
    public void ImageSelectionClick(View v) {
        Log.i("nPuzzle", "Clicked!");
        
    }
        
    private class ImageAdapter extends BaseAdapter {
        private LayoutInflater li;
        private Bitmap icon;
        
        public ImageAdapter(Context context) {
            // store the layout inflater in a local variable
            li = LayoutInflater.from(context);
            
            //icon = BitmapFactory.decodeResource()
        }
        
        public int getCount() {
            return ImageIdList.size();
        }
        
        public long getItemId(int position) {
            return position;
        }
        
        public Object getItem(int position) {
            return position;
        }
        
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
                        
            if (convertView == null) {
                convertView = li.inflate(R.layout.photo_item, null);
                
                holder = new ViewHolder();
                
                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            holder.text.setText(getResources().getResourceName(ImageIdList.get(position)));
            
            icon = BitmapFactory.decodeResource(getResources(), ImageIdList.get(position));
            
            //holder.image.setImageBitmap(BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(ImageIdList[position], "drawable", "net.cs76.projects.nPuzzle70852519")));
            
            holder.image.setImageBitmap(icon);
            
            //convertView.setOnClickListener(l)

            return convertView;
        }
        
        class ViewHolder {
            TextView text;
            ImageView image;
        }
    }
    
    public void onClickHandler(View v) {
        Log.i("nPuzzle", "Click Handler!");
    }
            
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // start new Game activity!!!
        Log.i("nPuzzle", "Clicked!");
        
    }
}