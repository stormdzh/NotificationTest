package com.stormdzh.notificationtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stormdzh.notificationtest.badgenum.MyService;

/**
 * @Description: 桌面数字角标 https://www.cnblogs.com/loaderman/p/11949985.html
 * @Author: dzh
 * @CreateDate: 2020-11-26 10:29
 */
public class BadgeMumActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badgenum);

        findViewById(R.id.tvBadgeStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BadgeMumActivity.this, MyService.class);
                startService(i);
                Toast.makeText(BadgeMumActivity.this,"开始",Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tvBadgeStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BadgeMumActivity.this, MyService.class);
                stopService(i);
                Toast.makeText(BadgeMumActivity.this,"结束",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
