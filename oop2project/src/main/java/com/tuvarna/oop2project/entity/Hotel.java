package com.tuvarna.oop2project.entity;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Account owner;

    @Column(nullable = false)
    private Integer floors;

    @Column(nullable = false)
    private Integer roomsPerFloor;

    @OneToMany(mappedBy = "hotel")
    private ArrayList<Room> rooms;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public Integer getRoomsPerFloor() {
        return roomsPerFloor;
    }

    public void setRoomsPerFloor(Integer roomsPerFloor) {
        this.roomsPerFloor = roomsPerFloor;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
