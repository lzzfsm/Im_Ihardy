package ihardy.im.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import ihardy.im.BaseActivity;
import ihardy.im.R;

/**
 * Created by Administrator on 2016/1/8.
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    @ViewInject(R.id.LinearLayout01)
    RelativeLayout LinearLayout01;

    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        LinearLayout01.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        new Thread(new Runnable() {
//            public void run() {
//                if (***********) {
//                    // ** 免登陆情况 加载所有本地群和会话
//                    //不是必须的，不加sdk也会自动异步去加载(不会重复加载)；
//                    //加上的话保证进了主页面会话和群组都已经load完毕
//                    long start = System.currentTimeMillis();
//                    EMGroupManager.getInstance().loadAllGroups();
//                    EMChatManager.getInstance().loadAllConversations();
//                    long costTime = System.currentTimeMillis() - start;
//                    //等待sleeptime时长
//                    if (sleepTime - costTime > 0) {
//                        try {
//                            Thread.sleep(sleepTime - costTime);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    //进入主页面
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                    finish();
//                }else {
//                    try {
//                        Thread.sleep(sleepTime);
//                    } catch (InterruptedException e) {
//                    }
//                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                    finish();
//                }
//            }
//        }).start();
    }
}
