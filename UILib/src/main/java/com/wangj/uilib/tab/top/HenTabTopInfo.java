package com.wangj.uilib.tab.top;

import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.uilib.tab.top
 * @ClassName: HenTabTopInfo
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/28 13:58
 * @Version: 1.0
 */
public class HenTabTopInfo<Color> {

    public enum TabType {
        BITMAP, TXT
    }


    public Class<? extends Fragment> fragment;
    public String name;
    public Bitmap defaultBitmap;
    public Bitmap selectedBitmap;
    public Color defaultColor;
    public Color tintColor;
    public TabType tabType;

    public HenTabTopInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.tabType = TabType.BITMAP;
    }

    public HenTabTopInfo(String name, Color defaultColor, Color tintColor) {
        this.name = name;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.TXT;
    }


}
