package br.com.smardroid.presentation.view;

import android.location.Location;

/**
 * Created by viniciusromani on 29/06/17.
 */

public interface HomeMapView extends BaseView {
    void updateLocation(Location location);
    void setAddress(String address);
}
