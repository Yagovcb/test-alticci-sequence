package com.praxis.praxis_users.application.services;

import com.praxis.praxis_users.domain.model.User;
import com.praxis.praxis_users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UsuarioDetailService")
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsuario = usuarioRepository.findByUsername(username);
        return optionalUsuario.map(usuario -> new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnabled(),
                true, true, true,
                usuario.getAuthorities()
        )).orElse(null);
    }
}