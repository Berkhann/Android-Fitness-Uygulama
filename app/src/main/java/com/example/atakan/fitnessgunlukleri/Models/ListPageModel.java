package com.example.atakan.fitnessgunlukleri.Models;

import java.io.Serializable;

public class ListPageModel implements Serializable {
    public int id;
    public String name;
    public String content;
    public String img_name1;
    public String img_name2;
    public String img_name3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_name1() {
        return img_name1;
    }

    public void setImg_name1(String img_name1) {
        this.img_name1 = img_name1;
    }

    public String getImg_name2() {
        return img_name2;
    }

    public void setImg_name2(String img_name2) {
        this.img_name2 = img_name2;
    }

    public String getImg_name3() {
        return img_name3;
    }

    public void setImg_name3(String img_name3) {
        this.img_name3 = img_name3;
    }
}
