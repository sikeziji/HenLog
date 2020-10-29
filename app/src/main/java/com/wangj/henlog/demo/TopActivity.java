package com.wangj.henlog.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wangj.henlog.R;
import com.wangj.uilib.tab.common.IHenTabLayout;
import com.wangj.uilib.tab.top.HenTabTopInfo;
import com.wangj.uilib.tab.top.HenTabTopLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.henlog.demo
 * @ClassName: TopActivity
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/29 12:01
 * @Version: 1.0
 */
public class TopActivity extends AppCompatActivity {

    String[] tabsStr = new String[]{
            "热门",
            "服装",
            "数码",
            "鞋子",
            "零食",
            "家电",
            "汽车",
            "百货",
            "家居",
            "装修",
            "运动"
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        initTabTop();
    }

    private void initTabTop() {
        HenTabTopLayout henTabTopLayout = findViewById(R.id.tab_top_layout);
        List<HenTabTopInfo<?>> infoList = new ArrayList<>();
        int defaultColor = getResources().getColor(R.color.tabBottomDefaultColor);
        int tintColor = getResources().getColor(R.color.tabBottomTintColor);
        for (String s : tabsStr) {
            HenTabTopInfo<?> info = new HenTabTopInfo<Integer>(s, defaultColor, tintColor);
            infoList.add(info);
        }
        henTabTopLayout.inflateInfo(infoList);
        henTabTopLayout.addTabSelectChangeListener(new IHenTabLayout.OnTabSelectedListener<HenTabTopInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable HenTabTopInfo<?> prevInfo, @NonNull HenTabTopInfo<?> nextInfo) {
                Toast.makeText(TopActivity.this, nextInfo.name, Toast.LENGTH_SHORT).show();
            }
        });
        henTabTopLayout.defaultSelected(infoList.get(0));
    }
}
