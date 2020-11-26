package com.stormdzh.notificationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ResidentNotificationView mResidentNotificationView;
    private ResidentTest mResidentTest;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResidentNotificationView = new ResidentNotificationView();
        mResidentNotificationView.init(this);

        mResidentTest = new ResidentTest();
        mResidentTest.init(this);

        tvInfo = findViewById(R.id.tvInfo);

        findViewById(R.id.tvShowNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
        findViewById(R.id.tvCloseNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeNotification();
            }
        });
        findViewById(R.id.tvOther).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otherNotification();
            }
        });

        setInfo();
        showNotification();
    }

    private void setInfo() {
        String info = "基本设备信息：\n" +
                "屏幕宽高：" + getScreenWidth() + "*" + getScreenHeight() +
                "\n屏幕密度：" + getScreenDensity() +
                "\n获取手机厂商:" + getDeviceBrand() +
                "\n手机型号:" + getSystemModel() +
                "\n系统版本：" + getSystemVersion();

        tvInfo.setText(info);
    }

    private void closeNotification() {
        if (mResidentNotificationView != null) {
            mResidentNotificationView.cancelResidentNotify();
        }
    }

    private void showNotification() {

        if (mResidentNotificationView != null) {
            mResidentNotificationView.update();
        }
    }

    private void otherNotification() {
        mResidentTest.update();

    }


    private int getScreenWidth() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    private int getScreenHeight() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    private float getScreenDensity() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.density;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }
}
