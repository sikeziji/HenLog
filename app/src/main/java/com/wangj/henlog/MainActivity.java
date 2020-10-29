package com.wangj.henlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wangj.henlog.demo.BottomActicity;
import com.wangj.henlog.demo.LogActivity;
import com.wangj.henlog.demo.TopActivity;
import com.wangj.loglib.HenLog;
import com.wangj.loglib.HenLogConfig;
import com.wangj.loglib.HenLogManager;
import com.wangj.loglib.HenLogType;
import com.wangj.loglib.HenViewPrinterProvider;
import com.wangj.loglib.impl.HenConsolePrinter;
import com.wangj.loglib.impl.HenViewPrinter;

public class MainActivity extends AppCompatActivity {

    private Button btn_Logactivity;
    private Button btn_Bootomactivity;
    private Button btn_Topactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btn_Logactivity = findViewById(R.id.btn_Logactivity);
        btn_Bootomactivity = findViewById(R.id.btn_Bootomactivity);
        btn_Topactivity = findViewById(R.id.btn_Topactivity);

        btn_Logactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogActivity.class));
            }
        });
        btn_Bootomactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BottomActicity.class));
            }
        });
        btn_Topactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TopActivity.class));
            }
        });

    }


}