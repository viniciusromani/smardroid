package br.com.smardroid.data.mapper.current_address;

import android.location.Address;
import android.util.Log;

import com.annimon.stream.Stream;

import java.util.List;

import javax.inject.Inject;

import br.com.smardroid.domain.entity.current_address.CurrentAddress;

/**
 * Created by viniciusromani on 07/07/17.
 */

public class CurrentAddressMapper {
    @Inject
    public CurrentAddressMapper() {
        /* Dagger */
    }

    public CurrentAddress transform(List<Address> addresses) {

        String cityName = "";
        try {
            cityName = Stream.of(addresses).findFirst().get().getLocality();
        } catch (Exception e) {
            Log.d("CurrentLocationMapper", "CurrentLocationMapper - " + e);
        }
        return CurrentAddress.create()
                .withCityName(cityName);
    }
}
