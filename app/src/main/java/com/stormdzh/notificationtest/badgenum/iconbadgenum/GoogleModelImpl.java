package com.stormdzh.notificationtest.badgenum.iconbadgenum;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.stormdzh.notificationtest.badgenum.Utils;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2020-11-26 11:06
 */
public class GoogleModelImpl implements IconBadgeNumModel {
    private static final String NOTIFICATION_ERROR = "google not support before API O";

    @Override
    public Notification setIconBadgeNum(@NonNull Application context, Notification notification, int count) throws Exception {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            throw new Exception(NOTIFICATION_ERROR);
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", Utils.getInstance().getLaunchIntentForPackage(context)); // com.test. badge.MainActivity is your apk main activity

//        if (Utils.getInstance().canResolveBroadcast(context, intent)) {
        context.sendBroadcast(intent);
//        } else {
//            throw new Exception(UNABLE_TO_RESOLVE_INTENT_ERROR_ + intent.toString());
//        }

        return notification;
    }
}
