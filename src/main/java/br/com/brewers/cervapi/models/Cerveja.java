package br.com.brewers.cervapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.Objects;

@Validated
@Document(collection = "cervejas")
@Data
public class Cerveja {

    @Id
    private String id;
    @NotNull(message = "Nome não pode ser nulo.")
    private String nome;
    @NotNull(message = "Descrição não pode ser nula.")
    private String descricao;
    @NotNull(message = "Imagem não pode ser nula.")
    private URI imagem;
    private String fabricante;
    private EstiloCerveja estilo;
    @Positive(message = "Teor alcólico não pode ser negativo.")
    private double teorAlcolico;

    public static String getCollection() {
        return "cervejas";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cerveja cerveja = (Cerveja) o;
        return Objects.equals(id, cerveja.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
