package br.com.smardroid.domain.interactor.home_map;

import android.location.Location;

import javax.inject.Inject;

import br.com.smardroid.data.repository.GoogleRepository;
import br.com.smardroid.domain.executor.ThreadExecutor;
import br.com.smardroid.domain.interactor.BaseInteractor;
import io.reactivex.Flowable;

/**
 * Created by viniciusromani on 05/07/17.
 */

public class HomeMapCase extends BaseInteractor {

    private GoogleRepository googleRepository;

    /**
     * Constructor
     */
    @Inject
    public HomeMapCase(final ThreadExecutor subscriberOn,
                       final ThreadExecutor observerOn,
                       GoogleRepository googleRepository) {

        super(subscriberOn, observerOn);
        this.googleRepository = googleRepository;
    }

    /**
     * Business logic
     */
    public Flowable retrieveUserLocation(Location lastKnownLocation) {
        return withFlowable(googleRepository.retrieveUserCurrentLocation(lastKnownLocation));
    }
}
