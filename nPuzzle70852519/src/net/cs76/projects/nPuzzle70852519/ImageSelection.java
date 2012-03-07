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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ImageSelection Class Activity
 * 
 * This class loads set of image files and displays them to the user in a
 * gallery format to select for the nPuzzle game.
 * 
 * @author rwilliams
 * @extends ListActivity
 */
public class ImageSelection extends ListActivity {
    private static List<Integer> ImageIdList;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);

            ImageIdList = new ArrayList<Integer>();
            Integer i = 0;

            // load images
            while (true) {
                String p = getResources().getString(R.string.ImagePrefix);
                int id = 0;

                id = getResources().getIdentifier("drawable/" + p + i, null,
                        getPackageName());

                if (id > 0) {
                    ImageIdList.add(id);
                } else {
                    break;
                }

                i++;
            }

            Log.i("nPuzzle", "ImageIdList Size" + ImageIdList.size());

            setListAdapter(new ImageAdapter(this));

        } catch (Exception e) {
            Log.i("nPuzzle", "ImageSelection[onCreate()] Exception caught: " + e.getMessage());
        }
    }

    /**
     * ImageAdapter class to populate the Image List
     * 
     * @author reagan
     * @extends BaseAdapter
     */
    private class ImageAdapter extends BaseAdapter {
        private LayoutInflater li;
        private Bitmap icon;

        /**
         * ImageAdapter constructor - establishes the LayoutInflator
         * 
         * @param context
         */
        public ImageAdapter(Context context) {
            // store the layout inflater in a local variable
            li = LayoutInflater.from(context);
        }

        /**
         * Get count method for ImageId List
         * 
         * @return count of imageid list
         */
        public int getCount() {
            return ImageIdList.size();
        }

        /**
         * Returns the Item id
         * 
         * @param position
         * @return position
         */
        public long getItemId(int position) {
            return position;
        }

        /**
         * Gets the generic Image object at the position
         * 
         * @param position
         * @return object (image)
         */
        public Object getItem(int position) {
            return position;
        }

        /**
         * View method to declare the inflating of the list activity
         * 
         * @param position
         * @param convertView
         * @param parent
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            // setup the convertView view with resources from the layout
            // template
            if (convertView == null) {
                convertView = li.inflate(R.layout.imagegallery, null);

                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.image = (ImageView) convertView.findViewById(R.id.image);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String strImageName = getResources().getResourceName(
                    ImageIdList.get(position));

            // remove android specific resource identifiers (strip the name down
            // to the filename)
            strImageName = strImageName
                    .substring(strImageName.indexOf("/") + 1);

            // configure text and image fields
            holder.text.setText(strImageName);

            try {
                icon = BitmapFactory.decodeResource(getResources(),
                        ImageIdList.get(position));

                holder.image.setImageBitmap(icon);

            } catch (Exception e) {
                Log.i("nPuzzle", "ImageSelection[getView()] Exception caught: "
                        + e.getMessage());
            }

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
     * On Click Handler for the List Activity - starts the GamePlay Activity
     * when an image is clicked
     * 
     */
    public void onClickHandler(View v) {
        Log.i("nPuzzle", "ImageSelection[ClickHandler] View Tag: " + v.getTag());

        Intent game = new Intent(v.getContext(), GamePlay.class);

        // retrieve the image resource id from the list row tag and send it to
        // the new activity
        game.putExtra("ImageResourceId", (Integer) v.getTag());

        startActivityForResult(game, 0);
    }
}