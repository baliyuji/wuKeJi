package com.configure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

public class ObjectKeySerializer extends JsonSerializer<String> {

    private final UriComponentsBuilder builder;

    public ObjectKeySerializer(String baseUrl) {
        this.builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
    }

    public ObjectKeySerializer(String endpoint, String bucket) {
        this(endpoint);
        this.builder.host(bucket + "." + this.builder.build().getHost());
    }

    public String toUrl(String key) {
        return builder.cloneBuilder().path(key).toUriString();
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeString(toUrl(value));
        }
    }

}