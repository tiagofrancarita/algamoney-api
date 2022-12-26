package com.franca.moneyalgaapi.service;

import com.franca.moneyalgaapi.model.Usuario;
import com.franca.moneyalgaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UserDetailServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findUserByLogin(username);

        if (usuario == null){
            throw new UsernameNotFoundException("Usuário inexistente");
        }

        return new User(usuario.getLogin(),usuario.getPassword(),usuario.getAuthorities());
    }
}