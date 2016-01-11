package ihardy.im.httputils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import ihardy.im.Constant;
import ihardy.im.dialog.HttpProgressDialog;
import ihardy.im.utils.Utils;


/**
 * Created by Administrator on 2016/1/4.
 */
public class H {

    protected Context context;
    protected RequestParams requestParams;
    protected HL onHL;
    protected int int_return = 2;
    protected String str;

    public H(Context c, RequestParams r, String s) {
        this.context = c;
        this.requestParams = r;
        this.str = s;
    }

    public void setReturn(int int_return) {
        this.int_return = int_return;
    }

    public void setOnHL(HL onHL) {
        this.onHL = onHL;
        connect();
    }

    protected void connect() {

        if (onHL == null) {
            Toast.makeText(context, "监听器为空！请检查代码！", Toast.LENGTH_LONG).show();
            return;
        }
        requestParams.addBodyParameter("sn", Utils.getUtils().getSN(context));
        requestParams.addBodyParameter("token", Constant.token);
        HttpProgressDialog.getDialog(context).showProgress(str);
        x.http().post(requestParams, new Callback.CommonCallback<byte[]>() {

            @Override
            public void onSuccess(byte[] result) {
                if (result == null || new String(result).equals("")) {
                    toast("网络异常！");

                    return;
                }
                try {
                    JSONObject jsonResult = new JSONObject(new String(result));
                    int i_return = jsonResult.getInt("return");
                    if (i_return == int_return) {
                        Log.v("onSuccess", jsonResult.toString());
                        Utils.getUtils().hideSoftKeyboard(context);
                        onHL.onSuccess(jsonResult);
                    } else {
                        if (i_return == 999 || i_return == -996) {
                            return;
                        }
                        toast(jsonResult.getString("msg"));
                    }
                } catch (JSONException e) {
                    toast("解析异常！");
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                toast(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                toast(cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.v("HTTP_UTIL", "onFinished()");
            }
        });
    }


    private void toast(String str) {
        HttpProgressDialog.getDialog(context).hideProgress();
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
