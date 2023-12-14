package com.franca.moneyalgaapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;


    private String login;


    private String senha;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataAtualSenha;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "codigo_pessoa", nullable = false,
                foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_usuarioPessoa"))
    private Pessoa pessoa;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_acesso", uniqueConstraints = @UniqueConstraint (columnNames = {"codigo_usuario", "codigo_acesso"} , name = "unique_acesso_user"),
            joinColumns = @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo", table = "usuario", unique = false,
                                                    foreignKey = @ForeignKey(name = "fk_usuarioAcesso", value = ConstraintMode.CONSTRAINT)),
                    inverseJoinColumns = @JoinColumn(name = "codigo_acesso", unique = false, referencedColumnName = "codigo", table = "acesso",
                                                    foreignKey = @ForeignKey(name = "aesso_fk", value = ConstraintMode.CONSTRAINT)))
    private List<Acesso> acessos;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataAtualSenha() {
        return dataAtualSenha;
    }

    public void setDataAtualSenha(LocalDate dataAtualSenha) {
        this.dataAtualSenha = dataAtualSenha;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Acesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<Acesso> acessos) {
        this.acessos = acessos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.acessos;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(codigo, usuario.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}

