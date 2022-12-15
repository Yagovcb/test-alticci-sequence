package br.com.yagovcb.testalticcisequence.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import br.com.yagovcb.testalticcisequence.domain.mock.UserMock;
import br.com.yagovcb.testalticcisequence.domain.model.User;
import br.com.yagovcb.testalticcisequence.domain.repository.UserRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserDetailService.class})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserDetailService - Classe de teste unitario")
class UserDetailServiceTest {

    @Autowired
    private UserDetailService userDetailService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserDetailService#loadUserByUsername(String)}
     */
    @Test
    @Order(1)
    @DisplayName("Teste de serviço que tenta recuperar o UserDetails tomando como base o username do usuario")
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = UserMock.getUserWithFullRoleMock();
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsername(any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userDetailService.loadUserByUsername(user.getUsername());
        assertFalse(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals(user.getUsername(), actualLoadUserByUsernameResult.getUsername());
        assertEquals(user.getPassword(), actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUsername(any());
    }

    /**
     * Method under test: {@link UserDetailService#getUserByUsername(String)}
     */
    @Test
    @Order(2)
    @DisplayName("Teste de serviço que tenta recuperar o User tomando como base o username do usuario")
    void testGetUserByUsername() {
        User user = UserMock.getUserWithFullRoleMock();
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsername(any())).thenReturn(ofResult);
        assertSame(user, userDetailService.getUserByUsername("teste_full"));
        verify(userRepository).findByUsername(any());
    }

}

