package com.stormdzh.notificationtest.badgenum.iconbadgenum;

import android.app.Application;
import android.app.Notification;

import androidx.annotation.NonNull;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2020-11-26 11:07
 */
public interface IconBadgeNumModel {

    Notification setIconBadgeNum(@NonNull Application context, Notification notification, int count) throws Exception;
}
