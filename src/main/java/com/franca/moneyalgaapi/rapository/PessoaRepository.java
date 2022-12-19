package com.franca.moneyalgaapi.rapository;

import com.franca.moneyalgaapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "SELECT p FROM Pessoa p WHERE UPPER(TRIM(p.nome)) LIKE %?1%")
    List<Pessoa> buscarPessoaNome(String nomePessoa);
}
