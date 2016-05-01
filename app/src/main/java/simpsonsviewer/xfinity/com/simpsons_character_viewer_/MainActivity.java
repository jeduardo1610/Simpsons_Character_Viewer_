package simpsonsviewer.xfinity.com.simpsons_character_viewer_;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.toolbox.ImageLoader;
import java.io.Serializable;
import java.util.HashMap;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Activities.DetailActivity;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Controller.OnHeadlineClickListener;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Fragments.DetailFragment;

public class MainActivity extends AppCompatActivity implements OnHeadlineClickListener {

    private final String TITLE = "Simpsons Character Viewer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITLE);
    }

    @Override
    public void headLineSelected(Activity activity, HashMap<String, String> detail, String urlImage, ImageLoader image, String name) {

        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);


        if(detailFragment == null){

            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra("detail",(Serializable) detail);
            intent.putExtra("url",urlImage);
            intent.putExtra("name",name);
            startActivity(intent);
        }else{
            detailFragment.cleanUp();
            detailFragment.setUp(this,detail,urlImage,image,name);
        }
    }
}
