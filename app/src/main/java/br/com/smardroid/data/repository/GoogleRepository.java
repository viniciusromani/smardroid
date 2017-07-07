package br.com.smardroid.data.repository;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;

import java.util.Locale;

import javax.inject.Inject;

import br.com.smardroid.data.mapper.current_address.CurrentAddressMapper;
import br.com.smardroid.domain.entity.current_address.CurrentAddress;
import io.reactivex.Flowable;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import hu.akarnokd.rxjava.interop.RxJavaInterop;

/**
 * Created by viniciusromani on 04/07/17.
 */

public class GoogleRepository {

    private final Context context;
    private final CurrentAddressMapper currentAddressMapper;
    private final Resources resources;

    /**
     * Constructor
     */
    @Inject
    public GoogleRepository(Context context, Resources resources) {
        this.context = context;
        this.resources = resources;
        this.currentAddressMapper = new CurrentAddressMapper();
    }

    /**
     * Business logic methods
     */
    public Flowable<CurrentAddress> retrieveAddress(Location location) {
        
        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(context);
        return RxJavaInterop.toV2Flowable(locationProvider.getReverseGeocodeObservable(getCurrentLocale(), location.getLatitude(), location.getLongitude(), 1)
                .flatMap(addresses -> rx.Observable.just(currentAddressMapper.transform(addresses))));
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
