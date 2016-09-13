package com.example.y.mvp.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by simaben on 7/4/16.
 */
public class FileUtil {

    public static final boolean isSDCardExists() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private static final String BYTES = "Bytes";
    private static final String MEGABYTES = "MB";
    private static final String KILOBYTES = "kB";
    private static final String GIGABYTES = "GB";
    private static final long KILO = 1024;
    private static final long MEGA = KILO * 1024;
    private static final long GIGA = MEGA * 1024;

    public static String formatFileSize(final long pBytes) {
        if (pBytes < KILO) {
            return pBytes + BYTES;
        } else if (pBytes < MEGA) {
            return (int) (0.5 + (pBytes / (double) KILO)) + KILOBYTES;
        } else if (pBytes < GIGA) {
            return (int) (0.5 + (pBytes / (double) MEGA)) + MEGABYTES;
        } else {
            return (int) (0.5 + (pBytes / (double) GIGA)) + GIGABYTES;
        }
    }

    public static boolean fileExist(String path) {
        try {
            return new File(path).exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static void appendAddress(String address, String name) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "tv.txt");
        if (file != null && file.exists()) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(file, true);
                writer.write("\n");
                writer.write(name + "," + address);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getDownloadPath(Context ctx,String filename) {
        if (isSDCardExists()) {
            File downlaodPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
            if (downlaodPath.exists()) return downlaodPath.getAbsolutePath();
            try {
                downlaodPath.createNewFile();
                return downlaodPath.getAbsolutePath();
            } catch (IOException e) {
                return ctx.getCacheDir().getAbsolutePath();
            }
        }
        return ctx.getCacheDir().getAbsolutePath();
    }
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete(); // The directory is empty now and can be deleted.
    }
}
