package com.franca.moneyalgaapi.repository.filter;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class LancamentoFilter {

    private String descricao;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataVencimentoDe;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate getDataVencimentoAte;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimentoDe() {
        return dataVencimentoDe;
    }

    public void setDataVencimentoDe(LocalDate dataVencimentoDe) {
        this.dataVencimentoDe = dataVencimentoDe;
    }

    public LocalDate getGetDataVencimentoAte() {
        return getDataVencimentoAte;
    }

    public void setGetDataVencimentoAte(LocalDate getDataVencimentoAte) {
        this.getDataVencimentoAte = getDataVencimentoAte;
    }
}
