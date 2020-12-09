package com.stormdzh.notificationtest.badgenum;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2020-11-26 11:20
 */
public class MyService extends Service {

    IconBadgeNumManager setIconBadgeNumManager;
    private boolean isStop;
    private int count;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!isStop) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count += 2;
                sendIconNumNotification();
            }
        }
    });

    public MyService() {
        setIconBadgeNumManager = new IconBadgeNumManager();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isStop = false;
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isStop = true;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendIconNumNotification() {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm == null) return;
        String notificationChannelId = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = createNotificationChannel();
            nm.createNotificationChannel(notificationChannel);
            notificationChannelId = notificationChannel.getId();
        }
        Notification notification = null;
        try {
            int mmcount= count%10==0?0:count;
            notification = new NotificationCompat.Builder(this, notificationChannelId)
                    .setSmallIcon(getApplicationInfo().icon)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("title")
                    .setContentText("content num: " + count)
                    .setTicker("ticker")
                    .setAutoCancel(true)
                    .setNumber(mmcount)
                    .build();
            notification = setIconBadgeNumManager.setIconBadgeNum(getApplication(), notification, mmcount);

            nm.notify(32154, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel createNotificationChannel() {
        String channelId = "test";
        NotificationChannel channel = null;
        channel = new NotificationChannel(channelId,
                "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.RED); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        return channel;
    }
}
