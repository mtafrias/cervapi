package br.com.brewers.cervapi.models;

public enum EstiloCerveja {

    PALE_LARGER("Pale Larger"),
    PILSENER("Pilsener");

    private final String descricao;

    EstiloCerveja(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
