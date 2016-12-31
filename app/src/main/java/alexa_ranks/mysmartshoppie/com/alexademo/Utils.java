package alexa_ranks.mysmartshoppie.com.alexademo;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.appbrain.AdId;
import com.appbrain.AppBrain;
import com.appbrain.AppBrainBanner;

import java.util.Random;

public class Utils {

    public static final String APPBRAIN_TEST_DEVICE_ID1 = "a9723260f3761cd6";
    public static final String APPBRAIN_TEST_DEVICE_ID2 = "feefac27f6b97409";

    public static void addAppBrainBanner(Context context, RelativeLayout parentView) {
        initAppBrain(context);
        int r = new Random().nextInt(5);
        AppBrainBanner appBrainBanner = new AppBrainBanner(context);
        appBrainBanner.setColors(r);
        appBrainBanner.setDesign(r);
        appBrainBanner.setTitleIndex(1);
        appBrainBanner.setAdId(AdId.DEFAULT);
        appBrainBanner.setSize(AppBrainBanner.BannerSize.RESPONSIVE);
        parentView.removeAllViews();
        parentView.addView(appBrainBanner, getLayoutParams());
    }

    public static void initAppBrain(Context context) {
        AppBrain.addTestDevice(APPBRAIN_TEST_DEVICE_ID1);
        AppBrain.addTestDevice(APPBRAIN_TEST_DEVICE_ID2);
        AppBrain.init(context);
    }

    private static RelativeLayout.LayoutParams getLayoutParams() {
        final RelativeLayout.LayoutParams bannerParameters =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bannerParameters.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        return bannerParameters;
    }

    public static void showMessage(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
