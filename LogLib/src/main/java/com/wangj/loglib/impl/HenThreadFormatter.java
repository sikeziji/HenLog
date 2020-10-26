package com.wangj.loglib.impl;

import com.wangj.loglib.interfaces.HenLogForMatter;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.loglib.impl
 * @ClassName: HenThreadFormatter
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/26 16:44
 * @Version: 1.0
 */
public class HenThreadFormatter implements HenLogForMatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}