package com.franca.moneyalgaapi.model.enums;

public enum TipoLancamento {

    RECEITA ("Receita"),
    DESPESA ("Despesa");

    private String descricao;

    TipoLancamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
