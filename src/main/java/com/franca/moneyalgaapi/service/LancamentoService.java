package com.franca.moneyalgaapi.service;


import com.franca.moneyalgaapi.model.Categoria;
import com.franca.moneyalgaapi.model.Lancamento;
import com.franca.moneyalgaapi.rapository.LancamentoRepository;
import com.franca.moneyalgaapi.resource.CategoriaResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final LancamentoRepository lancamentoRepository;


    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public Lancamento atualizarLancamento(Long codigoLancamento, Lancamento lancamento){

        Lancamento LancamentoAtualiza = lancamentoRepository.findById(codigoLancamento)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(lancamento, LancamentoAtualiza,"codigoLancamento");

        return lancamentoRepository.save(LancamentoAtualiza);
    }
}