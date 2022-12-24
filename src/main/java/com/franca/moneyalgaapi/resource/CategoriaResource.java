package com.franca.moneyalgaapi.resource;

import com.franca.moneyalgaapi.event.RecursoCriadoEvent;
import com.franca.moneyalgaapi.model.Categoria;
import com.franca.moneyalgaapi.rapository.CategoriaRepository;
import com.franca.moneyalgaapi.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@Api(value = "entry-point para gerenciar categorias", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, tags = {"entrypoint-categorias"})
public class CategoriaResource {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final CategoriaRepository categoriaRepository;

    private final CategoriaService categoriaService;

    private ApplicationEventPublisher publisher;

    @Autowired
    public CategoriaResource(CategoriaRepository categoriaRepository, CategoriaService categoriaService, ApplicationEventPublisher publisher) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaService = categoriaService;
        this.publisher = publisher;
    }

    @ApiOperation(value = "Lista todas as categorias cadastradas")
    @GetMapping("/listartodas")
    public List<Categoria> listar() {

        return categoriaRepository.findAll();

    }

    @ApiOperation(value = "Cadastrar uma nova categoria")
    @PostMapping("/cadastrarCategoria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {

        Categoria categoriaSalva = categoriaRepository.save(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

        return new ResponseEntity<Categoria>(categoriaSalva, HttpStatus.CREATED);

    }
    @ApiOperation(value = "Busca categoria cadastrada por ID")
    @GetMapping("/buscaCategoria/{codigoCategoria}")
    public ResponseEntity<Categoria> buscarCategoriaPorID(@PathVariable Long codigoCategoria) {

        Optional<Categoria> categoria = categoriaRepository.findById(codigoCategoria);

        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();

    }

    @ApiOperation(value = "deleta categoria cadastrada por ID")
    @DeleteMapping("/deletarCategoriaId/{codigoCategoria}")
    public ResponseEntity<String> deletarCategoriaId(@PathVariable("codigoCategoria") Long codigoCategoria) {

        categoriaRepository.deleteById(codigoCategoria);
        return new ResponseEntity<String>("Categoria Excluido", HttpStatus.OK);

    }

    @ApiOperation(value = "Busca categoria cadastrada por nome")
    @ResponseBody
    @GetMapping("**/buscarCategoriaNome/{nomeCategoria}")
    public ResponseEntity<List<Categoria>> buscarCategoriaNome(@PathVariable("nomeCategoria") String nomeCategoria) {

        List<Categoria> categorias = categoriaRepository.buscarCategoriaNome(nomeCategoria.toUpperCase());

        return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);

    }

    @ApiOperation(value = "Atualizar categoria cadastrada")
    @PutMapping("**/atualizarCategoria/{codigoCategoria}")
    public ResponseEntity<Categoria> atualizarPessoa(@PathVariable("codigoCategoria") Long codigoCategoria, @Valid @RequestBody Categoria categoria) {

        Categoria categoriaAtualiza = categoriaService.atualizarCategoria(codigoCategoria,categoria);

        return ResponseEntity.ok(categoriaAtualiza);
    }
}