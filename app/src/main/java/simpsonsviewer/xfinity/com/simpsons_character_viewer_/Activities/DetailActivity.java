package simpsonsviewer.xfinity.com.simpsons_character_viewer_.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import com.android.volley.toolbox.ImageLoader;
import java.util.HashMap;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Controller.CustomVolleyRequest;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Fragments.DetailFragment;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.R;

public class DetailActivity extends AppCompatActivity {
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        HashMap<String,String> detail = (HashMap<String, String>) intent.getSerializableExtra("detail");
        String url = intent.getStringExtra("url");
        String name = intent.getStringExtra("name");
        setTitle(name);

        imageLoader = CustomVolleyRequest.getInstance(getApplicationContext())
                .getImageLoader();
        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        detailFragment.cleanUp();
        detailFragment.setUp(this,detail,url,imageLoader,name);
    }


}
