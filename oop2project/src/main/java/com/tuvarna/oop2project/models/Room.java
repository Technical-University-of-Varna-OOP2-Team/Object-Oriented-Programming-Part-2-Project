package com.tuvarna.oop2project.models;

import com.tuvarna.oop2project.enums.RoomStatus;
import com.tuvarna.oop2project.enums.RoomType;

import java.util.Date;

public class Room {
    private long id;
    private int roomNumber;
    private int capacity;
    private RoomType type;
    private Date occupiedDate;
    private double price;
    private RoomStatus status;

    public Room(int roomNumber, int capacity, RoomType type, Date occupiedDate, double price, RoomStatus status) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.type = type;
        this.occupiedDate = occupiedDate;
        this.price = price;
        this.status = status;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
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