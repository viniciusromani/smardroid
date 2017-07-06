package br.com.smardroid.presentation.internal;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.smardroid.data.repository.GoogleRepository;
import br.com.smardroid.domain.executor.ThreadExecutor;
import br.com.smardroid.domain.interactor.home_map.HomeMapCase;
import br.com.smardroid.presentation.AndroidApplication;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by viniciusromani on 06/07/17.
 */

@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    /**
     * Resolving injects
     */
    // Context
    @Provides
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    // Resources
    @Provides
    Resources provideResources(Context context) {
        return context.getResources();
    }

    // Thread executor
    @Provides
    @Singleton
    @Named("subscriberOn")
    ThreadExecutor provideSubscriberOnThreadExecutor() {
        return new ThreadExecutor(Schedulers.newThread());
    }

    @Provides
    @Singleton
    @Named("observerOn")
    ThreadExecutor provideObserverOnExecutionThread() {
        return new ThreadExecutor(AndroidSchedulers.mainThread());
    }

    // Repository
    @Provides
    @Singleton
    GoogleRepository provideGoogleRepository(final Context context, final Resources resources) {
        return new GoogleRepository(context, resources);
    }

    // Case
    @Provides
    @Singleton
    HomeMapCase provideLandingCase(@Named("subscriberOn") final ThreadExecutor subscriberOn,
                                   @Named("observerOn") final ThreadExecutor observerOn,
                                   final GoogleRepository googleRepository) {
        return new HomeMapCase(subscriberOn, observerOn, googleRepository);
    }

}
