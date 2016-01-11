package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

/**
 * 年份选择器
 *
 * @author 李玉江[QQ:1032694760]
 * @since 2015/12/15
 * Created By Android Studio
 */
public class YearPicker extends OptionPicker {

    public YearPicker(FragmentActivity activity) {
        super(activity, new String[]{});
    }

    public void setRange(int startYear, int endYear) {
        options.clear();
        for (int i = startYear; i <= endYear; i++) {
            options.add(String.valueOf(i));
        }
    }

}