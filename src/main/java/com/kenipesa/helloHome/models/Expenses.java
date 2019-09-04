package com.kenipesa.helloHome.models;

import javax.persistence.*;

@Entity
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int annualIncome;
    int creditPayment;
    int entertainment;
    int utilities;
    int insurance;
    int vehicle;
    int misc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    ApplicationUser buyer;
    
    public Expenses() {}
    
    public Expenses(int annualIncome, int creditPayment, int entertainment, int utilities,
                    int insurance, int vehicle, int misc, ApplicationUser buyer) {
        this.annualIncome = annualIncome;
        this.creditPayment = creditPayment;
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
    
    public void setAnnualIncome(int annualIncome) {
        this.annualIncome = annualIncome;
    }
    
    public int getAnnualIncome() {
        return this.annualIncome;
    }
    
    public int getCreditPayment() {
        return this.creditPayment;
    }

    public void setCreditPayment(int creditPayment) {
        this.creditPayment = creditPayment;
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
