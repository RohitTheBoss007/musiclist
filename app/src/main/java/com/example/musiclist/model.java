package com.example.musiclist;

public class model {
    private String title,desc,id;

    public model() {
    }

    public model(String title, String desc, String id) {
        this.title = title;
        this.desc = desc;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
