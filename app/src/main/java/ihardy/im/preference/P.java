package ihardy.im.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/1/11.
 */
public class P {
    private static SharedPreferences mSharedPreferences;
    private static P mPreferencemManager;
    private static SharedPreferences.Editor editor;

    public static final String PREFERENCE_NAME = "saveInfo";

    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";


    private P(Context cxt) {
        mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public static synchronized void init(Context cxt) {
        if (mPreferencemManager == null) {
            mPreferencemManager = new P(cxt);
        }
    }

    /**
     * 单例模式，获取instance实例
     *
     * @param cxt
     * @return
     */
    public synchronized static P getInstance() {
        if (mPreferencemManager == null) {
            throw new RuntimeException("please init first!");
        }
        return mPreferencemManager;
    }

    public String getStr(String tag) {
        return mSharedPreferences.getString(tag, "");
    }

    public void setStr(String tag, String value) {
        editor.putString(tag, value);
        editor.commit();
    }

    public boolean getIsHold() {
        return mSharedPreferences.getBoolean("isHold", true);
    }

    public void setIsHold(boolean isHold) {
        editor.putBoolean("isHold", isHold);
        editor.commit();
    }

}
