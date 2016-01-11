package ihardy.im.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import ihardy.im.BaseActivity;
import ihardy.im.Constant;


public class Utils {
    private static Utils instance;

    private TelephonyManager tm;

    private Utils() {
    }

    public static Utils getUtils() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public String getSN(Context context) {
        if (Constant.SN == null) {
            if (tm == null) {
                tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
            }
            Constant.SN = tm.getDeviceId();
        }
        return Constant.SN;
    }

    public int getWidth(Context context) {

        if (Constant.WM_WIDTH == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay()
                    .getMetrics(dm);
            Constant.WM_WIDTH = dm.widthPixels;
        }
        Log.v("Utils", "getWidth=" + Constant.WM_WIDTH);
        return Constant.WM_WIDTH;
    }

    public int getHeight(Context context) {
        if (Constant.WM_HEIGHT == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay()
                    .getMetrics(dm);
            Constant.WM_HEIGHT = dm.heightPixels;
        }
        Log.v("Utils", "getHeight=" + Constant.WM_HEIGHT);
        return Constant.WM_HEIGHT;
    }

    public void hideSoftKeyboard(Context context) {
        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager) ((BaseActivity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
        if (((BaseActivity) context).getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (((BaseActivity) context).getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(((BaseActivity) context).getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


//	public LatLng gcj02_To_Bd09(double gg_lat, double gg_lon) {
//		double pi = 3.1415926535897932384626;
//		double x = gg_lon, y = gg_lat;
//		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
//		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
//		double bd_lon = z * Math.cos(theta) + 0.0065;
//		double bd_lat = z * Math.sin(theta) + 0.006;
//		return new LatLng(bd_lat, bd_lon);
//	}
}
