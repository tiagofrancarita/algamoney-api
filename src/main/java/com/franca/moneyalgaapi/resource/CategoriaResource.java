package com.franca.moneyalgaapi.resource;

import com.franca.moneyalgaapi.model.Categoria;
import com.franca.moneyalgaapi.rapository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaResource(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/listartodas")
    public List<Categoria> listar(){

        return categoriaRepository.findAll();

    }

}