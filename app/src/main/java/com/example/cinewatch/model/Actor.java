package com.example.cinewatch.model;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 10,June,2020
 */
public class Actor {
    private String birthday,name,biography,place_of_birth,profile_path,known_for_department;
    private Number popularity;
    private Integer id;
    private JsonObject movie_credits;

    public JsonObject getMovie_credits() {
        return movie_credits;
    }

    public void setMovie_credits(JsonObject movie_credits) {
        this.movie_credits = movie_credits;
    }

    public Actor(String birthday, String name, String biography, String place_of_birth,
                 String profile_path, String known_for_department, Integer id,
                 Number popularity, JsonObject movie_credits) {
        this.birthday = birthday;
        this.name = name;
        this.biography = biography;
        this.place_of_birth = place_of_birth;
        this.profile_path = profile_path;
        this.known_for_department = known_for_department;
        this.id = id;
        this.popularity = popularity;
        this.movie_credits = movie_credits;

    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }
}
