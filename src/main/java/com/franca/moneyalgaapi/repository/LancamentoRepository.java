package com.franca.moneyalgaapi.repository;

import com.franca.moneyalgaapi.model.Lancamento;
import com.franca.moneyalgaapi.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

    @Query(value = "SELECT l FROM Lancamento l WHERE UPPER(TRIM(l.descricao)) LIKE %?1%")
    List<Lancamento> buscarLancamentoNome(String descricao);



}
