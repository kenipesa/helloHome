package com.kenipesa.helloHome.libraries;

public class FinanceCalculator {

    public static int calcMonthlyGrossIncome(int annualIncome) {
        return annualIncome / 12;
    }

//  Using the 28% rule, calculate the monthly affordable budget of a gross annual income.
    public static int calcMonthlyMortgageBudget(int annualIncome) {
        int monthlyIncome = FinanceCalculator.calcMonthlyGrossIncome(annualIncome);
        return (monthlyIncome * 28) / 100;
    }

//  Using the 36% rule, check if total debt is no more than 36% of monthly income.
    public static boolean isMonthlyDebtLessThan36Percent(int monthlyIncome, int creditPayment, int entertainment, int utilities,
                                                         int insurance, int vehicle, int misc) {
        int totalDebt = creditPayment + entertainment + utilities + insurance + vehicle + misc;

        int value = totalDebt / monthlyIncome * 100;
        if(value == 36 || value < 36) {
            return true;
        } else {
            return false;
        }
    }

//  Calculate total affordable mortgage, using algorithm found online.
//  Reference: http://www.moneychimp.com/articles/finworks/fmmortgage.htm
    public static int calcAffordableMortgage(int monthlyMortgageBudget, double interestRate, int yearsOfLoan) {
        double scale = Math.pow(10, 5);
        float rate = (float) (interestRate / 12);
        double roundedRate = Math.round(rate * scale) / scale;
        int months = yearsOfLoan * 12;
        int value = (int)((float)( monthlyMortgageBudget * (Math.pow(1 + roundedRate, months) - 1) / ((float)(Math.pow((1 + roundedRate), months) * roundedRate))));
        value = round(value);
        return value;
    }

//    Helper function to round a number to the nearest ten thousandth.
//    Reference: https://www.geeksforgeeks.org/round-the-given-number-to-nearest-multiple-of-10/
    private static int round(int n) {
        // Smaller multiple
        int a = (n / 10000) * 10000;

        // Larger multiple
        int b = a + 10000;

        // Return of closest of two
        return (n - a > b - n)? b : a;
    }
}
