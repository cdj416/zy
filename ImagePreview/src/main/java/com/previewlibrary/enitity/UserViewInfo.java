package com.previewlibrary.enitity;

import android.graphics.Rect;
import android.os.Parcel;

import androidx.annotation.Nullable;

/**
 * 图片位置实体类
 * @author cdj
 * @date 2020/12/10
 */
public class UserViewInfo implements IThumbViewInfo {

    private String url;  //图片地址
    private Rect mBounds; // 记录坐标
    private String user = "用户字段";
     private String videoUrl;
     private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserViewInfo(String url) {
        this.url = url;
    }
    public UserViewInfo(String videoUrl, String url) {
        this.url = url;
        this.videoUrl = videoUrl;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getUrl() {//将你的图片地址字段返回
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Rect getBounds() {//将你的图片显示坐标字段返回
        return mBounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setBounds(Rect bounds) {
        mBounds = bounds;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.mBounds, flags);
        dest.writeString(this.user);
        dest.writeString(this.videoUrl);
        dest.writeString(id);
    }

    protected UserViewInfo(Parcel in) {
        this.url = in.readString();
        this.mBounds = in.readParcelable(Rect.class.getClassLoader());
        this.user = in.readString();
        this.videoUrl = in.readString();
        this.id = in.readString();
    }

    public static final Creator<UserViewInfo> CREATOR = new Creator<UserViewInfo>() {
        @Override
        public UserViewInfo createFromParcel(Parcel source) {
            return new UserViewInfo(source);
        }

        @Override
        public UserViewInfo[] newArray(int size) {
            return new UserViewInfo[size];
        }
    };
}
