package com.wangj.loglib;

import androidx.annotation.NonNull;

import com.wangj.loglib.interfaces.HenLogPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: HenLog
 * @Package: com.wangj.loglib
 * @ClassName: HenLogManager
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/26 16:41
 * @Version: 1.0
 */
public class HenLogManager {
    private HenLogConfig config;
    private static HenLogManager instance;
    private List<HenLogPrinter> printers = new ArrayList<>();

    private HenLogManager(HenLogConfig config, HenLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static HenLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull HenLogConfig config, HenLogPrinter... printers) {
        instance = new HenLogManager(config, printers);
    }

    public HenLogConfig getConfig() {
        return config;
    }

    public List<HenLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(HenLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(HenLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }
}
