package com.tuvarna.oop2project.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class ReservationService {
    @EmbeddedId
    private ReservationServiceId id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double totalPrice;

    @Embeddable
    public static class ReservationServiceId implements java.io.Serializable {
        @ManyToOne
        @JoinColumn(name = "reservation_id", nullable = false)
        private Reservation reservation;

        @ManyToOne
        @JoinColumn(name = "service_id", nullable = false)
        private Services service;

        // Equals and HashCode
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            ReservationServiceId that = (ReservationServiceId) o;
            return Objects.equals(reservation, that.reservation) && Objects.equals(service, that.service);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reservation, service);
        }

        // Getters and Setters
        public Services getService() {
            return service;
        }

        public void setService(Services service) {
            this.service = service;
        }

        public Reservation getReservation() {
            return reservation;
        }

        public void setReservation(Reservation reservation) {
            this.reservation = reservation;
        }
    }

    // Getters and Setters
    public ReservationServiceId getId() {
        return id;
    }

    public void setId(ReservationServiceId id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
