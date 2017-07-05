package br.com.smardroid.data.mapper;

import android.location.Address;
import android.util.Log;

import java.util.List;
import com.annimon.stream.Stream;

import javax.inject.Inject;

import br.com.smardroid.domain.entity.current_location.CurrentLocation;

/**
 * Created by viniciusromani on 04/07/17.
 */

public class CurrentLocationMapper {

    @Inject
    public CurrentLocationMapper() {
        /* Dagger */
    }

    public CurrentLocation transform(List<Address> addresses) {

        String cityName = "";
        try {
            cityName = Stream.of(addresses).findFirst().get().getLocality();
        } catch (Exception e) {
            Log.d("CurrentLocationMapper", "CurrentLocationMapper - " + e);
        }
        return CurrentLocation.create()
                .withCityName(cityName);
    }
}
