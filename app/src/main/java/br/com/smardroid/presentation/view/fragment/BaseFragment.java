package br.com.smardroid.presentation.view.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import br.com.smardroid.presentation.view.BaseView;
import br.com.smardroid.presentation.view.util.Utilities;

/**
 * Created by viniciusromani on 29/06/17.
 */

public class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showSnackBarMessage(View view,
                                    String message,
                                    int snackbarLength) {
        Utilities.showSnackBar(view, message, snackbarLength);
    }
}
