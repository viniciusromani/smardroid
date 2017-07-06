package br.com.smardroid.presentation.view.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cantrowitz.rxbroadcast.RxBroadcast;

import java.util.Set;

import br.com.smardroid.presentation.internal.ApplicationComponent;
import br.com.smardroid.presentation.view.BaseView;
import br.com.smardroid.presentation.view.activity.BaseActivity;
import br.com.smardroid.presentation.view.util.ViewUtilities;

import io.reactivex.Flowable;
import io.reactivex.BackpressureStrategy;

/**
 * Created by viniciusromani on 29/06/17.
 */

public class BaseFragment extends Fragment implements BaseView {

    /**
     * View life cycle
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Application component
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((BaseActivity) getActivity()).getApplicationComponent();
    }

    /**
     * BaseView methods
     */
    @Override
    public void showSnackBarMessage(View view,
                                    String message,
                                    int snackbarLength) {
        ViewUtilities.showSnackBar(view, message, snackbarLength);
    }

    /**
     * Setting broadcast methods
     */
    protected Flowable<Intent> setBroadcastReceiver(String broadCastTag) {
        IntentFilter intentFilter = new IntentFilter(broadCastTag);
        return RxBroadcast.fromBroadcast(getContext(), intentFilter).toFlowable(BackpressureStrategy.LATEST);
    }

    protected Flowable<Intent> setBroadcastReceiver(String broadCastTag, String category) {
        IntentFilter intentFilter = new IntentFilter(broadCastTag);
        intentFilter.addCategory(category);
        return RxBroadcast.fromBroadcast(getContext(), intentFilter).toFlowable(BackpressureStrategy.LATEST);
    }

    protected Flowable<Intent> setBroadcastReceiver(String broadCastTag, Set<String> categories) {
        IntentFilter intentFilter = new IntentFilter(broadCastTag);

        for (String category : categories) {
            intentFilter.addCategory(category);
        }

        return RxBroadcast.fromBroadcast(getContext(), intentFilter).toFlowable(BackpressureStrategy.LATEST);
    }

    /**
     * Sending broadcast methods
     */
    protected void sendBroadcast(String broadcastTag) {
        Intent intent = new Intent(broadcastTag);
        getContext().sendBroadcast(intent);
    }
}
