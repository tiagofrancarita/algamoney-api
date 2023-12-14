package com.franca.moneyalgaapi.repository;

import com.franca.moneyalgaapi.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
}
