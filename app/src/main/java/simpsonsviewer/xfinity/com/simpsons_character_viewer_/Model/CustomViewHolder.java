package simpsonsviewer.xfinity.com.simpsons_character_viewer_.Model;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Controller.CustomVolleyRequest;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Controller.OnHeadlineClickListener;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.R;


/**
 * Created by m14x on 04/29/2016.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {

    private TextView characterName;
    private TextView characterDetail;
    private NetworkImageView characterIcon;
    private ImageLoader imageLoader;
    private Context context;
    private Activity activity;
    private CardView cardview;
    private HashMap<String,String> detail = new HashMap<>();
    OnHeadlineClickListener listener;

    public CustomViewHolder(View itemView, Context context, Activity activity, OnHeadlineClickListener listener) {
        super(itemView);
        characterName = (TextView) itemView.findViewById(R.id.nameTextView);
        characterDetail = (TextView) itemView.findViewById(R.id.detailTextView);
        characterIcon = (NetworkImageView) itemView.findViewById(R.id.image);
        cardview = (CardView) itemView.findViewById(R.id.cardview);
        this.context = context;
        this.activity = activity;
        this.listener = listener;

    }
    public void setItem(final ObjectDescriptor od) {
        final String txt = od.getCharacterName();
        characterName.setText(od.getCharacterName());
        characterDetail.setText(od.getCharacterDetail());
        if (od.getImageUrl().length() != 0) {
        imageLoader = CustomVolleyRequest.getInstance(context)
                .getImageLoader();
        imageLoader.get(od.getImageUrl(), ImageLoader.getImageListener(characterIcon,
                R.drawable.unknown, android.R.drawable
                        .ic_dialog_alert));

            characterIcon.setImageUrl(od.getImageUrl(), imageLoader);
        } else {
          characterIcon.setDefaultImageResId(R.drawable.unknown);

        }
    cardview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getDetails(od.getDetailUrl(),od.getImageUrl(),imageLoader,od.getCharacterName());
        }
    });
    }

    public void getDetails(String url, final String urlImage, final ImageLoader image, final String name){

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject infobox = response.getJSONObject("Infobox");
                    JSONArray jsonArray = infobox.getJSONArray("content");
                    for(int i = 0 ; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        detail.put(jsonObject.optString("label","Unknown"),jsonObject.optString("value","Unknown"));
                    }
                    listener.headLineSelected(activity,detail,urlImage,image,name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);

    }
}
