package com.tuvarna.oop2project.models;

public class Guest {
    private int id;
    private String name;
    private String phone;
    private String address;
    private Room room;
    private double rating;

    public Guest(int id, String name, String phone, String address, Room room, double rating) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.room = room;
        this.rating = rating;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}