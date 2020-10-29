package com.wangj.uilib.tab.top;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wangj.loglib.util.HenDisplayUtil;
import com.wangj.uilib.tab.common.IHenTabLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.uilib.tab.top
 * @ClassName: HenTabTopLayout
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/29 10:19
 * @Version: 1.0
 */
public class HenTabTopLayout extends HorizontalScrollView implements IHenTabLayout<HenTabTop, HenTabTopInfo<?>> {


    private List<OnTabSelectedListener<HenTabTopInfo<?>>> tabSelectedChangeListeners = new ArrayList<>();
    private HenTabTopInfo<?> selectedInfo;
    private List<HenTabTopInfo<?>> infoList;
    //Tab的宽度
    private int tabWith;


    //region 构造参数
    public HenTabTopLayout(@NonNull Context context) {
        this(context, null);
    }

    public HenTabTopLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HenTabTopLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVerticalScrollBarEnabled(false);
    }
    //endregion

    //region IHenTabLayout 接口实现
    @Override
    public HenTabTop findTab(HenTabTopInfo<?> data) {
        //获取到根View
        ViewGroup ll = getRootLayout(false);
        for (int i = 0; i < ll.getChildCount(); i++) {
            View child = ll.getChildAt(i);
            if (child instanceof HenTabTop) {
                HenTabTop top = (HenTabTop) child;
                if (top.getTabInfo() == data) {
                    return top;
                }
            }
        }
        return null;
    }


    @Override
    public void addTabSelectChangeListener(OnTabSelectedListener<HenTabTopInfo<?>> listener) {

    }

    @Override
    public void defaultSelected(@NonNull HenTabTopInfo<?> defaultInfo) {
        onSelected(defaultInfo);

    }


    @Override
    public void inflateInfo(@Nullable List<HenTabTopInfo<?>> infoList) {
        if (infoList.isEmpty()) {
            return;
        }
        this.infoList = infoList;
        LinearLayout linearLayout = getRootLayout(true);
        selectedInfo = null;
        //清除之前添加的HiTabTop listener，Tips：Java foreach remove问题
        Iterator<OnTabSelectedListener<HenTabTopInfo<?>>> iterator = tabSelectedChangeListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof HenTabTop) {
                iterator.remove();
            }
        }
        for (int i = 0; i < infoList.size(); i++) {
            final HenTabTopInfo<?> info = infoList.get(i);
            HenTabTop tab = new HenTabTop(getContext());
            tabSelectedChangeListeners.add(tab);
            tab.setHenTabInfo(info);
            linearLayout.addView(tab);
            tab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelected(info);
                }
            });
        }


    }
    //endregion

    /**
     * 获取到Layout
     *
     * @param clear 是否删除全部视图
     * @return
     */
    private LinearLayout getRootLayout(boolean clear) {

        LinearLayout rootView = (LinearLayout) getChildAt(0);
        if (rootView == null) {
            rootView = new LinearLayout(getContext());
            rootView.setOrientation(LinearLayout.HORIZONTAL);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
            addView(rootView, layoutParams);
        } else if (clear) {
            rootView.removeAllViews();
        }

        return rootView;
    }


    /**
     * 选中
     *
     * @param nextInfo 下一个info
     */
    private void onSelected(@NonNull HenTabTopInfo<?> nextInfo) {
        for (OnTabSelectedListener<HenTabTopInfo<?>> listener : tabSelectedChangeListeners) {
            listener.onTabSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        this.selectedInfo = nextInfo;
        autoScroll(nextInfo);
    }

    /**
     * 自动滚动，实现点击的位置能够自动滚动以展示前后2个
     *
     * @param nextInfo 点击tab的info
     */
    private void autoScroll(HenTabTopInfo<?> nextInfo) {

        //根据tab的info获取TabTOP
        HenTabTop tabTop = findTab(nextInfo);
        if (tabTop == null) {
            return;
        }
        //返回当前info在list中的索引
        int index = infoList.indexOf(nextInfo);
        int[] loc = new int[2];
        //获取屏幕点击位置
        tabTop.getLocationInWindow(loc);

        int scrollWidth;
        //赋值top的宽度
        if (tabWith == 0) {
            tabWith = tabTop.getWidth();
        }
        if ((loc[0] + tabWith / 2) > HenDisplayUtil.getDisplayWidthInPx(getContext()) / 2) {
            scrollWidth = rangeScrollWidth(index, 2);
        } else {
            scrollWidth = rangeScrollWidth(index, -2);
        }
        scrollTo(getScrollX() + scrollWidth, 0);
    }

    /**
     * 获取可滚动的范围
     *
     * @param index 从第几个开始
     * @param range 向前向后的范围
     * @return 可滚动的范围
     */
    private int rangeScrollWidth(int index, int range) {
        int scrollWidth = 0;
        for (int i = 0; i < Math.abs(range); i++) {
            int next;
            if (range < 0) {
                next = range + i + index;
            } else {
                next = range - i + index;
            }
            if (next > 0 && next < infoList.size()) {
                if (range < 0) {
                    scrollWidth -= scrollWidth(next, false);
                } else {
                    scrollWidth += scrollWidth(next, true);
                }
            }
        }
        return scrollWidth;
    }

    /**
     * 指定位置的控件可滚动的距离
     *
     * @param index   指定位置的控件
     * @param toRight 是否是点击了屏幕右侧
     * @return 可滚动的距离
     */
    private int scrollWidth(int index, boolean toRight) {
        HenTabTop target = findTab(infoList.get(index));
        if (target == null) return 0;
        Rect rect = new Rect();
        target.getLocalVisibleRect(rect);
        if (toRight) {//点击屏幕右侧
            if (rect.right > tabWith) {//right坐标大于控件的宽度时，说明完全没有显示
                return tabWith;
            } else {//显示部分，减去已显示的宽度
                return tabWith - rect.right;
            }
        } else {
            if (rect.left <= -tabWith) {//left坐标小于等于-控件的宽度，说明完全没有显示
                return tabWith;
            } else if (rect.left > 0) {//显示部分
                return rect.left;
            }
            return 0;
        }
    }
}
