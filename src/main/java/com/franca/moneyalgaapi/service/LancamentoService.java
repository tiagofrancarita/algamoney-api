package com.franca.moneyalgaapi.service;


import com.franca.moneyalgaapi.model.Lancamento;
import com.franca.moneyalgaapi.model.Pessoa;
import com.franca.moneyalgaapi.rapository.LancamentoRepository;
import com.franca.moneyalgaapi.rapository.PessoaRepository;
import com.franca.moneyalgaapi.resource.CategoriaResource;
import com.franca.moneyalgaapi.service.exception.PessoaInexistenteOuInativaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;


    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository, PessoaRepository pessoaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Lancamento atualizarLancamento(Long codigoLancamento, Lancamento lancamento){

        Lancamento LancamentoAtualiza = lancamentoRepository.findById(codigoLancamento)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(lancamento, LancamentoAtualiza,"codigoLancamento");

        return lancamentoRepository.save(LancamentoAtualiza);
    }

    public Lancamento salvarLancamento(Lancamento lancamento) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

        if (pessoa.isEmpty() || pessoa.get().isInativo ()){
                throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
}