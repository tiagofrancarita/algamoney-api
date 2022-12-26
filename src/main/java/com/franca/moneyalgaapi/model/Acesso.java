package com.franca.moneyalgaapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "acesso")
public class Acesso implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String descricao; /* Acesso ex: ROLE_ADMIN ou ROLE_SECRETARIO */


    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long id) {
        this.codigo = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acesso)) return false;
        Acesso acesso = (Acesso) o;
        return Objects.equals(codigo, acesso.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
