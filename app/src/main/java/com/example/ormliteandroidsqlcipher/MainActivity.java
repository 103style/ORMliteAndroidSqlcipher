package com.example.ormliteandroidsqlcipher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ormliteandroidsqlcipher.clickcounter.ClickConfig;
import com.example.ormliteandroidsqlcipher.clickcounter.CounterScreen;
import com.example.ormliteandroidsqlcipher.clickcounter.CreateCounter;
import com.example.ormliteandroidsqlcipher.helloandroid.HelloAndroid;
import com.example.ormliteandroidsqlcipher.hellonobase.HelloNoBase;
import com.example.ormliteandroidsqlcipher.hellonohelper.HelloNoHelper;
import com.example.ormliteandroidsqlcipher.hellotwodbs.HelloTwoDbs;
import com.example.ormliteandroidsqlcipher.notifyservice.MyActivity;
import com.example.ormliteandroidsqlcipher.notifyservice.ThingActivity;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * @author xiaoke.luo@tcl.com 2019/5/13 16:42
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase.loadLibs(this);

        findViewById(R.id.bt_hello_android).setOnClickListener(v -> startActivity(HelloAndroid.class));
        findViewById(R.id.bt_hello_no_base).setOnClickListener(v -> startActivity(HelloNoBase.class));
        findViewById(R.id.bt_hello_two_dbs).setOnClickListener(v -> startActivity(HelloTwoDbs.class));
        findViewById(R.id.bt_hello_no_helper).setOnClickListener(v -> startActivity(HelloNoHelper.class));
        findViewById(R.id.bt_create_counter).setOnClickListener(v -> startActivity(CreateCounter.class));
        findViewById(R.id.bt_click_config).setOnClickListener(v -> startActivity(ClickConfig.class));
        findViewById(R.id.bt_counter_screen).setOnClickListener(v -> startActivity(CounterScreen.class));
        findViewById(R.id.bt_my_activity).setOnClickListener(v -> startActivity(MyActivity.class));
        findViewById(R.id.bt_thing_activity).setOnClickListener(v -> startActivity(ThingActivity.class));
    }

    private void startActivity(Class className) {
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }
}
