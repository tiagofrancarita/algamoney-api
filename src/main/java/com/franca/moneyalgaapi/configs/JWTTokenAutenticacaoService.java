package com.franca.moneyalgaapi.configs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Service
@Component
public class JWTTokenAutenticacaoService {

    // Tempo de validade 30 min
    private static final long EXPIRATION_TIME =	1800000;

    //Chave para concatenar com o JWT --> API-GERENCIAMENTO-FINANCAS-2022
    //MD5 Hash	7a470cdb35fb87bf12340e8b6c67f5d3
    //SHA1 Hash	c8c584af49a01643b2ad7f0959933afcb5e2e118
    private static final String SECRET = "c8c584af49a01643b2ad7f0959933afcb5e2e118";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";


    public void addAuthenticator(HttpServletResponse response, String username) throws Exception {

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS512,SECRET).compact();

        String token = TOKEN_PREFIX + " " + JWT;


        response.addHeader(HEADER_STRING,token);

        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");


    }









}