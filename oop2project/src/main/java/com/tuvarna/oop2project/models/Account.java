package com.tuvarna.oop2project.models;

import java.util.List;

public class Account {
    private Hotel hotel;
    private List<Employee> employees;

    public Account(Hotel hotel, List<Employee> employees) {
        this.hotel = hotel;
        this.employees = employees;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
