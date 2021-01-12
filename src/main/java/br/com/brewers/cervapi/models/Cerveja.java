package br.com.brewers.cervapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Objects;

@Validated
@Document
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
    @NegativeOrZero(message = "Teor alcólico deve ser maior que zero.")
    private double teorAlcolico;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public URI getImagem() {
        return imagem;
    }

    public void setImagem(URI imagem) {
        this.imagem = imagem;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public EstiloCerveja getEstilo() {
        return estilo;
    }

    public void setEstilo(EstiloCerveja estilo) {
        this.estilo = estilo;
    }

    public double getTeorAlcolico() {
        return teorAlcolico;
    }

    public void setTeorAlcolico(double teorAlcolico) {
        this.teorAlcolico = teorAlcolico;
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
