package com.rgzn.zt.entity;

import android.graphics.Bitmap;

public class DiaryItem {
    private String theme;
    private String content;
    private String addtime;
    private Bitmap photo;

    public DiaryItem(String theme, String content, String addtime, Bitmap photo) {
        this.theme = theme;
        this.content = content;
        this.addtime = addtime;
        this.photo = photo;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
