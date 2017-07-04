package br.com.smardroid.presentation.view.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by viniciusromani on 04/07/17.
 */

public class Utilities {

    public static void showSnackBar(View view,
                                    String message,
                                    int snackbarLength) {

        Snackbar snackbar = Snackbar.make(view, message, snackbarLength);
        snackbar.show();
    }
}
