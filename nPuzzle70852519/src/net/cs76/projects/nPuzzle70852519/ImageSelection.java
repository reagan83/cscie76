package net.cs76.projects.nPuzzle70852519;

import java.util.ArrayList;

import net.cs76.projects.nPuzzle70852519.R;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
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
    
    private ArrayList<GameImage> gameImages = null;
    private ImageAdapter imageAdapter = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
//        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            
            gameImages = new ArrayList<GameImage>();
            
            gameImages.add(new GameImage("image00", R.drawable.sample_0));
            gameImages.add(new GameImage("image01", R.drawable.sample_1));
            gameImages.add(new GameImage("image02", R.drawable.sample_2));
            gameImages.add(new GameImage("image03", R.drawable.sample_3));
            
            this.imageAdapter = new ImageAdapter(this, R.layout.photo_item, gameImages);
            
            setListAdapter(this.imageAdapter);
                        
//        } catch (Exception e) {
            
//        }
    }
    
    
    private class GameImage {
        private String imageName;
        private int imageId;
        
        public GameImage(String name, int id) {
            imageName = name;
            imageId = id;
        }
        
        public String getImageName() {
            return imageName;
        }

        public int getImageResourceId() {
            return imageId;
        }
    }
    
    private class ImageAdapter extends ArrayAdapter<GameImage> {
        
        private ArrayList<GameImage> items = null;
        
        public ImageAdapter(Context context, int textViewResourceId, ArrayList<GameImage> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.photo_item, null);
            }

            GameImage gi = items.get(position);
            
            if (gi != null) {
                ImageView iv = null;
                TextView tv = null;
                
                try {
                
                iv = (ImageView) iv.findViewById(R.id.photoIcon);
                tv = (TextView) tv.findViewById(R.id.photoDescription);
                } catch (Exception e) { 
                
                }
                
                if (iv != null) {
                    iv.setImageResource(gi.getImageResourceId());
                }
                
                if (tv != null) {
                    tv.setText(gi.getImageName());
                }
            }
            
            return v;
        }
        
        
/*        
        int mGalleryItemBackground;
        private Context mContext;

        private Integer[] mImageIds = {
                R.drawable.sample_0,
                R.drawable.sample_1,
                R.drawable.sample_2,
                R.drawable.sample_3
        };

        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
            mGalleryItemBackground = attr.getResourceId(
                    R.styleable.HelloGallery_android_galleryItemBackground, 0);
            attr.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);

            imageView.setImageResource(mImageIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(mGalleryItemBackground);

            return imageView;
        }
*/    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // start new Game activity!!!
        
    }
}