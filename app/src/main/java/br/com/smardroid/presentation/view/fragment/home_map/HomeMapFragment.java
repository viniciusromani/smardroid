package br.com.smardroid.presentation.view.fragment.home_map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viniciusromani.cleanteste.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import javax.inject.Inject;

import br.com.smardroid.presentation.presenter.home_map.HomeMapPresenter;
import br.com.smardroid.presentation.view.HomeMapView;
import br.com.smardroid.presentation.view.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static br.com.smardroid.presentation.view.util.Constants.Broadcast.BROADCAST_CURRENT_LOCATION;

/**
 * Created by viniciusromani on 05/07/17.
 */

@RuntimePermissions
public class HomeMapFragment extends BaseFragment implements OnMapReadyCallback, HomeMapView {

    /**
     * Binds
     */
    @BindView(R.id.map_view)
    protected MapView mapView;

    /**
     * Variables
     */
    private GoogleMap map;
    @Inject
    HomeMapPresenter homeMapPresenter;

    /**
     * View life cycle methods
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);
        getApplicationComponent().inject(this);

        homeMapPresenter.setView(this);

        setBroadcastReceiver(BROADCAST_CURRENT_LOCATION).subscribe(this::retrieveCurrentLocation);//, message -> Log.d("HomeMapFragment", "HomeMapFragment " + message));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        hideStatusBar();
    }

    /**
     * OnMapReadyCallback delegate
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Checking permission
        HomeMapFragmentPermissionsDispatcher.onMapReadyWithCheck(this);

        // Sending broadcast to get location
        sendBroadcast(BROADCAST_CURRENT_LOCATION);
    }

    /**
     * HomeMapView delegate
     */
    @Override
    public void setCurrentLocation(String location) {
        Log.d("HomeMapFragment", "Current location " + location);
    }

    /**
     * Broadcast methods
     */
    private void retrieveCurrentLocation(@Nullable Intent intent) {
        // Checks if it has permission and if provider is enabled
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (getContext() != null && ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Location lastKnownLocation = new Location("");
            lastKnownLocation.setLatitude(-22.0166);
            lastKnownLocation.setLongitude(-47.8971);
            homeMapPresenter.retrieveUserCurrentLocation(lastKnownLocation);
        }
    }

    /**
     * Permission for location
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HomeMapFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION })
    void onMapReady() { onMapReadyAfterPermissionChecked(); }

    @OnShowRationale({ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION })
    void showRationaleForMap(final PermissionRequest request) {

        /*
        if (!getUserVisibleHint()) return;

        CustomDialog.create(getContext())
                .withTitle(R.string.permission_location_title)
                .withText(R.string.permission_location_denied)
                .withPosButton(R.string.permission_allow, (dialog, button) -> request.proceed())
                .withNegButton(R.string.permission_deny, (dialog, button) -> request.cancel())
                .show();*/
    }

    @OnPermissionDenied({ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION })
    void showDeniedForMap() {
        if (!getUserVisibleHint()) return;

        //animateToMyLocation();
        showSnackBar(getString(R.string.permission_location_denied), Snackbar.LENGTH_SHORT);
    }

    @OnNeverAskAgain({ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION })
    void showNeverAskForMap() {
        if (!getUserVisibleHint()) return;

        //animateToMyLocation();
        showSnackBar(getString(R.string.permission_location_never_ask_again), Snackbar.LENGTH_LONG);
    }

    /**
     * Helpers
     */
    private void hideStatusBar() {
        View decorView = getActivity().getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide action bar.
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) { actionBar.hide(); }
    }

    private void showSnackBar(String message, int snackbarLength) {
        showSnackBarMessage(getView(), message, snackbarLength);
    }

    private void onMapReadyAfterPermissionChecked() {
        try {
            // map stuff
            map.setMyLocationEnabled(true);
        } catch (SecurityException se) {
            Log.d("MapFragment", "MapFragment - Security Exception");
        }
    }
}
