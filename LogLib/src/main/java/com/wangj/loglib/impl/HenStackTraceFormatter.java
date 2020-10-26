package com.wangj.loglib.impl;

import com.wangj.loglib.interfaces.HenLogForMatter;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.loglib
 * @ClassName: HenStackTraceFormatter
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/26 16:31
 * @Version: 1.0
 */
public class HenStackTraceFormatter implements HenLogForMatter<StackTraceElement[]> {


    @Override
    public String format(StackTraceElement[] stackTrace) {
        //设置String的长度
        StringBuilder sb = new StringBuilder(128);
        if (stackTrace == null || stackTrace.length == 0) {
            //返回空
            return null;
        } else if (stackTrace.length == 1) {
            //直接输出内容
            return "\t-" + stackTrace[0].toString();
        } else {
            //把内容进行拼接
            for (int i = 0, len = stackTrace.length; i < len; i++) {
                //拼接开头
                if (i == 0) {
                    sb.append("stackTrace: \n");
                }
                //拼接中间内容
                if (i != len - 1) {
                    sb.append("\t├ ");
                    sb.append(stackTrace[i].toString());
                    sb.append("\n");
                } else {
                    //拼接结尾
                    sb.append("\t└ ");
                    sb.append(stackTrace[i].toString());
                }
            }
        }
        return sb.toString();
    }
}
