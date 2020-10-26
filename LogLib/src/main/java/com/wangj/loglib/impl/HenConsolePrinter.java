package com.wangj.loglib.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.wangj.loglib.HenLogConfig;
import com.wangj.loglib.interfaces.HenLogPrinter;

import static com.wangj.loglib.HenLogConfig.MAX_LEN;


public class HenConsolePrinter implements HenLogPrinter {

    @Override
    public void print(@NonNull HenLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len) {
                log.append(printString.substring(index, len));
            }
            Log.println(level, tag, log.toString());
        } else {
            Log.println(level, tag, printString);
        }
    }
}
