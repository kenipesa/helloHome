package com.kenipesa.helloHome.models;

import javax.persistence.*;

@Entity
public class Searches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String city;
    String State;

    @ManyToOne
    ApplicationUser buyer;

    public Searches(Long id, String city, String state, ApplicationUser buyer) {
        this.id = id;
        this.city = city;
        State = state;
        this.buyer = buyer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public ApplicationUser getBuyer() {
        return buyer;
    }

    public void setBuyer(ApplicationUser buyer) {
        this.buyer = buyer;
    }
}
