package br.com.smardroid.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by viniciusromani on 29/06/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    /*
    @Override
    public void showSnackBarMessage(@NonNull Activity activity, @IdRes int baseViewGroupId, String message, SnackBarType snackBarType) {
        @DrawableRes int icon;
        @ColorRes int backgroundColor;
        if (snackBarType == SnackBarType.SUCCESS) {
            icon = R.drawable.ic_check_circle_white_24dp;
            backgroundColor = R.color.success;
        } else {
            icon = R.drawable.ic_error_white_24dp;
            backgroundColor = R.color.error;
        }
        Utilities.showTopSnackBar(activity, baseViewGroupId, icon, message, R.color.white, backgroundColor);
    }

    @Override
    public void showErrorDialog(ErrorDisplay errorDisplay) {
        if (!isActivityVisible()) {
            return;
        }

        if (isShowingFragmentDialog) {
            return;
        }

        dismissCustomDialog();
        mCustomDialog = CustomDialog.create(this)
                .withTitle(errorDisplay.getTitle())
                .withText(errorDisplay.getMessage());

        ErrorButton positiveButton = errorDisplay.getPositiveButton();

        if (positiveButton != null) {
            mCustomDialog.withPosButton(positiveButton.getLabel(), (dialogInterface, which) -> {
                dialogInterface.dismiss();
                handleErrorButtonAction(positiveButton);
            });
        }

        ErrorButton negativeButton = errorDisplay.getNegativeButton();

        if (negativeButton != null) {
            mCustomDialog.withNegButton(negativeButton.getLabel(), (dialogInterface, which) -> {
                dialogInterface.dismiss();
                handleErrorButtonAction(negativeButton);
            });
        }

        mCustomDialog.setCancelable(false);

        mCustomDialog.show();
    }

    private void handleErrorButtonAction(ErrorButton errorButton) {
        switch (errorButton.getAction()) {
            case NAVIGATE_BACK:
                finish();
                break;
            case LOGOUT:
                goToLandingPage(true);
                break;
            case CUSTOM:
                errorButton.getCustomAction().run();
                break;
            default:
                break;
        }
    }

    private void dismissCustomDialog() {
        if (mCustomDialog != null && mCustomDialog.isShowing()) {
            mCustomDialog.dismiss();
            mCustomDialog = null;
        }
    }*/
}
