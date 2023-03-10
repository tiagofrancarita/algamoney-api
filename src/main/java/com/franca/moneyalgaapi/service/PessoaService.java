package com.franca.moneyalgaapi.service;

import com.franca.moneyalgaapi.model.Pessoa;
import com.franca.moneyalgaapi.repository.PessoaRepository;
import com.franca.moneyalgaapi.resource.CategoriaResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final PessoaRepository  pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa atualizarPessoa(Long codigoPessoa, Pessoa pessoa){

        Pessoa pessoaAtualizar = pessoaRepository.findById(codigoPessoa)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(pessoa, pessoaAtualizar,"codigoPessoa");

        return pessoaRepository.save(pessoaAtualizar);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean inativo) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(inativo);
        pessoaRepository.save(pessoaSalva);
    }

    public Pessoa buscarPessoaPeloCodigo(Long codigo) {
        Pessoa pessoaSalva =  pessoaRepository.findById(codigo)
                                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        return pessoaSalva;
    }
}
