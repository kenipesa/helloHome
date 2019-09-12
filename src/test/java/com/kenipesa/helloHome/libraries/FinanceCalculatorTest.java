package com.kenipesa.helloHome.libraries;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class FinanceCalculatorTest {

    // All of these should use assertEquals, not assertTrue, and IntelliJ even highlights them to tell you that.
    // They should also include helpful messages, and also some explanation of why the expected answer is what it is!
    @Test
    public void calcMonthlyMortgageBudgetTest() {
        assertEquals(1866, FinanceCalculator.calcMonthlyMortgageBudget(80000));
    }

    @Test
    public void isMonthlyDebtLessThan36PercentTest() {

        // 36% debt returns 0.
        assertTrue(FinanceCalculator.isMonthlyDebtLessThan36Percent(100000, 3000) == 0);
        // Less than 36% debt returns positive integer.
        assertTrue(FinanceCalculator.isMonthlyDebtLessThan36Percent(100000, 1000) == 1999);
        // Greater than 36% debt returns negative integer.
        assertTrue(FinanceCalculator.isMonthlyDebtLessThan36Percent(100000, 5000) == -2000);
    }

    @Test
    public void calcAffordableMortgageTest() {
       assertTrue(FinanceCalculator.calcAffordableMortgage(665, 0.07, 30) == 100000);
       assertTrue(FinanceCalculator.calcAffordableMortgage(1000, 0.07, 30) == 150000);
       assertTrue(FinanceCalculator.calcAffordableMortgage(1000, 0.075, 30) == 140000);
    }
}