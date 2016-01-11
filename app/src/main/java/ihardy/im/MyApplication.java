package ihardy.im;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.easemob.chat.EMChat;
import com.easemob.easeui.controller.EaseUI;


import org.xutils.x;

import java.util.Iterator;
import java.util.List;

import cn.qqtheme.framework.popup.Popup;
import ihardy.im.preference.P;

/**
 * Created by Administrator on 2015/12/30.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase("ihardy.im")) {
            Log.e("MyApplication", "enter the service process!");
            return;
        }
       // CrashReport.initCrashReport(this, "900017484", false);
        x.Ext.init(this);
        //  x.Ext.setDebug(true); // 是否输出debug日志
//        SDKInitializer.initialize(this);
        EaseUI.getInstance().init(this);

        EMChat.getInstance().init(this);
        /**
         * debugMode == true 时为打开，sdk 会在log里输入调试信息
         * @param debugMode
         * 在做代码混淆的时候需要设置成false
         */
        EMChat.getInstance().setDebugMode(true);//在做打包混淆时，要关闭debug模式，避免消耗不必要的资源
        P.init(this);
        Popup.setAnimation(R.style.Animation_CustomPopup);
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                Log.d("MyApplication", "Error>> :" + e.toString());
            }
        }
        return processName;
    }
}
