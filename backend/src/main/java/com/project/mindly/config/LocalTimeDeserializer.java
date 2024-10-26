package com.project.mindly.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Map;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        Map<String, Integer> timeMap = jsonParser.readValueAs(Map.class);
        return LocalTime.of(timeMap.get("hour"), timeMap.get("minute"), timeMap.get("second"));
    }
}

