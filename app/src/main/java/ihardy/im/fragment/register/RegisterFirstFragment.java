package ihardy.im.fragment.register;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.util.AssetsUtils;
import ihardy.im.BaseFragment;
import ihardy.im.R;

/**
 * Created by Administrator on 2016/1/11.
 */
@ContentView(R.layout.fragment_register_first)
public class RegisterFirstFragment extends BaseFragment {
    @ViewInject(R.id.tv_register_first_date)
    TextView tv_date;

    @Event(value = R.id.tv_register_first_date)
    private void dateClick(View view) {
        String str = tv_date.getText().toString();
        DatePicker picker = new DatePicker(getActivity());
        picker.setRange(1965, 2015);//年份范围
        picker.setSelectedItem(Integer.valueOf(str.substring(0, 4)), Integer.valueOf(str.substring(5, 7)), Integer.valueOf(str.substring(8, 10)));
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                tv_date.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    @ViewInject(R.id.tv_register_first_education)
    TextView tv_education;

    String[] edu_str_s = new String[]{"文盲", "小学", "初中", "高中", "职中", "大专", "本科", "硕士", "博士"};

    @Event(value = R.id.tv_register_first_education)
    private void educatiClick(View view) {
        OptionPicker picker = new OptionPicker(getActivity(), edu_str_s);
        picker.setOffset(1);
        picker.setTextSize(20);
        picker.setSelectedIndex(1);
        for (int i = 0; i < edu_str_s.length; i++)
            if (tv_education.getText().toString().equals(edu_str_s[i])) {
                picker.setSelectedIndex(i);
                break;
            }
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(String option) {
                tv_education.setText(option);
            }
        });
        picker.show();
    }

    @Event(value = R.id.tv_register_first_city)
    private void cityClick(View view) {
        ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
        String json = AssetsUtils.readText(getActivity(), "city.json");
        data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
        AddressPicker picker = new AddressPicker(getActivity(), data);
        //picker.setHideProvince(true);
        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(String province, String city, String county) {
                Toast.makeText(getActivity(), province + city + county, Toast.LENGTH_SHORT).show();
            }
        });
        picker.show();
    }
}
