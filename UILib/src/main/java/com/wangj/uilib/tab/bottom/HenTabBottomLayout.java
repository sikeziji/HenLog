package com.wangj.uilib.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.wangj.loglib.util.HenDisplayUtil;
import com.wangj.loglib.util.HenViewUtil;
import com.wangj.uilib.R;
import com.wangj.uilib.tab.common.IHenTabLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.uilib.tab.bottom
 * @ClassName: HenTabBottomLayout
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/28 10:19
 * @Version: 1.0
 */
public class HenTabBottomLayout extends FrameLayout implements IHenTabLayout<HenTabBottom,
        HenTabBottomInfo<?>> {
    private static final String TAG_TAB_BOTTOM = "TAG_TAB_BOTTOM";

    //数据列表
    private List<HenTabBottomInfo<?>> infoList;
    //当前选中Tab的信息
    private HenTabBottomInfo<?> selectedInfo;
    //TabBottom高度
    private static float tabBottomHeight = 50;
    //透明度
    private float bottomAlpha = 1f;
    //tab选中修改接口
    private List<OnTabSelectedListener<HenTabBottomInfo<?>>> tabSelectedChangeListeners = new ArrayList<>();
    //TabBottom的头部线条高度
    private float bottomLineHeight = 0.5f;
    //TabBottom的头部线条颜色
    private String bottomLineColor = "#dfe0e1";


    //region 构造方法
    public HenTabBottomLayout(@NonNull Context context) {
        this(context, null);
    }

    public HenTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HenTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //endregion


    //region IHenTabLayout接口实现
    @Override
    public HenTabBottom findTab(HenTabBottomInfo<?> info) {
        ViewGroup ll = findViewWithTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < ll.getChildCount(); i++) {
            View child = ll.getChildAt(i);
            if (child instanceof HenTabBottom) {
                HenTabBottom tab = (HenTabBottom) child;
                if (tab.getTabInfo() == info) {
                    return tab;
                }
            }
        }
        return null;
    }

    @Override
    public void addTabSelectChangeListener(OnTabSelectedListener<HenTabBottomInfo<?>> listener) {
        tabSelectedChangeListeners.add(listener);
    }

    @Override
    public void defaultSelected(@NonNull HenTabBottomInfo<?> defaultInfo) {
        onSelected(defaultInfo);
    }

    @Override
    public void inflateInfo(@Nullable List<HenTabBottomInfo<?>> infoList) {

        //判空
        if (infoList.isEmpty()) {
            return;
        }
        //变量赋值
        this.infoList = infoList;
        //移除之前已经添加的View
        for (int i = getChildCount() - 1; i > 0; i--) {
            removeViewAt(i);
        }
        //重置选中信息
        selectedInfo = null;
        //添加背景视图
        addBackground();
        //清除之前添加的HiTabBottom listener，Tips：Java foreach remove问题
        Iterator<OnTabSelectedListener<HenTabBottomInfo<?>>> iterator = tabSelectedChangeListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof HenTabBottom) {
                iterator.remove();
            }
        }
        int height = HenDisplayUtil.dp2px(tabBottomHeight, getResources());
        FrameLayout ll = new FrameLayout(getContext());
        int width = HenDisplayUtil.getDisplayWidthInPx(getContext()) / infoList.size();
        ll.setTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < infoList.size(); i++) {
            final HenTabBottomInfo<?> info = infoList.get(i);
            //Tips：为何不用LinearLayout：当动态改变child大小后Gravity.BOTTOM会失效
            LayoutParams params = new LayoutParams(width, height);
            params.gravity = Gravity.BOTTOM;
            params.leftMargin = i * width;

            HenTabBottom tabBottom = new HenTabBottom(getContext());
            tabSelectedChangeListeners.add(tabBottom);
            tabBottom.setHenTabInfo(info);
            ll.addView(tabBottom, params);
            tabBottom.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelected(info);
                }
            });
        }
        LayoutParams flPrams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        flPrams.gravity = Gravity.BOTTOM;
        addBottomLine();
        addView(ll, flPrams);

        fixContentView();


    }
    //endregion


    private void onSelected(@NonNull HenTabBottomInfo<?> nextInfo) {
        for (OnTabSelectedListener<HenTabBottomInfo<?>> listener : tabSelectedChangeListeners) {
            listener.onTabSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        this.selectedInfo = nextInfo;
    }

    private void addBottomLine() {
        View bottomLine = new View(getContext());
        bottomLine.setBackgroundColor(Color.parseColor(bottomLineColor));

        LayoutParams bottomLineParams =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HenDisplayUtil.dp2px(bottomLineHeight, getResources()));
        bottomLineParams.gravity = Gravity.BOTTOM;
        bottomLineParams.bottomMargin = HenDisplayUtil.dp2px(tabBottomHeight - bottomLineHeight, getResources());
        addView(bottomLine, bottomLineParams);
        bottomLine.setAlpha(bottomAlpha);
    }

    /**
     * 修复内容区域的底部Padding
     */
    private void fixContentView() {
        if (!(getChildAt(0) instanceof ViewGroup)) {
            return;
        }
        ViewGroup rootView = (ViewGroup) getChildAt(0);
        ViewGroup targetView = HenViewUtil.findTypeView(rootView, RecyclerView.class);
        if (targetView == null) {
            targetView = HenViewUtil.findTypeView(rootView, ScrollView.class);
        }
        if (targetView == null) {
            targetView = HenViewUtil.findTypeView(rootView, AbsListView.class);
        }
        if (targetView != null) {
            targetView.setPadding(0, 0, 0, HenDisplayUtil.dp2px(tabBottomHeight, getResources()));
            targetView.setClipToPadding(false);
        }
    }

    /**
     * 添加背景
     */
    private void addBackground() {
        //获取布局  ps：该布局为单一View
        View view = LayoutInflater.from(getContext()).inflate(R.layout.hen_bottom_layout_bg, null);

        //设置尺寸和位置
        LayoutParams params =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HenDisplayUtil.dp2px(tabBottomHeight, getResources()));
        params.gravity = Gravity.BOTTOM;
        addView(view, params);
        //设置透明度
        view.setAlpha(bottomAlpha);
    }

    public void setBottomLineHeight(float bottomLineHeight) {
        this.bottomLineHeight = bottomLineHeight;
    }

    public void setBottomLineColor(String bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
    }

    public static void setTabBottomHeight(float tabBottomHeight) {
        HenTabBottomLayout.tabBottomHeight = tabBottomHeight;
    }

    public void setBottomAlpha(float bottomAlpha) {
        this.bottomAlpha = bottomAlpha;
    }
}
