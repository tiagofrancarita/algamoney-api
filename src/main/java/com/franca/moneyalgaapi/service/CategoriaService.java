package com.franca.moneyalgaapi.service;

import com.franca.moneyalgaapi.model.Categoria;
import com.franca.moneyalgaapi.repository.CategoriaRepository;
import com.franca.moneyalgaapi.resource.CategoriaResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final CategoriaRepository categoriaRepository;


    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria atualizarCategoria(Long codigoCategoria, Categoria categoria){

        Categoria categoriaAtualiza = categoriaRepository.findById(codigoCategoria)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(categoria, categoriaAtualiza,"codigoCategoria");

        return categoriaRepository.save(categoriaAtualiza);
    }
}
