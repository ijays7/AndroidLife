package com.ijays.androidlife.utils;

import android.app.Activity;
import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by ijaysdev on 16/7/31.
 */

public class DeviceUtils {
    /**
     * SD卡判断
     *
     * @return
     */
    public static boolean isSDCardAvailabe() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 创建app文件夹
     * @param appName
     * @param application
     * @return
     */
    public static String createAPPFolder(String appName, Application application) {
        return createAPPFolder(appName, application, null);
    }

    /**
     * @param appName
     * @param application
     * @param folderName
     */
    public static String createAPPFolder(String appName, Application application, String folderName) {
        File root = Environment.getExternalStorageDirectory();
        File folder;

        //如果sd卡存在
        if (isSDCardAvailabe() && root != null) {
            folder = new File(root, appName);
            if (!folder.exists()) {
                folder.mkdir();
            }
        } else {
            //如果sd卡不存在,则放到缓存中
            root = application.getCacheDir();
            folder = new File(root, appName);
            if (!folder.exists()) {
                folder.mkdir();
            }
        }
        if (folderName != null) {
            folder = new File(folder, folderName);
            if (!folder.exists()) {
                folder.exists();
            }
        }
        return folder.getAbsolutePath();
    }

    /**
     * 通过uri获取文件
     *
     * @param context
     * @param uri
     * @return
     */
    public static File uri2File(Activity context, Uri uri) {
        File file;
        String[] project = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver()
                .query(uri, project, null, null, null);
        if (cursor != null) {
            int imageIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToNext();
            String img_path = cursor.getString(imageIndex);
            file = new File(img_path);
        } else {
            file = new File(uri.getPath());
        }
        //释放cursor
        if (cursor != null) {
            cursor.close();
        }
        return file;
    }
}
