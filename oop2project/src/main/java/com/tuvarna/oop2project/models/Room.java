package com.tuvarna.oop2project.models;

import com.tuvarna.oop2project.enums.RoomStatus;
import com.tuvarna.oop2project.enums.RoomType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private int roomNumber;

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Temporal(TemporalType.DATE)
    private Date occupiedDate;

    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;


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