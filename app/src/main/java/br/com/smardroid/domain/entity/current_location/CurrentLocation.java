package br.com.smardroid.domain.entity.current_location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by viniciusromani on 04/07/17.
 */

public class CurrentLocation {

    private String cityName;
    private LatLng coordinate;

    /**
     * Constructors
     */
    public static CurrentLocation create() {
        return new CurrentLocation();
    }
    public CurrentLocation withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    /**
     * Getters
     */
    public String getCityName() {
        return this.cityName;
    }
    public LatLng getCoordinate() {
        return this.coordinate;
    }
}
