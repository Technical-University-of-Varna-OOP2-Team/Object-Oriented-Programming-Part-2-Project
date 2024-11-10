package com.tuvarna.oop2project.models;

public class ReservationService {
    private long id;
    private Reservation reservation;
    private Services service;
    private int quantity;

    public ReservationService(Reservation reservation, Services service, int quantity) {
        this.reservation = reservation;
        this.service = service;
        this.quantity = quantity;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}