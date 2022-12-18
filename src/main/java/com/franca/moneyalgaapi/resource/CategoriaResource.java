package com.franca.moneyalgaapi.resource;

import com.franca.moneyalgaapi.model.Categoria;
import com.franca.moneyalgaapi.rapository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaResource(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/listartodas")
    public List<Categoria> listar(){

        return categoriaRepository.findAll();

    }

    @PostMapping("/cadastrarCategoria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse response){

       Categoria categoriaSalva =  categoriaRepository.save(categoria);

       URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();

        response.setHeader("Location", uri.toASCIIString());

        return new ResponseEntity<Categoria>(categoriaSalva, HttpStatus.CREATED);

    }

    @GetMapping("**/busca/{codigoCategoria}")
    public ResponseEntity<Categoria> buscarCategoriaPorID(@PathVariable Long codigoCategoria)  {

        Optional<Categoria> categoria = categoriaRepository.findById(codigoCategoria);

        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();

    }
}