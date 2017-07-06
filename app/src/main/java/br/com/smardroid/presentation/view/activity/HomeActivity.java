package br.com.smardroid.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.viniciusromani.cleanteste.R;

import br.com.smardroid.presentation.view.fragment.home_map.HomeMapFragment;
import br.com.smardroid.presentation.view.util.Constants;

/**
 * Created by viniciusromani on 29/06/17.
 */

public class HomeActivity extends BaseActivity {

    private int mapContainerId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mapContainerId = R.id.map_container;
        setFragments();
    }

    /**
     * Helpers
     */
    private void setFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(mapContainerId, new HomeMapFragment(), Constants.FragmentTag.MAP_FRAGMENT_TAG);
        transaction.commitNow();
    }
}
