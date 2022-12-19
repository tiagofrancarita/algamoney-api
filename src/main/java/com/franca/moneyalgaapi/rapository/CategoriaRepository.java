package com.franca.moneyalgaapi.rapository;

import com.franca.moneyalgaapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT c FROM Categoria c WHERE UPPER(TRIM(c.nome)) LIKE %?1%")
    List<Categoria> buscarCategoriaNome(String nomeCategoria);
}