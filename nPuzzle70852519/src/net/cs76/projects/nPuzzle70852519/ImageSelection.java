/**
 * Reagan Williams
 * reagan.williams@gmail.com
 * 70852519
 */

package net.cs76.projects.nPuzzle70852519;

import java.util.ArrayList;
import java.util.List;

import net.cs76.projects.nPuzzle70852519.R;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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
 * This class loads set of image files and displays them to the user in
 * a gallery format to select for the nPuzzle game.
 * 
 * @author rwilliams
 * @extends ListActivity
 */
public class ImageSelection extends ListActivity {
    private static List<Integer> ImageIdList = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
//        try {
            super.onCreate(savedInstanceState);
           
            ImageIdList = new ArrayList<Integer>();
            
            Log.i("nPuzzle", "To do: remove the i < 10 on this section");
            for (int i = 0; i < 10; i++) {
                String p = getResources().getString(R.string.ImagePrefix);
                int id = 0;
                
                id = getResources().getIdentifier("drawable/" + p + i, null, getPackageName());
                
                if (id > 0) {
                    ImageIdList.add(id);                   
                }
                
            }
            Log.i("nPuzzle", "ImageIdList Size" + ImageIdList.size());

            setListAdapter(new ImageAdapter(this));

//        } catch (Exception e) {
            
//        }
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
                convertView = li.inflate(R.layout.imagegallery, null);
                
                holder = new ViewHolder();
                
                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.image = (ImageView) convertView.findViewById(R.id.image);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            holder.text.setText(getResources().getResourceName(ImageIdList.get(position)));
            
            icon = BitmapFactory.decodeResource(getResources(), ImageIdList.get(position));
            
            //holder.image.setImageBitmap(BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(ImageIdList[position], "drawable", "net.cs76.projects.nPuzzle70852519")));
            
            holder.image.setImageBitmap(icon);
            
            // store the image resource id in the tag of the list row
            convertView.setTag(ImageIdList.get(position));
            convertView.setClickable(true);

            return convertView;
        }
        
        class ViewHolder {
            TextView text;
            ImageView image;
        }
    }
    
    /**
     * There's an issue here with the trackball on the emulator.  NEED TO RESEARCH AND FIX!
     */
    public void onClickHandler(View v) {
        Log.i("nPuzzle", "Click Handler - " + v.getTag());

        Intent game = new Intent(v.getContext(), GamePlay.class);

        // retrieve the image resource id from the list row tag and send it to the new activity
        game.putExtra("ImageResourceId", (Integer)v.getTag());

        startActivityForResult(game, 0);
    }
}