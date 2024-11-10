package com.tuvarna.oop2project.models;

import com.tuvarna.oop2project.enums.ReservationType;

import java.util.Date;
import java.util.List;

public class Reservation {
    private long id;
    private int roomNumber;
    private Date dateFrom;
    private Date dateTo;
    private Employee employee;
    private List<Guest> guests;
    private ReservationType type;
    private double price;

    public Reservation(int roomNumber, Date dateFrom, Date dateTo, Employee employee, List<Guest> guests, ReservationType type, double price) {
        this.roomNumber = roomNumber;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.employee = employee;
        this.guests = guests;
        this.type = type;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}