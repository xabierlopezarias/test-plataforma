package com.plataforma.prueba.shared.infrastructure.dates;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getNowISO6801() {
        DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'hh:mm:ssxxx")
            .withZone(ZoneId.systemDefault());

        return formatter.format(Instant.now());
    }
}
