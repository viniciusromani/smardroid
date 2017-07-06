package br.com.smardroid.presentation;

import android.support.multidex.MultiDexApplication;

import br.com.smardroid.presentation.internal.ApplicationComponent;
import br.com.smardroid.presentation.internal.ApplicationModule;
import br.com.smardroid.presentation.internal.DaggerApplicationComponent;

/**
 * Created by viniciusromani on 06/07/17.
 */

public class AndroidApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    /**
     * Life cycle methods
     */
    @Override
    public void onCreate() {
        super.onCreate();
        initDependencyInjectionModule();
        getApplicationComponent().inject(this);
    }

    /**
     * Getters
     */
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    /**
     * Helpers
     */
    private void initDependencyInjectionModule() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
