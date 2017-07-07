package br.com.smardroid.domain.entity.current_address;

/**
 * Created by viniciusromani on 07/07/17.
 */

public class CurrentAddress {

    private String countryName;
    private String cityName;
    private String postalCode;

    /**
     * Constructors
     */
    public static CurrentAddress create() {
        return new CurrentAddress();
    }

    /**
     * Builder setters
     */
    public CurrentAddress withCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }
    public CurrentAddress withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }
    public CurrentAddress withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Getters
     */
    public String getCountryName() { return this.countryName; }
    public String getCityName() {
        return this.cityName;
    }
    public String getPostalCode() { return this.postalCode; }
}
