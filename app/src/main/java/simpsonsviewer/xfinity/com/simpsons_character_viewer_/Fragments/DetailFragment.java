package simpsonsviewer.xfinity.com.simpsons_character_viewer_.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import simpsonsviewer.xfinity.com.simpsons_character_viewer_.R;

/**
 * Created by m14x on 05/01/2016.
 */
public class DetailFragment extends Fragment {


    private  View view;
    private List<String> label;
    private List<String> value;
    private NetworkImageView imageView;
    private TextView characterName;
    private LinearLayout layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.detail_fragment_layout,container,false);
         return view;
    }

    public void setUp(Activity activity, HashMap<String,String> detail, String url, ImageLoader image, String name){
        label = new ArrayList<>(detail.keySet());
        value = new ArrayList<>(detail.values());

        imageView = (NetworkImageView) view.findViewById(R.id.characterImage);
        characterName = (TextView) view.findViewById(R.id.characterNameTextView);
        characterName.setText(name);

        if(url.length()!=0) {
            imageView.setImageUrl(url, image);
        }else{
            imageView.setDefaultImageResId(R.drawable.unknown);
        }
        TextView textView1 = new TextView(activity);
        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView1.setText(label.get(0)+": "+value.get(0));
        textView1.setPadding(20, 10, 10, 10);
        layout.addView(textView1);

        for(int i = 1 ; i<label.size(); i++){
            TextView info = new TextView(activity);
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            ll.setMargins(20, 10, 10, 10);
            info.setLayoutParams(ll);
            info.setText(label.get(i)+": "+value.get(i));
            layout.addView(info);
        }
    }
    public void cleanUp(){
        layout = (LinearLayout)view.findViewById(R.id.relativeLayout);
       layout.removeAllViews();
    }
}
