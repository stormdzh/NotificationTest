package com.stormdzh.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

/**
 * @Description: 常驻通知栏 展示控件
 * @Author: dzh
 * @CreateDate: 2020-08-12 15:22
 */
public class ResidentTest {

    private int notifycatonid = 45;

    private NotificationManager manager;
    private Context mContext;

    private RemoteViews mNormalRemoteViews;
    private RemoteViews mSmallNormalRemoteViews;
    private Notification notification = null;
    private static final String mChannelId = "mmmmtest";
    private static final String mChannelName = "测试通知";
    private NotificationChannel mChannel;
    private NotificationCompat.Builder mBuilder;

    /**
     * 初始化
     *
     * @param context Context
     */
    @SuppressWarnings("all")
    public void init(Context context) {
        this.mContext = context;
        manager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
    }

    /**
     * 更新通知栏
     */
    public synchronized void update() {
        //设置UI
        setNotificationUI();

        //构建通知栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //8.0
            createApi26();
        } else {
            createNormal();
        }
        showNotify();
    }

    /**
     * 初始化通知栏信息
     */
    private synchronized void setNotificationUI() { //目前只能展示3个和4个入口的样式

        if (mNormalRemoteViews == null) {
            mNormalRemoteViews = new RemoteViews(mContext.getPackageName(),
                    R.layout.common_resident_notification_big_test);
        }
        if (mSmallNormalRemoteViews == null) {
            mSmallNormalRemoteViews = new RemoteViews(mContext.getPackageName(),
                    R.layout.common_resident_notification_big_test);
        }

    }


    /**
     * 创建8.0以下的ui
     */
    @SuppressWarnings("all")
    private void createNormal() {
        if (mBuilder == null) {
            mBuilder = new NotificationCompat.Builder(mContext);
        }
        if (notification != null) {
            notification = null;
        }
        mBuilder.setContent(mNormalRemoteViews)
                .setCustomContentView(mSmallNormalRemoteViews)
                .setCustomBigContentView(mNormalRemoteViews)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.FLAG_ONLY_ALERT_ONCE);
        notification = mBuilder.build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
    }

    /**
     * 8.0以上创建通知栏
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createApi26() {
        if (manager == null) return;
        if (mChannel == null) {
            mChannel = new NotificationChannel(mChannelId, mChannelName,
                    NotificationManager.IMPORTANCE_HIGH);
        }
        manager.createNotificationChannel(mChannel);
        if (notification != null) {
            notification = null;
        }
        notification = new NotificationCompat.Builder(mContext, mChannelId)
                .setContent(mNormalRemoteViews)
                .setCustomContentView(mSmallNormalRemoteViews)
                .setCustomBigContentView(mNormalRemoteViews)
                .setShowWhen(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

    }

    /**
     * 展示通知栏
     */
    private void showNotify() {
        if (manager == null || notification == null) return;
        manager.notify(notifycatonid, notification);
    }

    /**
     * 关闭常驻通知栏
     */
    @SuppressWarnings("all")
    public void cancelResidentNotify() {
        if (manager != null) {
            try {
                manager.cancel(notifycatonid);
            } catch (Exception e) {
            }
        }
    }

}
