package br.com.smardroid.presentation.view;

import android.view.View;

/**
 * Created by viniciusromani on 04/07/17.
 */

public interface BaseView {
    void showSnackBarMessage(View view,
                             String message,
                             int snackbarLength);
}
