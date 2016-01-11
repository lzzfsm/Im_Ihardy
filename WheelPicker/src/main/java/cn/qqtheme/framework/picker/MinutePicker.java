package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import cn.qqtheme.framework.util.DateUtils;

/**
 * 分钟选择器
 *
 * @author 李玉江[QQ:1032694760]
 * @since 2015/12/15
 * Created By Android Studio
 */
public class MinutePicker extends OptionPicker {

    public MinutePicker(FragmentActivity activity) {
        super(activity, new String[]{});
        for (int i = 0; i < 60; i++) {
            options.add(DateUtils.fillZero(i));
        }
    }

}