package com.wangj.henlog.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wangj.henlog.MainActivity;
import com.wangj.henlog.R;
import com.wangj.loglib.util.HenDisplayUtil;
import com.wangj.uilib.tab.bottom.HenTabBottom;
import com.wangj.uilib.tab.bottom.HenTabBottomInfo;
import com.wangj.uilib.tab.bottom.HenTabBottomLayout;
import com.wangj.uilib.tab.common.IHenTabLayout;

import java.util.ArrayList;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.henlog.demo
 * @ClassName: BottomActicity
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/28 11:35
 * @Version: 1.0
 */
public class BottomActicity extends AppCompatActivity {

    private HenTabBottomLayout henTabBottomLayout;
    private ArrayList<HenTabBottomInfo<?>> henTabBottomInfoList;
    private HenTabBottomInfo<String> homeInfo;
    private HenTabBottomInfo<String> infoCategory;
    private HenTabBottomInfo<String> infoProfile;
    private HenTabBottomInfo<String> infoChat;
    private HenTabBottomInfo<String> infoRecommend;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hen_tab_bottom_demo);
        initTabBottom();
    }

    private void initTabBottom() {

        henTabBottomLayout = findViewById(R.id.hitablayout);
        henTabBottomLayout.setBottomAlpha(0.85f);
        henTabBottomInfoList = new ArrayList<>();
        homeInfo = new HenTabBottomInfo<>("首页",
                "fonts/iconfont.ttf",
                getString(R.string.if_home),
                null,
                "#ff656667",
                "#ffd44949");
        infoRecommend = new HenTabBottomInfo<>("收藏",
                "fonts/iconfont.ttf",
                getString(R.string.if_favorite),
                null,
                "#ff656667",
                "#ffd44949");
//        homeInfo = new HenTabBottomInfo<>("分类",
//            "fonts/iconfont.ttf",
//            getString(R.string.if_category),
//            null,
//            "#ff656667",
//            "#ffd44949");

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fire, null);

        infoCategory = new HenTabBottomInfo<>(
                "分类",
                bitmap,
                bitmap
        );

        infoChat = new HenTabBottomInfo<>("推荐",
                "fonts/iconfont.ttf",
                getString(R.string.if_recommend),
                null,
                "#ff656667",
                "#ffd44949");
        infoProfile = new HenTabBottomInfo<>("我的",
                "fonts/iconfont.ttf",
                getString(R.string.if_profile),
                null,
                "#ff656667",
                "#ffd44949");

        henTabBottomInfoList.add(homeInfo);
        henTabBottomInfoList.add(infoRecommend);
        henTabBottomInfoList.add(infoCategory);
        henTabBottomInfoList.add(infoChat);
        henTabBottomInfoList.add(infoProfile);
        henTabBottomLayout.inflateInfo(henTabBottomInfoList);

        henTabBottomLayout.addTabSelectChangeListener(new IHenTabLayout.OnTabSelectedListener<HenTabBottomInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable HenTabBottomInfo<?> preInfo, @Nullable HenTabBottomInfo<?> nextInfo) {
                Toast.makeText(BottomActicity.this, nextInfo.name, Toast.LENGTH_SHORT).show();

            }
        });

        henTabBottomLayout.defaultSelected(homeInfo);
        //        改变某个tab的高度
        HenTabBottom tab = henTabBottomLayout.findTab(henTabBottomInfoList.get(2));
        tab.resetHeight(HenDisplayUtil.dp2px(66f, getResources()));
    }
}
