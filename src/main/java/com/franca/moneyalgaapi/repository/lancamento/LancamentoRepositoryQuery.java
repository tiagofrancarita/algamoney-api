package com.franca.moneyalgaapi.repository.lancamento;


import com.franca.moneyalgaapi.model.Lancamento;
import com.franca.moneyalgaapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {


    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);



}
