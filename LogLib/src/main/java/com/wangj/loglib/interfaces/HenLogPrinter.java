package com.wangj.loglib.interfaces;

import androidx.annotation.NonNull;

import com.wangj.loglib.HenLogConfig;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.loglib.interfaces
 * @ClassName: HenLogPrinter
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/26 16:30
 * @Version: 1.0
 */
public interface HenLogPrinter {

    /**
     * 打印
     *
     * @param config      打印配置
     * @param level       打印等级
     * @param tag         打印 TAG
     * @param printString 打印数据
     */
    void print(@NonNull HenLogConfig config, int level, String tag, @NonNull String printString);

}
