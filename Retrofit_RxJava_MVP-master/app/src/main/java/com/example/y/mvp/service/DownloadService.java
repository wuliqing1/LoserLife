package com.example.y.mvp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.example.y.mvp.R;
import com.example.y.mvp.constant.Constant;
import com.example.y.mvp.utils.FileUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by simaben on 12/4/16.
 */
public class DownloadService extends Service {

    Intent intentReuslt;
    NotificationManager mNotificationManager;

    String name = "";
    String path = "";
    Map<Integer, NotificationCompat.Builder> taskIdMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        if (intent == null) return super.onStartCommand(intent, flags, startId);
        path = intent.getStringExtra(Constant.ARG_VIDEO_PATH);
        if (TextUtils.isEmpty(path)) return super.onStartCommand(intent, flags, startId);
        path = path.trim();
        name = intent.getStringExtra(Constant.ARG_VIDEO_NAME);
        if (TextUtils.isEmpty(name)) return super.onStartCommand(intent, flags, startId);
        name = name.trim();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(DownloadService.this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(name)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setAutoCancel(true)
                .setOngoing(true)
                .setVibrate(null)
                .setSound(null)
                .setContentText("开始下载...")
                .setContentTitle(name);
        taskIdMap.put(startId, builder);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.download(path, FileUtil.getDownloadPath(this, name), false, new RequestCallBack<File>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                int percent = (int) (current * 100 / total);
                taskIdMap.get(startId).setProgress(100, percent, false);
                taskIdMap.get(startId).setContentText(percent + "%");
                notifyAllTask();
            }

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                intentReuslt = new Intent();
                intentReuslt.setAction(Intent.ACTION_VIEW);
                intentReuslt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
                Uri uri = Uri.fromFile(f);
                intentReuslt.setData(uri);

                PendingIntent pendingIntent = PendingIntent.getActivity(DownloadService.this, 0, intentReuslt, PendingIntent.FLAG_CANCEL_CURRENT);
                taskIdMap.get(startId).setContentText("已下载至:" + f.getAbsolutePath());
                taskIdMap.get(startId).setContentIntent(pendingIntent);
                taskIdMap.get(startId).setProgress(0, 0, false);
                taskIdMap.get(startId).setOngoing(false);
                notifyAllTask();
            }

            @Override
            public void onFailure(com.lidroid.xutils.exception.HttpException e, String s) {
                mNotificationManager.cancel(startId);
            }

        });

        return super.onStartCommand(intent, flags, startId);
    }

    private void notifyAllTask() {
        for (Map.Entry<Integer, NotificationCompat.Builder> task : taskIdMap.entrySet()) {
            int id = task.getKey();
            Notification n = task.getValue().build();
            mNotificationManager.notify(id, n);
        }
    }
}
