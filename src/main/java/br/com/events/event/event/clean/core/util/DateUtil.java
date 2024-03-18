package br.com.events.event.event.clean.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * This class helps to convert every needed date format
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    private static final String ZONE_ID = "America/Sao_Paulo";

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
    public static Long LocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.of(ZONE_ID)).toInstant().toEpochMilli();
    }
}
