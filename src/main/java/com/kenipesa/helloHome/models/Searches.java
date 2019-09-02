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
    ApplicationUser user;

    public Searches(Long id, String city, String state, ApplicationUser user) {
        this.id = id;
        this.city = city;
        State = state;
        this.user = user;
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

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }
}
