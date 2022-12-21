package com.franca.moneyalgaapi.resource;

import com.franca.moneyalgaapi.event.RecursoCriadoEvent;
import com.franca.moneyalgaapi.model.Pessoa;
import com.franca.moneyalgaapi.rapository.PessoaRepository;
import com.franca.moneyalgaapi.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@Api(value = "Test API Controller", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"test-api-controller"},
// description = "Testing API")
@ApiOperation(value = "EntryPointPessoas", produces = MediaType.APPLICATION_JSON_VALUE,tags = {"entrypoint-pessoas"})
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final PessoaRepository pessoaRepository;
    private final PessoaService pessoaService;

    private ApplicationEventPublisher publisher;

    @Autowired
    public PessoaResource(PessoaRepository pessoaRepository, PessoaService pessoaService, ApplicationEventPublisher publisher) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
        this.publisher = publisher;
    }
    @ApiOperation(value = "Lista Pessoas",tags = {"entrypoint-listar-pessoas"})
    @GetMapping(value = "/listartodas", produces = "application/json", consumes = "application/json")
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

    @DeleteMapping("**/deletarPessoaId/{codigoPessoa}")
    public ResponseEntity<String> deletarPessoaId(@PathVariable("codigoPessoa") Long codigoPessoa){

        pessoaRepository.deleteById(codigoPessoa);
        return new ResponseEntity<String>("Acesso Excluido", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("**/buscarPessoaNome/{nomePessoa}")
    public ResponseEntity<List<Pessoa>> buscarCategoriaNome(@PathVariable("nomePessoa") String nomePessoa){

        List<Pessoa>  pessoas = pessoaRepository.buscarPessoaNome(nomePessoa.toUpperCase());
        return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);

    }

    @PutMapping("**/atualizarPessoa/{codigoPessoa}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable("codigoPessoa") Long codigoPessoa, @Valid @RequestBody Pessoa pessoa){

        Pessoa pessoaAtualizar = pessoaService.atualizarPessoa(codigoPessoa,pessoa);

        return ResponseEntity.ok(pessoaAtualizar);
    }
}