package br.com.events.event.event.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BigDecimalUtil {

    private final static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


    public static String format(BigDecimal value) {
        return currencyFormat.format(value);
    }
}
