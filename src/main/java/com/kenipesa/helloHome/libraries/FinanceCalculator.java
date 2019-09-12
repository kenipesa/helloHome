package com.kenipesa.helloHome.libraries;
// nitpick: I would rather this be called "utilities" or similar, only because
// libraries implies that they're packaged for standalone use, as a dependency.

public class FinanceCalculator {

    public static int calcMonthlyGrossIncome(int annualIncome) {
        return annualIncome / 12;
    }

//  Using the 28% rule, calculate the monthly affordable budget of a gross annual income.
    public static int calcMonthlyMortgageBudget(int annualIncome) {
        int monthlyIncome = calcMonthlyGrossIncome(annualIncome);
        return (monthlyIncome * 28) / 100;
    }

//  Using the 36% rule, check if total debt is no more than 36% of monthly income.
    // Any method that starts with the word "is", I expect to return a boolean.
    // It seriously threw me off that this returns an int!
    public static int isMonthlyDebtLessThan36Percent(int annualIncome, int totalExpenses) {
        int monthlyIncome = calcMonthlyGrossIncome(annualIncome);
        double thirtySixRule = (monthlyIncome * .36);
        return (int)(thirtySixRule - totalExpenses);
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

//    Helper function to round a number to the nearest ten thousand.
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
