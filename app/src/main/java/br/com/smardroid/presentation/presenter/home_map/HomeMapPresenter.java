package br.com.smardroid.presentation.presenter.home_map;

import android.location.Location;
import android.support.annotation.Nullable;
import android.util.Log;

import br.com.smardroid.presentation.presenter.BasePresenter;
import br.com.smardroid.presentation.view.BaseView;
import io.reactivex.subscribers.DisposableSubscriber;

import javax.inject.Inject;

import br.com.smardroid.domain.entity.current_location.CurrentLocation;
import br.com.smardroid.domain.interactor.home_map.HomeMapCase;
import br.com.smardroid.presentation.view.HomeMapView;

/**
 * Created by viniciusromani on 05/07/17.
 */

public class HomeMapPresenter extends BasePresenter {

    protected final HomeMapCase homeMapCase;
    @Nullable
    private HomeMapView homeMapView;

    /**
     * Constructor
     */
    @Inject
    public HomeMapPresenter(HomeMapCase mapCase) {
        this.homeMapCase = mapCase;
    }

    /**
     * Setter
     */
    public void setView(HomeMapView view) {
        setBaseView(view);
        this.homeMapView = view;
    }

    /**
     * Business logic
     */
    public void retrieveUserCurrentLocation(Location lastKnownLocation) {
        this.homeMapCase.retrieveUserLocation(lastKnownLocation);
        this.homeMapCase.execute(new HomeMapPresenter.UserLocationSubscriber());
    }

    /**
     * Subscribers
     */
    private final class UserLocationSubscriber extends DisposableSubscriber<CurrentLocation> {
        @Override
        public void onComplete() { }

        @Override
        public void onNext(CurrentLocation currentLocation) {
            if (homeMapView != null) {
                homeMapView.setCurrentLocation("Teste " + currentLocation.getCityName());
            }
        }

        @Override
        public void onError(Throwable t) { }
    }
}
