package com.kenipesa.helloHome.models;

import javax.persistence.*;

@Entity
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int housePayment;
    int entertainment;
    int utilities;
    int insurance;
    int vehicle;
    int misc;

    @OneToOne
    ApplicationUser user;

    public Expenses(Long id, int housePayment, int entertainment, int utilities,
                    int insurance, int vehicle, int misc, ApplicationUser buyer) {
        this.id = id;
        this.housePayment = housePayment;
        this.entertainment = entertainment;
        this.utilities = utilities;
        this.insurance = insurance;
        this.vehicle = vehicle;
        this.misc = misc;
        this.user = buyer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHousePayment() {
        return housePayment;
    }

    public void setHousePayment(int housePayment) {
        this.housePayment = housePayment;
    }

    public int getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(int entertainment) {
        this.entertainment = entertainment;
    }

    public int getUtilities() {
        return utilities;
    }

    public void setUtilities(int utilities) {
        this.utilities = utilities;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public int getMisc() {
        return misc;
    }

    public void setMisc(int misc) {
        this.misc = misc;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }
}
