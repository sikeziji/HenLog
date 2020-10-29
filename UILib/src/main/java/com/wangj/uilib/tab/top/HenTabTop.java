package com.wangj.uilib.tab.top;

import android.content.Context;
import android.graphics.Color;
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
import com.wangj.uilib.tab.bottom.HenTabBottomInfo;
import com.wangj.uilib.tab.common.IHenTab;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.uilib.tab.top
 * @ClassName: HenTabTop
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/28 13:56
 * @Version: 1.0
 */
public class HenTabTop extends RelativeLayout implements IHenTab<HenTabTopInfo<?>> {

    private ImageView tabImageView;
    private TextView tabNameView;
    private View indicator;
    private HenTabTopInfo<?> tabInfo;

    //region 构造方法
    public HenTabTop(Context context) {
        this(context,null);
    }

    public HenTabTop(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HenTabTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    //endregion

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.hen_tab_top, this);
        tabImageView = inflate.findViewById(R.id.iv_image);
        tabNameView = inflate.findViewById(R.id.tv_name);
        indicator = inflate.findViewById(R.id.tab_top_indicator);
    }

    //region IHenTab 接口实现
    @Override
    public void setHenTabInfo(@Nullable HenTabTopInfo<?> henTabInfo) {

        this.tabInfo = henTabInfo;
        inflateInfo(false, true);
    }


    @Override
    public void resetHeight(int height) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        getTabNameView().setVisibility(GONE);
    }

    @Override
    public void onTabSelectedChange(int index, @Nullable HenTabTopInfo<?> preInfo, @Nullable HenTabTopInfo<?> nextInfo) {

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
     * 填充信息
     *
     * @param selected 是否已选中
     * @param init     是否需要初始化
     */
    private void inflateInfo(boolean selected, boolean init) {
        if (tabInfo.tabType == HenTabTopInfo.TabType.TXT) {
            if (init) {
                tabNameView.setVisibility(VISIBLE);
                tabImageView.setVisibility(GONE);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }
            if (selected) {
                indicator.setVisibility(VISIBLE);
                tabNameView.setTextColor(getTextColor(tabInfo.tintColor));
            } else {
                indicator.setVisibility(GONE);
                tabNameView.setTextColor(getTextColor(tabInfo.defaultColor));
            }

        } else if (tabInfo.tabType == HenTabTopInfo.TabType.BITMAP) {
            if (init) {
                tabImageView.setVisibility(VISIBLE);
                tabNameView.setVisibility(GONE);
            }
            if (selected) {
                indicator.setVisibility(VISIBLE);
                tabImageView.setImageBitmap(tabInfo.selectedBitmap);
            } else {
                indicator.setVisibility(GONE);
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


    public ImageView getTabImageView() {
        return tabImageView;
    }

    public TextView getTabNameView() {
        return tabNameView;
    }

    public HenTabTopInfo<?> getTabInfo() {
        return tabInfo;
    }
}
