package ihardy.im.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

public class HttpProgressDialog {
    public static Context context;
    public static HttpProgressDialog instance;
    private ProgressDialog pd;

    private HttpProgressDialog(Context context) {
        this.context = context;
    }

    public static HttpProgressDialog getDialog(Context context) {
        //以上下文为依据的单例模式，无需在Activity中创建Dialog
        if(HttpProgressDialog.context!=context)
            instance = new HttpProgressDialog(context);
        return instance;
    }

    public void showProgress(String str) {
        if (pd == null) {
            pd = new ProgressDialog(context);
            pd.setCanceledOnTouchOutside(false);
            pd.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    hideProgress();
                }
            });
        }
        pd.setMessage(str);
        pd.show();
    }

    public void hideProgress() {
        if (pd != null)
            pd.dismiss();
    }
}

