package com.tuvarna.oop2project.models;

import com.tuvarna.oop2project.enums.RoomStatus;

import java.util.Date;

public class Room {
    private int id;
    private int capacity;
    private String type;
    private Date occupiedDate;
    private double price;
    private RoomStatus status;

    public Room(int id, int capacity, String type, Date occupiedDate, double price, RoomStatus status) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
        this.occupiedDate = occupiedDate;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getOccupiedDate() {
        return occupiedDate;
    }

    public void setOccupiedDate(Date occupiedDate) {
        this.occupiedDate = occupiedDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}