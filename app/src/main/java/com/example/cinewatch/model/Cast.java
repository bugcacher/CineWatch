package com.example.cinewatch.model;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class Cast {
    private String character,name,profile_path;
    private Integer id;

    public Cast(String character, String name, String profile_path, Integer id) {
        this.character = character;
        this.name = name;
        this.profile_path = profile_path;
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
