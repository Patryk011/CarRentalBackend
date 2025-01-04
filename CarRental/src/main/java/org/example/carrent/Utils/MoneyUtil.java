package org.example.carrent.Utils;

import java.math.BigDecimal;

public class MoneyUtil {

    private static final BigDecimal HUNDRED = new BigDecimal(100);


    public static BigDecimal toDisplayFormat(BigDecimal storedValue) {
        return storedValue.divide(HUNDRED);
    }

    public static BigDecimal toStoredFormat(BigDecimal displayValue) {
        return displayValue.multiply(HUNDRED);
    }

    public static BigDecimal calculateTotalCost(BigDecimal pricePerDay, long days) {
        return pricePerDay.multiply(BigDecimal.valueOf(days));
    }
}
