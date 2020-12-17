package com.zhongyiguolian.zy.data.userbean;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class FileBean {
    private String filePath;
    private Uri fileUri;
    private File mFile;
    private Bitmap videoBitmap;
    private String fileKey;
    private String fileType;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Uri getFileUri() {
        return fileUri;
    }

    public void setFileUri(Uri fileUri) {
        this.fileUri = fileUri;
    }

    public File getmFile() {
        return mFile;
    }

    public void setmFile(File mFile) {
        this.mFile = mFile;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Bitmap getVideoBitmap() {
        return videoBitmap;
    }

    public void setVideoBitmap(Bitmap videoBitmap) {
        this.videoBitmap = videoBitmap;
    }

    /*
    * 是否有内容
    * */
    public boolean isData(){
        if(mFile != null || filePath != null){
            return true;
        }

        return false;
    }

}
