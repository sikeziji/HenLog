package com.wangj.uilib.tab.bottom;

import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.uilib.tab.bottom
 * @ClassName: HiTabBottomInfo
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/27 17:41
 * @Version: 1.0
 */
public class HenTabBottomInfo<Color> {
    public enum TabType {
        BITMAP, ICON
    }

    /*存储当前Fragment*/
    public Class<? extends Fragment> fragment;
    /*Bootom名称*/
    public String name;
    /*默认Bitmap*/
    public Bitmap defaultBitmap;
    /*选中Bitmap*/
    public Bitmap selectedBitmap;
    /*iconFont*/
    public String iconFont;

    /**
     * Tips：在Java代码中直接设置iconfont字符串无效，需要定义在string.xml
     */
    /* defaultIconName*/
    public String defaultIconName;
    /* selectedIconName*/
    public String selectedIconName;
    /* 默认颜色*/
    public Color defaultColor;
    /* 着色颜色 */
    public Color tintColor;
    /*TYPE TAB类型*/
    public TabType tabType;


    public HenTabBottomInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.tabType = TabType.BITMAP;
    }

    public HenTabBottomInfo(String name, String iconFont, String defaultIconName, String selectedIconName, Color defaultColor, Color tintColor) {
        this.name = name;
        this.iconFont = iconFont;
        this.defaultIconName = defaultIconName;
        this.selectedIconName = selectedIconName;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.ICON;
    }
}
