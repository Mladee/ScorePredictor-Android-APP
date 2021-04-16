package com.hfad.fcbarcelonaplayerdatabase;

public class PlayerModel {
    private int id;
    private String Name;
    private String Nationality;
    private int Rating;
    private String Position;


    public PlayerModel(int id, String name, String nationality, int rating, String position) {
        this.id = id;
        Name = name;
        Nationality = nationality;
        Rating = rating;
        Position = position;
    }
    public PlayerModel(){};


    @Override
    public String toString() {
        return

                "Name='" + Name + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", Rating=" + Rating +
                ", Position='" + Position + '\'';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }
}
