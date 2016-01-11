package ihardy.im.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import org.xutils.view.annotation.ContentView;


import ihardy.im.BaseActivity;
import ihardy.im.R;
import ihardy.im.fragment.register.RegisterFirstFragment;
import ihardy.im.fragment.register.RegisterSecondFragment;


/**
 * Created by Administrator on 2016/1/11.
 */

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    private FragmentManager mFragMgr;

    private RegisterFirstFragment r_f_f;
    private RegisterSecondFragment r_s_f;

    private int which = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    private void initFragment() {
        mFragMgr = getSupportFragmentManager();
        r_f_f = new RegisterFirstFragment();
        r_s_f = new RegisterSecondFragment();
        mFragMgr.beginTransaction().add(R.id.vg_register_fragment, r_f_f).add(R.id.vg_register_fragment, r_s_f).hide(r_s_f).show(r_f_f).commit();
    }

    public void showSecondFragment() {
        mFragMgr.beginTransaction().hide(r_f_f).show(r_s_f).commit();
        which = 0;
    }

    public void showFirstFragment() {
        mFragMgr.beginTransaction().hide(r_s_f).show(r_f_f).commit();
        which = 1;
    }

    public void backClick(View view) {
        if (which == 1)
            showFirstFragment();
        else
            finish();
    }


}
