package br.com.smardroid.presentation.view.fragment.map;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.ActionBar;

import com.example.viniciusromani.cleanteste.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import br.com.smardroid.presentation.view.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by viniciusromani on 29/06/17.
 */

@RuntimePermissions
public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    /**
     * Binds
     */
    @BindView(R.id.map_view)
    protected MapView mapView;

    /**
     * Variable
     */
    private GoogleMap map;

    /**
     * View life cycle methods
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);
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
        MapFragmentPermissionsDispatcher.onMapReadyWithCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MapFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * Permission for location
     */
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
            map.setMyLocationEnabled(true);
        } catch (SecurityException se) {
            Log.d("MapFragment", "MapFragment - Security Exception");
        }
    }
}
