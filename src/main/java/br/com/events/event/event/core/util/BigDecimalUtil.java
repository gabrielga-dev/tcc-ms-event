package br.com.events.event.event.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BigDecimalUtil {

    private final static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


    public static String format(BigDecimal value) {
        if (Objects.isNull(value)){
            value = BigDecimal.ZERO;
        }
        return currencyFormat.format(value);
    }
}
