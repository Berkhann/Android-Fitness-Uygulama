package com.example.atakan.fitnessgunlukleri.Models;

import java.io.Serializable;

/**
 * Created by ATAKAN on 18.09.2019.
 */

public class ReadyPageModel implements Serializable {

    public int id;
    public  String exercise_name;
    public  String image_gif;
    public  String exercise_content;

    public ReadyPageModel(String exercise_name, String image_gif, String exercise_content) {
        this.exercise_name = exercise_name;
        this.image_gif = image_gif;
        this.exercise_content = exercise_content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getImage_gif() {
        return image_gif;
    }

    public void setImage_gif(String image_gif) {
        this.image_gif = image_gif;
    }

    public String getExercise_content() {
        return exercise_content;
    }

    public void setExercise_content(String exercise_content) {
        this.exercise_content = exercise_content;
    }
}
