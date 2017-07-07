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

        String countryName = "";
        String cityName = "";
        String postalCode = "";

        try {
            countryName = Stream.of(addresses).findFirst().get().getCountryName();
            cityName = Stream.of(addresses).findFirst().get().getLocality();
            postalCode = Stream.of(addresses).findFirst().get().getPostalCode();
        } catch (Exception e) {
            Log.d("CurrentLocationMapper", "CurrentLocationMapper - " + e);
        }

        return CurrentAddress.create()
                .withCountryName(countryName)
                .withCityName(cityName)
                .withPostalCode(postalCode);
    }
}
