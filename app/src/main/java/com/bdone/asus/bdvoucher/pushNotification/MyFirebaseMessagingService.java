/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bdone.asus.bdvoucher.pushNotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.bdone.asus.bdvoucher.ActivityHome;
import com.bdone.asus.bdvoucher.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        sendNotification(remoteMessage);
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param remoteMessage FCM RemoteMessage received.
     */
    private void sendNotification(RemoteMessage remoteMessage) {
        //   String sample_url = "http://codeversed.com/androidifysteve.png";

        Bitmap remotePicture = null;
        //  Bitmap largeForAjkerdeal = BitmapFactory.decodeResource(getResources(), R.drawable.sitehomehot);


        Intent intent = new Intent(this, ActivityHome.class);




        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        try {
            remotePicture = BitmapFactory.decodeStream((InputStream) new URL(remoteMessage.getData().get("imageLink")).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }


        NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
        notiStyle.setBigContentTitle(remoteMessage.getData().get("title"));
        notiStyle.setSummaryText(remoteMessage.getData().get("shortDescription"));
        notiStyle.bigPicture(remotePicture);


// this is a my insertion looking for a solution

        int icon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? R.drawable.logo : R.mipmap.ic_launcher;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("shortDescription"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColor(Color.RED)
                .setStyle(notiStyle)
                .setPriority(2)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Random rand = new Random();

        int n = rand.nextInt(50) + 1;

        notificationManager.notify(n /* RECENTLY_ID of notification */, notificationBuilder.build());
    }

}