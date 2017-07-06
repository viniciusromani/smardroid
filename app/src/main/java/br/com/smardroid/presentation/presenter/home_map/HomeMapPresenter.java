package br.com.smardroid.presentation.presenter.home_map;

import android.location.Location;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.MapView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import br.com.smardroid.domain.entity.current_location.CurrentLocation;
import br.com.smardroid.domain.interactor.home_map.HomeMapCase;
import br.com.smardroid.presentation.view.HomeMapView;

/**
 * Created by viniciusromani on 05/07/17.
 */

public class HomeMapPresenter {

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


    /**
     * Business logic
     */
    private void getUserCurrentLocation(Location lastKnownLocation) {
        this.homeMapCase.getUserLocation(lastKnownLocation);
        this.homeMapCase.execute(new HomeMapPresenter.UserLocationSubscriber());
    }

    /**
     * Subscribers
     */
    private final class UserLocationSubscriber implements Subscriber<CurrentLocation> {
        @Override
        public void onSubscribe(Subscription s) { }

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
