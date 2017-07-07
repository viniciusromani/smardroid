package br.com.smardroid.presentation.presenter.home_map;

import android.location.Location;
import android.support.annotation.Nullable;

import br.com.smardroid.domain.entity.current_address.CurrentAddress;
import br.com.smardroid.presentation.presenter.BasePresenter;
import io.reactivex.subscribers.DisposableSubscriber;

import javax.inject.Inject;

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
    public void retrieveAddress(Location location) {
        this.homeMapCase.retrieveAddress(location);
        this.homeMapCase.execute(new HomeMapPresenter.AddressSubscriber());
    }

    /**
     * Subscribers
     */
    private final class AddressSubscriber extends DisposableSubscriber<CurrentAddress> {
        @Override
        public void onComplete() { }

        @Override
        public void onNext(CurrentAddress address) {
            if (homeMapView != null) {
                homeMapView.setAddress(address.getCityName());
            }
        }

        @Override
        public void onError(Throwable t) { }
    }
}
