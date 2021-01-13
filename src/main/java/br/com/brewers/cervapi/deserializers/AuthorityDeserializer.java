package br.com.brewers.cervapi.deserializers;

import br.com.brewers.cervapi.models.Role;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.*;

public class AuthorityDeserializer extends JsonDeserializer<List<GrantedAuthority>> {
    @Override
    public List<GrantedAuthority> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode nodes = jsonParser.readValueAsTree();
        if (nodes.isArray()) {
            Set<GrantedAuthority> set = new HashSet<>();
            for (JsonNode node : nodes) {
                try {
                    set.add(new Role(Role.Tipo.valueOf(node.asText())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return new ArrayList<>(set);
        }
        return Collections.emptyList();
    }
}
