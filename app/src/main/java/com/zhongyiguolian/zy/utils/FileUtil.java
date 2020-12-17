/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.zhongyiguolian.zy.utils;

import android.content.Context;

import java.io.File;

public class FileUtil {
    public static File getFrontSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "frontpic.jpg");
        return file;
    }
    public static File getBackSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "backpic.jpg");
        return file;
    }

    public static File getBlankSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "blank.jpg");
        return file;
    }
}
