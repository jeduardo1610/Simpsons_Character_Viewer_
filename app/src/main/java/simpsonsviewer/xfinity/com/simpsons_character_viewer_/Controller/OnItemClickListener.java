package simpsonsviewer.xfinity.com.simpsons_character_viewer_.Controller;

import android.app.Activity;

import com.android.volley.toolbox.ImageLoader;

import java.util.HashMap;

/**
 * Created by m14x on 05/01/2016.
 */
public interface OnItemClickListener {
    public void itemLineSelected(Activity activity,
                                 HashMap<String, String> detail,
                                 String urlImage,
                                 ImageLoader image,
                                 String name);
}

