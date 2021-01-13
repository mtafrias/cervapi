package br.com.brewers.cervapi.models;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private final Tipo tipo;

    public Role(Tipo tipo) {
        this.tipo = tipo;
    }

    public static Role from(Tipo tipo) {
        return tipo != null ? new Role(tipo) : null;
    }

    @Override
    public String getAuthority() {
        return tipo != null ? tipo.getName() : null;
    }

    public enum Tipo {
        EDITOR("EDITOR");

        private final String name;

        Tipo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
