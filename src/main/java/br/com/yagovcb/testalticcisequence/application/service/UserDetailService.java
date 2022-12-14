package br.com.yagovcb.testalticcisequence.application.service;

import br.com.yagovcb.testalticcisequence.domain.model.User;
import br.com.yagovcb.testalticcisequence.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public User getUserByUsername(String userName){
        Optional<User> usuarioOptional = usuarioRepository.findByUsername(userName);
        return usuarioOptional.orElseThrow(() ->  new EntityNotFoundException("Usuario não encontrado"));
    }
}