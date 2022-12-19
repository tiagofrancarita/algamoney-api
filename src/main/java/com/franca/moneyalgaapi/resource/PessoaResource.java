package com.franca.moneyalgaapi.resource;

import com.franca.moneyalgaapi.event.RecursoCriadoEvent;
import com.franca.moneyalgaapi.model.Pessoa;
import com.franca.moneyalgaapi.rapository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final PessoaRepository pessoaRepository;

    private ApplicationEventPublisher publisher;

    @Autowired
    public PessoaResource(PessoaRepository pessoaRepository, ApplicationEventPublisher publisher) {
        this.pessoaRepository = pessoaRepository;
        this.publisher = publisher;
    }

    @GetMapping("/listartodas")
    public List<Pessoa> listar(){

        return pessoaRepository.findAll();

    }

    @PostMapping("/cadastrarPessoa")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> cadastrarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){

        Pessoa pessoaSalva =  pessoaRepository.save(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this,response,pessoaSalva.getCodigo()));

        return new ResponseEntity<Pessoa>(pessoaSalva, HttpStatus.CREATED);

    }

    @GetMapping("**/busca/{codigoPessoa}")
    public ResponseEntity<Pessoa> buscarPessoaPorID(@PathVariable Long codigoPessoa)  {

        Optional<Pessoa> pessoa = pessoaRepository.findById(codigoPessoa);

        return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();

    }
}