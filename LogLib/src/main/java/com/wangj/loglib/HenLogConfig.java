package com.wangj.loglib;

import com.wangj.loglib.impl.HenStackTraceFormatter;
import com.wangj.loglib.impl.HenThreadFormatter;
import com.wangj.loglib.interfaces.HenLogPrinter;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.loglib
 * @ClassName: HiLogConfig
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/26 16:31
 * @Version: 1.0
 */
public abstract class HenLogConfig {
    public static int MAX_LEN = 512;
    static HenThreadFormatter HI_THREAD_FORMATTER = new HenThreadFormatter();
    static HenStackTraceFormatter HI_STACK_TRACE_FORMATTER = new HenStackTraceFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "HiLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public HenLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }
}
