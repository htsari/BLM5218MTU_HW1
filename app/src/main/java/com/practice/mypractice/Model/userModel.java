package com.practice.mypractice.Model;

public class userModel {
    private int Id;
    private String name;
    private String lastName;
    private String password;
    private String gender;
    private int weight;
    private int height;
    private String date;

    public userModel(int Id, String name, String lastName, String password, String gender, int weight, int height, String date) {
        this.Id = Id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
