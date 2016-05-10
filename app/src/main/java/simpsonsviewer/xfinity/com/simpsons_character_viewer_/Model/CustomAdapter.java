package simpsonsviewer.xfinity.com.simpsons_character_viewer_.Model;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Controller.OnItemClickListener;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.R;


/**
 * Created by m14x on 04/29/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<ObjectDescriptor> content = new ArrayList<>();
    private Activity activity;
    OnItemClickListener listener;

    public CustomAdapter(Context context, List<ObjectDescriptor> content, Activity activity, OnItemClickListener listener) {
        this.context = context;
        this.content = content;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view, context, activity, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        ObjectDescriptor od = content.get(position);
        holder.setItem(od);

    }

    @Override
    public int getItemCount() {
        return content.size();
    }
}
