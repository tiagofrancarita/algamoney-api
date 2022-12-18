package com.franca.moneyalgaapi.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgaMoneyExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public AlgaMoneyExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
        String mensagemLog = ex.getCause().toString();
        return handleExceptionInternal(ex,new Erro(mensagemUsuario, mensagemLog),headers ,HttpStatus.BAD_REQUEST, request);
    }

    public static class Erro{

        private String mensagemUsuario;
        private String mensagemLog;

        public Erro(String mensagemUsuario, String mensagemLog) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemLog = mensagemLog;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemLog() {
            return mensagemLog;
        }
    }
}