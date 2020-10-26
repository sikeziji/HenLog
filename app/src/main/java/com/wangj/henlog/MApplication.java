package com.wangj.henlog;

import android.app.Application;

import com.google.gson.Gson;
import com.wangj.loglib.HenLogConfig;
import com.wangj.loglib.HenLogManager;
import com.wangj.loglib.impl.HenConsolePrinter;
import com.wangj.loglib.impl.HenFilePrinter;
import com.wangj.loglib.interfaces.HenLogPrinter;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.henlog
 * @ClassName: MApplication
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/26 17:01
 * @Version: 1.0
 */
public class MApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        HenLogManager.init(new HenLogConfig() {
                               @Override
                               public JsonParser injectJsonParser() {
                                   return new JsonParser() {
                                       @Override
                                       public String toJson(Object src) {
                                           return new Gson().toJson(src);
                                       }
                                   };
                               }

                               @Override
                               public String getGlobalTag() {
                                   return "MApplication";
                               }

                               @Override
                               public boolean enable() {
                                   return true;
                               }

                               @Override
                               public boolean includeThread() {
                                   return true;
                               }

                               @Override
                               public int stackTraceDepth() {
                                   return 5;
                               }
                           }, new HenConsolePrinter(),
                HenFilePrinter.getInstance(getApplicationContext().getCacheDir().getAbsolutePath(), 0));
    }
}
