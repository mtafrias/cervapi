package br.com.brewers.cervapi.models;

public enum EstiloCerveja {

    PALE_LAGER("Pale Lager"),
    PILSENER("Pilsener");

    private final String descricao;

    EstiloCerveja(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
