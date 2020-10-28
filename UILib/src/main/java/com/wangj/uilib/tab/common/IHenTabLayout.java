package com.wangj.uilib.tab.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.uilib.tab
 * @ClassName: IHenTabLayout
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/27 17:24
 * @Version: 1.0
 */
public interface IHenTabLayout<Tab extends ViewGroup, D> {

    /**
     * 根据数据查询 Tab
     *
     * @param data
     * @return
     */
    Tab findTab(D data);

    /**
     * 添加Tab选中事件
     *
     * @param listener
     */
    void addTabSelectChangeListener(OnTabSelectedListener<D> listener);

    void defaultSelected(@NonNull D defaultInfo);

    /**
     * 填入信息
     *
     * @param infoList 信息列表
     */
    void inflateInfo(@Nullable List<D> infoList);


    /**
     * 选中事件接口
     *
     * @param <D>
     */
    interface OnTabSelectedListener<D> {

        /**
         * 选中事件方法
         *
         * @param index    下标
         * @param preInfo  上一个
         * @param nextInfo 下一个
         */
        void onTabSelectedChange(int index, @Nullable D preInfo, @Nullable D nextInfo);

    }

}
