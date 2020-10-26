package com.wangj.loglib;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.loglib
 * @ClassName: HenLogType
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/26 16:40
 * @Version: 1.0
 */
public class HenLogType {
    @IntDef({V, D, I, W, E, A})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }


    public final static int V = Log.VERBOSE;
    public final static int D = Log.DEBUG;
    public final static int I = Log.INFO;
    public final static int W = Log.WARN;
    public final static int E = Log.ERROR;
    public final static int A = Log.ASSERT;
}
