package com.endava.tmd.BookProject.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateConfig extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ZoneId defaultZone = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZone).toInstant());
        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = localDateFormat.format(date);
        jsonGenerator.writeString(formattedDate);
    }
}
