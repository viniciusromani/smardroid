package br.com.smardroid.data.repository;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;

import java.util.Locale;

import javax.inject.Inject;

import br.com.smardroid.data.mapper.CurrentLocationMapper;
import br.com.smardroid.domain.entity.current_location.CurrentLocation;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.BackpressureStrategy;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import hu.akarnokd.rxjava.interop.RxJavaInterop;

/**
 * Created by viniciusromani on 04/07/17.
 */

public class GoogleRepository {

    private final Context context;
    private final CurrentLocationMapper currentLocationMapper;
    private final Resources resources;

    /**
     * Constructor
     */
    @Inject
    public GoogleRepository(Context context, Resources resources) {
        this.context = context;
        this.resources = resources;
        this.currentLocationMapper = new CurrentLocationMapper();
    }

    /**
     * Business logic methods
     */
    public Flowable<CurrentLocation> retrieveUserCurrentLocation(Location lastKnownLocation) {
        
        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(context);
        return RxJavaInterop.toV2Flowable(locationProvider.getReverseGeocodeObservable(getCurrentLocale(), lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 1)
                .flatMap(addresses -> rx.Observable.just(currentLocationMapper.transform(addresses))));
    }

    /**
     * Helper
     */
    @TargetApi(Build.VERSION_CODES.N)
    private Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return resources.getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return resources.getConfiguration().locale;
        }
    }
}
