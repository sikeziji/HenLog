package com.wangj.loglib;

import androidx.annotation.NonNull;

import com.wangj.loglib.interfaces.HenLogPrinter;

import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.loglib
 * @ClassName: HenLog
 * @Description: 1. 打印堆栈信息
 * 2. File输出
 * 3. 模拟控制台
 * @Author: wangj
 * @CreateDate: 2020/10/26 16:38
 * @Version: 1.0
 */
public class HenLog {
    private static final String HI_LOG_PACKAGE;

    static {
        String className = HenLog.class.getName();
        HI_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(HenLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(HenLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(HenLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(HenLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(HenLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(HenLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(HenLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(HenLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(HenLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(HenLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(HenLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(HenLogType.A, tag, contents);
    }

    public static void log(@HenLogType.TYPE int type, Object... contents) {
        log(type, HenLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@HenLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(HenLogManager.getInstance().getConfig(), type, tag, contents);
    }

    public static void log(@NonNull HenLogConfig config, @HenLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (config.includeThread()) {
            String threadInfo = HenLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (config.stackTraceDepth() > 0) {
            String stackTrace = HenLogConfig.HI_STACK_TRACE_FORMATTER.format(
                    HenStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), HI_LOG_PACKAGE, config.stackTraceDepth()));
            sb.append(stackTrace).append("\n");
        }
        String body = parseBody(contents, config);
        if (body != null) {//替换转义字符\
            body = body.replace("\\\"", "\"");
        }
        sb.append(body);
        List<HenLogPrinter> printers =
                config.printers() != null ? Arrays.asList(config.printers()) : HenLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        //打印log
        for (HenLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }


    private static String parseBody(@NonNull Object[] contents, @NonNull HenLogConfig config) {
        if (config.injectJsonParser() != null) {
            //只有一个数据且为String的情况下不再进行序列化
            if (contents.length == 1 && contents[0] instanceof String) {
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
