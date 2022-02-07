package com.javarush.task.task34.task3404.format;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class OutputFormat {

    public static String decimalFormat(String number) {
        return decimalFormat(new BigDecimal(number));
    }

    public static String decimalFormat(BigDecimal result) {
        final DecimalFormat pattern = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.ENGLISH);
        pattern.setRoundingMode(RoundingMode.HALF_UP);
        pattern.applyPattern("#.##");
        return pattern.format(result);
    }

}
