package com.kenipesa.helloHome.models;

import javax.persistence.*;

@Entity
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int annualIncome;
    int housePayment;
    int entertainment;
    int utilities;
    int insurance;
    int vehicle;
    int misc;

    @OneToOne
    ApplicationUser buyer;

    public Expenses() {}
    
    public Expenses(int annualIncome, int housePayment, int entertainment, int utilities,
                    int insurance, int vehicle, int misc, ApplicationUser buyer) {
        this.annualIncome = annualIncome;
        this.housePayment = housePayment;
        this.entertainment = entertainment;
        this.utilities = utilities;
        this.insurance = insurance;
        this.vehicle = vehicle;
        this.misc = misc;
        this.buyer = buyer;
    }

    public Long getId() {
        return id;
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

    public ApplicationUser getBuyer() {
        return buyer;
    }

    public void setBuyer(ApplicationUser buyer) {
        this.buyer = buyer;
    }
}
