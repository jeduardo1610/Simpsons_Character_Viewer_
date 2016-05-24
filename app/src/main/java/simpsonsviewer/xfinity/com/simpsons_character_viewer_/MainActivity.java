package simpsonsviewer.xfinity.com.simpsons_character_viewer_;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;

import java.io.Serializable;
import java.util.HashMap;

import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Activities.DetailActivity;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Controller.OnItemClickListener;
import simpsonsviewer.xfinity.com.simpsons_character_viewer_.Fragments.DetailFragment;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private final String TITLE_SIMPSONS = "Simpsons Character Viewer";
    private final String TITLE_FUTURAMA = "Futurama Character Viewer";
    private final String FLAVOR_SIMPSONS = "simpsons";
    private final String FLAVOR_FUTURAMA = "futurama";
    private final String TEST_BRANCH = "Test-Branch";
    private final String TEST_BRANCH_2 = "Test-Branch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BuildConfig.FLAVOR.contains(FLAVOR_SIMPSONS)){
            setTitle(TITLE_SIMPSONS);
        }
        else if(BuildConfig.FLAVOR.contains(FLAVOR_FUTURAMA)){
            setTitle(TITLE_FUTURAMA);

        }

    }

    @Override
    public void itemLineSelected(Activity activity, HashMap<String, String> detail, String urlImage, ImageLoader image, String name) {

        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);


        if (detailFragment == null) {

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("detail", (Serializable) detail);
            intent.putExtra("url", urlImage);
            intent.putExtra("name", name);
            startActivity(intent);
        } else {
            detailFragment.cleanUp();
            detailFragment.setUp(this, detail, urlImage, image, name);
        }
    }

}

