package com.franca.moneyalgaapi.resource;


import com.franca.moneyalgaapi.repository.AcessoRepository;
import com.franca.moneyalgaapi.service.AcessoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acessos")
@Api(value = "entry-point para gerenciar acessos dos usuarios ''PERMISSÕES'' ", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, tags = {"entrypoint-acessos"})
public class AcessoResource {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private AcessoRepository acessoRepository;
    private AcessoService acessoService;

    @Autowired
    public AcessoResource(AcessoRepository acessoRepository, AcessoService acessoService) {
        this.acessoRepository = acessoRepository;
        this.acessoService = acessoService;
    }
}
