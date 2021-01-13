package br.com.brewers.cervapi.deserializers;

import br.com.brewers.cervapi.models.Role;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Enums;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AuthorityDeserializer extends JsonDeserializer<Set<Role>> {
    @Override
    public Set<Role> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode nodes = jsonParser.readValueAsTree();
        if (nodes.isArray()) {
            Set<Role> set = new HashSet<>();
            nodes.forEach(jsonNode -> set.add(Role.from(Enums.getIfPresent(Role.Tipo.class, jsonNode.asText()).orNull())));
            set.removeIf(Objects::isNull);
            return set;
        } else {
            return Collections.emptySet();
        }
    }
}
