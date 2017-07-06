package br.com.smardroid.presentation.presenter;

import android.view.View;

import br.com.smardroid.presentation.view.BaseView;

/**
 * Created by viniciusromani on 05/07/17.
 */

public class BasePresenter {

    private BaseView baseView;

    /**
     * Setter
     */
    public void setBaseView(BaseView baseView) {
        this.baseView = baseView;
    }

    /**
     * Business logic
     */
    protected void showSnackBarMessage(View view,
                                       String message,
                                       int snackbarLength) {
        if (baseView != null) {
            baseView.showSnackBarMessage(view, message, snackbarLength);
        }
    }
}
