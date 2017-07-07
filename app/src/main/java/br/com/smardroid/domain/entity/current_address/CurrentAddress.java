package br.com.smardroid.domain.entity.current_address;

/**
 * Created by viniciusromani on 07/07/17.
 */

public class CurrentAddress {
    private String cityName;

    /**
     * Constructors
     */
    public static CurrentAddress create() {
        return new CurrentAddress();
    }
    public CurrentAddress withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    /**
     * Getters
     */
    public String getCityName() {
        return this.cityName;
    }
}
