package ihardy.im.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import ihardy.im.BaseActivity;
import ihardy.im.R;
import ihardy.im.URLConstant;
import ihardy.im.httputils.H;
import ihardy.im.httputils.HL;
import ihardy.im.preference.P;

/**
 * Created by Administrator on 2016/1/8.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isKeep = P.getInstance().getIsHold();
        if (isKeep) {
            setKeep(isKeep);
            ed_login_username.setText(P.getInstance().getStr(P.USER_NAME));
            ed_login_pass.setText(P.getInstance().getStr(P.PASSWORD));
        }
    }

    @ViewInject(R.id.ed_login_username)
    EditText ed_login_username;
    @ViewInject(R.id.ed_login_pass)
    EditText ed_login_pass;
    @ViewInject(R.id.tv_login_iskeep)
    TextView tv_login_iskeep;
    @ViewInject(R.id.iv_login_iskeep)
    ImageView iv_login_iskeep;

    boolean isKeep = false;

    @Event(value=R.id.tv_login_register)
    private void registerClick(View view){
        startActivity(new Intent(context,RegisterActivity.class));
    }

    @Event(value = R.id.iv_login_iskeep)
    private void isKeepIvClick(View view) {
        isKeep = !isKeep;
        setKeep(isKeep);
    }

    @Event(value = R.id.tv_login_iskeep)
    private void isKeepTvClick(View view) {
        isKeep = !isKeep;
        setKeep(isKeep);
    }

    @Event(value = R.id.btn_login)
    private void loginClick(View view) {
        login(ed_login_username.getText().toString(), ed_login_pass.getText().toString());
    }


    private void setKeep(boolean is) {
        if (is) {
            setKeepBg(R.mipmap.login_check_box_true, R.color.colorMain);
        } else {
            setKeepBg(R.mipmap.login_check_box_false, R.color.colorMainGray);
        }
    }

    private void setKeepBg(int bg, int color) {
        iv_login_iskeep.setBackgroundResource(bg);
        tv_login_iskeep.setTextColor(getResources().getColor(color));
    }

    private void login(String user, String pass) {
        if (user.equals("") || pass.equals("")) {
            Toast.makeText(context, "用户/密码，不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams r = new RequestParams(URLConstant.USER.LOGINGETSTATION);
        r.addBodyParameter("u", user);
        r.addBodyParameter("p", pass);
        H h = new H(context, r, "登陆中...");
        h.setOnHL(new LoginHL());
    }

    class LoginHL implements HL {

        @Override
        public void onSuccess(JSONObject result) {
            P.getInstance().setIsHold(isKeep);
            if (isKeep) {
                setP(ed_login_username.getText().toString(), ed_login_pass.getText().toString());
            } else {
                setP("", "");
            }

            startActivity(new Intent(context,MainActivity.class));
        }
    }

    private void setP(String s1, String s2) {
        P.getInstance().setStr(P.USER_NAME, s1);
        P.getInstance().setStr(P.PASSWORD, s2);
    }


}
