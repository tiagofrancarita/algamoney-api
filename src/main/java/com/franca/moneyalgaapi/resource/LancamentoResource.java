package com.franca.moneyalgaapi.resource;


import com.franca.moneyalgaapi.event.RecursoCriadoEvent;
import com.franca.moneyalgaapi.exceptionhandler.AlgaMoneyExceptionHandler;
import com.franca.moneyalgaapi.model.Lancamento;
import com.franca.moneyalgaapi.repository.LancamentoRepository;
import com.franca.moneyalgaapi.repository.filter.LancamentoFilter;
import com.franca.moneyalgaapi.service.LancamentoService;
import com.franca.moneyalgaapi.service.exception.PessoaInexistenteOuInativaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
@Api(value = "entry-point para gerenciar lançamentos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, tags = {"entrypoint-lançamentos"})
public class LancamentoResource {

    private static final Logger logger = LoggerFactory.getLogger(LancamentoResource.class);

    private final LancamentoResource lancamentoResource;
    private final LancamentoRepository lancamentoRepository;
    private  final LancamentoService lancamentoService;

    private ApplicationEventPublisher publisher;
    private MessageSource messageSource;

    @Autowired
    public LancamentoResource(@Lazy LancamentoResource lancamentoResource,
                              LancamentoRepository lancamentoRepository, LancamentoService lancamentoService,
                              ApplicationEventPublisher publisher, MessageSource messageSource) {

        this.lancamentoResource = lancamentoResource;
        this.lancamentoRepository = lancamentoRepository;
        this.lancamentoService = lancamentoService;
        this.publisher = publisher;
        this.messageSource = messageSource;
    }

    @ApiOperation(value = "Lista todas os lançamentos cadastrados")
    @GetMapping(value = "/listarTodosLancamentos")
    public List<Lancamento> listarTodosLancamentos() {

        return lancamentoRepository.findAll();

    }

    @GetMapping(value = "/listarPesquisa")
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){

        return lancamentoRepository.filtrar(lancamentoFilter, pageable);

    }

    @GetMapping("**/busca/{codigoLancamento}")
    @ApiOperation(value = "Busca lançamento cadastrado por ID")
    public ResponseEntity<Lancamento> buscarLancamentoPorID(@PathVariable Long codigoLancamento)  {

        Optional<Lancamento> lancamento = lancamentoRepository.findById(codigoLancamento);
        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();

    }

    @ApiOperation(value = "Cadastra um novo lançamento")
    @PostMapping("/cadastrarLancamento")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Lancamento> cadastrarCategoria(@Valid @RequestBody Lancamento lancamento , HttpServletResponse response) {

        Lancamento lancamentoSalvo = lancamentoService.salvarLancamento(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return new ResponseEntity<Lancamento>(lancamentoSalvo, HttpStatus.CREATED);

    }
    @ApiOperation(value = "Deleta um lançamento por ID")
    @DeleteMapping("/deletarLancamentoID/{codigoLancamento}")
    public ResponseEntity<String> deletarLancamentoID(@PathVariable("codigoLancamento") Long codigoLancamento) {

        lancamentoRepository.deleteById(codigoLancamento);
        return new ResponseEntity<String>("Lancamento Excluido", HttpStatus.OK);

    }

    @ApiOperation(value = "Busca lançamento pela descrição.")
    @ResponseBody
    @GetMapping("**/buscarLancamentoDescricao/{descricaoLancamento}")
    public ResponseEntity<List<Lancamento>> buscarLancamentoDescricao(@PathVariable("descricaoLancamento") String descricaoLancamento) {

        List<Lancamento> lancamentos = lancamentoRepository.buscarLancamentoNome(descricaoLancamento.toUpperCase());

        return new ResponseEntity<List<Lancamento>>(lancamentos, HttpStatus.OK);

    }

    @ApiOperation(value = "Atualiza um lancamento")
    @PutMapping("**/atualizarLancamento/{codigoLancamento}")
    public ResponseEntity<Lancamento> atualizarPessoa(@PathVariable("codigoLancamento") Long codigoLancamento, @Valid @RequestBody Lancamento lancamento) {

        Lancamento lancamentoAtualiza = lancamentoService.atualizarLancamento(codigoLancamento,lancamento);
        return ResponseEntity.ok(lancamentoAtualiza);
    }

    @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<AlgaMoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgaMoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}