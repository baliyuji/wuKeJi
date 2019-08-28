package com.configure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

public class ObjectKeyDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return value == null ? null : fromUrl(value);
    }

    public String fromUrl(String url) {
        String path = UriComponentsBuilder.fromHttpUrl(url).build().getPath();
        if (path == null) {
            return null;
        }
        return path.startsWith("/") ? path.substring(1) : path;
    }

}