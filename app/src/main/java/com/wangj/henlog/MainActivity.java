package com.wangj.henlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wangj.loglib.HenLog;
import com.wangj.loglib.HenLogConfig;
import com.wangj.loglib.HenLogManager;
import com.wangj.loglib.HenLogType;
import com.wangj.loglib.HenViewPrinterProvider;
import com.wangj.loglib.impl.HenConsolePrinter;
import com.wangj.loglib.impl.HenViewPrinter;

public class MainActivity extends AppCompatActivity {

    private Button btn_log;
    private HenViewPrinter viewPrinter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        btn_log = findViewById(R.id.btn_log);
        viewPrinter = new HenViewPrinter(this);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printLog();
            }
        });
        if (viewPrinter != null) {
            viewPrinter.getViewProvider().showFloatingView();
        }

    }

    private void printLog() {
        HenLogManager.getInstance().addPrinter(viewPrinter);

        HenLog.log(new HenLogConfig() {
            @Override
            public boolean includeThread() {
                return false;
            }
        }, HenLogType.E, "---", "5566");
        HenLog.a("9900");

    }

}