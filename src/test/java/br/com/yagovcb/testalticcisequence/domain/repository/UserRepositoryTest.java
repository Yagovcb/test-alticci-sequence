package br.com.yagovcb.testalticcisequence.domain.repository;

import br.com.yagovcb.testalticcisequence.domain.mock.UserMock;
import br.com.yagovcb.testalticcisequence.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("Teste da classe de repository UserRepository")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        userRepository.save(UserMock.getUserWithAdmin_RoleMock());
    }

    @Test
    @DisplayName("Teste do método findByUsername do repository")
    void findByUsernameTest(){
        var username = UserMock.getUserWithAdmin_RoleMock().getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);

        assertTrue(optionalUser.isPresent());
        assertNotNull(optionalUser.get());
        assertEquals(username, optionalUser.get().getUsername());

    }
}
