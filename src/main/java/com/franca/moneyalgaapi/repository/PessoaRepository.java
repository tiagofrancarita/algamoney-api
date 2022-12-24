package com.franca.moneyalgaapi.repository;

import com.franca.moneyalgaapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "SELECT p FROM Pessoa p WHERE UPPER(TRIM(p.nome)) LIKE %?1%")
    List<Pessoa> buscarPessoaNome(String nomePessoa);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE Pessoa SET ativo = 0 WHERE codigo = ?1")
    void inativarPessoa(Long codigoPessoa);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE Pessoa SET ativo = 1 WHERE codigo = ?1")
    void ativarPessoa(Long codigoPessoa);
}
