package com.wangj.uilib.tab.common;

import androidx.annotation.Nullable;
import androidx.annotation.Px;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.uilib.tab
 * @ClassName: IHenTab
 * @Description: HenTab对外接口
 * @Author: wangj
 * @CreateDate: 2020/10/27 17:23
 * @Version: 1.0
 */
public interface IHenTab<D> extends IHenTabLayout.OnTabSelectedListener<D> {


    /**
     * 设置Tab信息
     *
     * @param data
     */
    void setHenTabInfo(@Nullable D data);


    /**
     * 动态修改某个item的大小
     *
     * @param height 高度
     */
    void resetHeight(@Px int height);


}
