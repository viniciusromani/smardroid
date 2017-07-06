package br.com.smardroid.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.smardroid.presentation.AndroidApplication;
import br.com.smardroid.presentation.internal.ApplicationComponent;

/**
 * Created by viniciusromani on 29/06/17.
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * View life cycle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    /**
     * Application Component - inject annotations
     */
    public ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }
}
