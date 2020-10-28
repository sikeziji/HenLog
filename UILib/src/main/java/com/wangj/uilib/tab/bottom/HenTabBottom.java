package com.wangj.uilib.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.wangj.uilib.R;
import com.wangj.uilib.tab.common.IHenTab;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.uilib.tab.common
 * @ClassName: HenTabBottom
 * @Description 自定义View 底部标签栏
 * @Author: wangj
 * @CreateDate: 2020/10/27 17:39
 * @Version: 1.0
 */
public class HenTabBottom extends RelativeLayout implements IHenTab<HenTabBottomInfo<?>> {
    // 底部Tab信息
    private HenTabBottomInfo<?> tabInfo;
    //视图组件
    private ImageView tabImageView;
    private TextView tabIconView;
    private TextView tabNameView;

    //region 构造方法
    public HenTabBottom(Context context) {
        this(context, null);
    }

    public HenTabBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HenTabBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //endregion

    /**
     * 初始化视图
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.hen_tab_bottom, this);
        tabImageView = findViewById(R.id.iv_image);
        tabIconView = findViewById(R.id.tv_icon);
        tabNameView = findViewById(R.id.tv_name);
    }

    //region IHenTab接口
    @Override
    public void setHenTabInfo(@Nullable HenTabBottomInfo<?> henTabInfo) {
        this.tabInfo = henTabInfo;
        inflateInfo(false, true);
    }

    @Override
    public void resetHeight(int height) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        getTabNameView().setVisibility(View.GONE);
    }

    @Override
    public void onTabSelectedChange(int index, @Nullable HenTabBottomInfo<?> preInfo, @Nullable HenTabBottomInfo<?> nextInfo) {
        if (preInfo != tabInfo && nextInfo != tabInfo || preInfo == nextInfo) {
            return;
        }
        if (preInfo == tabInfo) {
            inflateInfo(false, false);
        } else {
            inflateInfo(true, false);
        }
    }
    //endregion

    /**
     * inflateInfo 填充Tab信息
     *
     * @param selected 当前选中
     * @param init     是否需要初始化
     */
    private void inflateInfo(boolean selected, boolean init) {
        //判断当前Tab类型是否为ICOn
        if (tabInfo.tabType == HenTabBottomInfo.TabType.ICON) {
            //解决初始化问题
            if (init) {
                tabImageView.setVisibility(GONE);
                tabIconView.setVisibility(VISIBLE);
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), tabInfo.iconFont);
                tabIconView.setTypeface(typeface);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }

            //视图选中进行的组件变化
            if (selected) {
                tabIconView.setText(TextUtils.isEmpty(tabInfo.selectedIconName) ? tabInfo.defaultIconName : tabInfo.selectedIconName);
                tabIconView.setTextColor(getTextColor(tabInfo.tintColor));
                tabNameView.setTextColor(getTextColor(tabInfo.tintColor));
            } else {
                tabIconView.setText(tabInfo.defaultIconName);
                tabIconView.setTextColor(getTextColor(tabInfo.defaultColor));
                tabNameView.setTextColor(getTextColor(tabInfo.defaultColor));
            }

        }
//            判断当前Tab类型是否为ICOn
        else if (tabInfo.tabType == HenTabBottomInfo.TabType.BITMAP) {
            //解决初始化问题
            if (init) {
                tabImageView.setVisibility(VISIBLE);
                tabIconView.setVisibility(GONE);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }
            //视图选中进行的组件变化
            if (selected) {
                tabImageView.setImageBitmap(tabInfo.selectedBitmap);
            } else {
                tabImageView.setImageBitmap(tabInfo.defaultBitmap);
            }
        }
    }

    @ColorInt
    private int getTextColor(Object color) {
        if (color instanceof String) {
            return Color.parseColor((String) color);
        } else {
            return (int) color;
        }
    }


    //region 视图组件Get方法
    public HenTabBottomInfo<?> getTabInfo() {
        return tabInfo;
    }

    public ImageView getTabImageView() {
        return tabImageView;
    }

    public TextView getTabIconView() {
        return tabIconView;
    }

    public TextView getTabNameView() {
        return tabNameView;
    }
    //endregion
}
