package com.wangj.uilib.tab.top;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

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
public class HenTabTop extends RelativeLayout implements IHenTab<HenTabTopInfo> {
    //region 构造方法
    public HenTabTop(Context context) {
        super(context);
    }

    public HenTabTop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HenTabTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //endregion

    //region IHenTab 接口实现
    @Override
    public void setHenTabInfo(@Nullable HenTabTopInfo data) {

    }

    @Override
    public void resetHeight(int height) {

    }

    @Override
    public void onTabSelectedChange(int index, @Nullable HenTabTopInfo preInfo, @Nullable HenTabTopInfo nextInfo) {

    }
    //endregion
}
