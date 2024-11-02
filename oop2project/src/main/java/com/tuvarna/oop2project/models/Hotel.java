package com.tuvarna.oop2project.models;

import java.util.List;

public class Hotel {
    private int floors;
    private int roomsPerFloor;
    private List<Room> rooms;
    private Account owner;

    public Hotel(int floors, int roomsPerFloor, List<Room> rooms, Account owner) {
        this.floors = floors;
        this.roomsPerFloor = roomsPerFloor;
        this.rooms = rooms;
        this.owner = owner;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getRoomsPerFloor() {
        return roomsPerFloor;
    }

    public void setRoomsPerFloor(int roomsPerFloor) {
        this.roomsPerFloor = roomsPerFloor;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }
}
