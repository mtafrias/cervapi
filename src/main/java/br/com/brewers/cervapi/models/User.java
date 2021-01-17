package br.com.brewers.cervapi.models;

import br.com.brewers.cervapi.deserializers.AuthorityDeserializer;
import br.com.brewers.cervapi.deserializers.PasswordDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Document(collection = "users")
@Data
@ApiIgnore
public class User implements UserDetails {

    @Id
    private String id;
    @NotBlank(message = "Username não pode ser nulo/vazio")
    @Size(min = 5, message = "Username deve ter no mínimo 5 caracteres")
    private String username;
    @NotBlank(message = "Senha não pode ser nula/vazia")
    @Size(min = 5, message = "Senha deve ter no mínimo 5 caracteres")
    @JsonDeserialize(using = PasswordDeserializer.class)
    private String password;
    @JsonDeserialize(using = AuthorityDeserializer.class)
    private Set<Role> authorities;
    private boolean expiredAccount = false;
    private boolean expiredCredentials = false;
    private boolean locked = false;
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expiredAccount;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expiredCredentials;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
