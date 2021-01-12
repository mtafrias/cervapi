package br.com.brewers.cervapi.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.io.IOException;

public class PasswordDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String password = jsonParser.getValueAsString();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
    }
}
