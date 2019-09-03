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
    
    public int getAnnualIncome() {
        return this.annualIncome;
    }
    
    public int getHousePayment() {
        return this.housePayment;
    }

    public void setHousePayment(int housePayment) {
        this.housePayment = housePayment;
    }

    public int getEntertainment() {
        return this.entertainment;
    }

    public void setEntertainment(int entertainment) {
        this.entertainment = entertainment;
    }

    public int getUtilities() {
        return this.utilities;
    }

    public void setUtilities(int utilities) {
        this.utilities = utilities;
    }

    public int getInsurance() {
        return this.insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public int getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public int getMisc() {
        return this.misc;
    }

    public void setMisc(int misc) {
        this.misc = misc;
    }

    public ApplicationUser getBuyer() {
        return this.buyer;
    }

    public void setBuyer(ApplicationUser buyer) {
        this.buyer = buyer;
    }
}
