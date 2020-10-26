package com.wangj.loglib.interfaces;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.loglib
 * @ClassName: HenLogForMatter
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/26 16:28
 * @Version: 1.0
 */
public interface HenLogForMatter<T> {


    /**
     * 格式化
     * @param data 被格式化的数据
     * @return 返回格式化后的数据
     */
    String format(T data);


}
