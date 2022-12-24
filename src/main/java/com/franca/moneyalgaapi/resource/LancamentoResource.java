package com.franca.moneyalgaapi.resource;


import com.franca.moneyalgaapi.model.Lancamento;
import com.franca.moneyalgaapi.rapository.LancamentoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
@Api(value = "entry-point para gerenciar lançamentos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, tags = {"entrypoint-lançamentos"})
public class LancamentoResource {

    private static final Logger logger = LoggerFactory.getLogger(LancamentoResource.class);

    private final LancamentoResource lancamentoResource;
    private final LancamentoRepository lancamentoRepository;

    @Autowired
    public LancamentoResource(@Lazy LancamentoResource lancamentoResource, LancamentoRepository lancamentoRepository) {
        this.lancamentoResource = lancamentoResource;
        this.lancamentoRepository = lancamentoRepository;


    }

    @ApiOperation(value = "Lista todas os lançamentos cadastrados")
    @GetMapping(value = "/listartodas")
    public List<Lancamento> listar() {

        return lancamentoRepository.findAll();

    }

}