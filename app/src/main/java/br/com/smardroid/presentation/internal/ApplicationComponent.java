package br.com.smardroid.presentation.internal;

import javax.inject.Singleton;

import br.com.smardroid.presentation.AndroidApplication;
import br.com.smardroid.presentation.view.activity.BaseActivity;
import br.com.smardroid.presentation.view.fragment.home_map.HomeMapFragment;
import dagger.Component;

/**
 * Created by viniciusromani on 06/07/17.
 */

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

    // Application
    void inject(AndroidApplication androidApplication);

    // BaseActivity
    void inject(BaseActivity baseActivity);

    // HomeMapFragment
    void inject(HomeMapFragment homeMapFragment);
}
