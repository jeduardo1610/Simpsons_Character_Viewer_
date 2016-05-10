package simpsonsviewer.xfinity.com.simpsons_character_viewer_.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import simpsonsviewer.xfinity.com.simpsons_character_viewer_.BuildConfig;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Controller.OnItemClickListener;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Model.CustomAdapter;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Model.ObjectDescriptor;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.R;

/**
 * Created by m14x on 05/01/2016.
 */
public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private final String FLAVOR_SIMPSONS = "simpsons";
    private final String FLAVOR_FUTURAMA = "futurama";
    private  String URL_REQUEST ="";
    private final String URL_FUTURAMA = "https://duckduckgo.com/?q=futurama+characters&format=json";
    private final String URL_SIMPSONS = "http://api.duckduckgo.com/?q=simpsons+characters&format=json";
    private List<ObjectDescriptor> characterShortDetail = new ArrayList<>();
    private View view;
    OnItemClickListener onHeadlineClickListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        setUpFlavor();
        ConnectivityManager connMgr = (ConnectivityManager)
             view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Log.d("Result", "CONNECTION");
            getData();
        } else {
            Toast.makeText(getContext(),"No connection available",Toast.LENGTH_LONG).show();
        }
        return view;
    }

    public void setUpFlavor(){
        if(BuildConfig.FLAVOR.contains((FLAVOR_SIMPSONS))){
            URL_REQUEST = URL_SIMPSONS;
        }
        else if (BuildConfig.FLAVOR.contains((FLAVOR_FUTURAMA))){
            URL_REQUEST = URL_FUTURAMA;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onHeadlineClickListener = (OnItemClickListener) context;
        } catch (Exception e) {

        }
    }

    public void getData() {

        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_REQUEST, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("RelatedTopics");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ObjectDescriptor od = new ObjectDescriptor();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject icon = jsonObject.getJSONObject("Icon");
                        od.setImageUrl(icon.optString("URL", "Unknown"));
                        od.setDetailUrl(jsonObject.optString("FirstURL", "Unknown") + "&format=json");
                        String[] details = jsonObject.optString("Text", "Unknown").split("-", 2);
                        od.setCharacterName(details[0]);
                        od.setCharacterDetail(details[1]);
                        characterShortDetail.add(od);
                        //Log.d("Result", Integer.toString(characterShortDetail.size()));
                        fillList(view.getContext());
                    }

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

    public void fillList(Context context) {
        CustomAdapter adapter = new CustomAdapter(context, characterShortDetail, getActivity(), onHeadlineClickListener);
        recyclerView.setAdapter(adapter);
    }


}
