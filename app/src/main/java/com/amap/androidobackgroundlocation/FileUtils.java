package com.amap.androidobackgroundlocation;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtils {

    private static final String TAG = "FileUtils";

    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024;

    public static final String DEF_FILE_PATH = Environment.getExternalStorageDirectory() + "/gps_loc";

    public static final String DEF_FILE_NAME = "loc_rec.txt";

    public static final String DEF_ERR_FILE = "loc_err.txt";

    public static boolean addContentToFile(String fileName, String content, String path) {
        boolean isSuccess = false;
        BufferedWriter bufferedWriter = null;
        try {
            File dirs = new File(path);
            if (!dirs.exists()) {
                if (!dirs.mkdirs()) {
                    Log.e(TAG, "addContentToFile mkdirs failed");
                    return false;
                }
            }
            File file = new File(path + "/" + fileName);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    Log.e(TAG, "addContentToFile createNewFile failed");
                    return false;
                }
            }
            boolean isAdd = true;
            if (file.length() > MAX_FILE_SIZE) { // 20971520
                isAdd = false;
            }
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, isAdd)));
            bufferedWriter.write(content + "\r\n");
            isSuccess = true;
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally{
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return isSuccess;
    }
}
