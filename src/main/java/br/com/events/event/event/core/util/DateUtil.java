package br.com.events.event.event.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * This class helps to convert every needed date format
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    private static final String ZONE_ID = "America/Sao_Paulo";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter DATE_IN_FULL_FORMATTER = DateTimeFormatter.ofPattern(
            "d 'de' MMMM 'de' yyyy", new Locale("pt", "BR")
    );


    /**
     * This method transforms a {@link Long} timestamp into a {@link LocalDateTime} object
     *
     * @param timestamp {@link Long} timestamp value
     * @return {@link LocalDateTime} object that represents the equivalent date
     */
    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        if (Objects.isNull(timestamp)) {
            return null;
        }
        return Instant
                .ofEpochMilli(timestamp)
                .atZone(ZoneId.of(ZONE_ID))
                .toLocalDateTime();
    }

    /**
     * This method transforms a {@link LocalDateTime} object into a {@link Long} timestamp
     *
     * @param localDateTime {@link LocalDateTime} object with the data to be converted
     * @return {@link Long} timestamp converted
     */
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.atZone(ZoneId.of(ZONE_ID)).toInstant().toEpochMilli();
    }

    public static String format(LocalDateTime date) {
        if (Objects.isNull(date)) {
            return "";
        }
        return date.format(DATE_FORMAT);
    }

    public static String formatTime(LocalTime localTime) {
        return localTime.format(TIME_FORMATTER);
    }

    public static String dateInFull(LocalDateTime localTime) {
        return localTime.format(DATE_IN_FULL_FORMATTER);
    }
}
