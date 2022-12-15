package br.com.yagovcb.testalticcisequence.domain.mock;

import br.com.yagovcb.testalticcisequence.domain.enums.Role;
import br.com.yagovcb.testalticcisequence.domain.model.User;

import java.time.LocalDate;
import java.util.Set;

public class UserMock {

    public static User getUserWithUser_RoleMock(){
        return User.builder()
                .username("teste_user")
                .password("testeUser123")
                .email("teste@teste.com")
                .active(true)
                .mobile(false)
                .registrationDate(LocalDate.now())
                .numberMobile("11 911112222")
                .fullName("Usuario de teste para o sistema com Role User")
                .roles(Set.of(Role.ROLE_USER))
                .build();
    }

    public static User getUserWithAdmin_RoleMock(){
        return User.builder()
                .username("teste_admin")
                .password("testeAdmin123")
                .email("teste_admin@teste.com")
                .active(true)
                .mobile(false)
                .registrationDate(LocalDate.now())
                .numberMobile("11 911112222")
                .fullName("Usuario de teste para o sistema com Role Admin")
                .roles(Set.of(Role.ROLE_ADMIN))
                .build();
    }

    public static User getUserWithFullRoleMock(){
        return User.builder()
                .username("teste_full")
                .password("testeFul123")
                .email("teste_full@teste.com")
                .active(true)
                .mobile(false)
                .registrationDate(LocalDate.now())
                .numberMobile("11 911112222")
                .fullName("Usuario de teste para o sistema com todas as Roles")
                .roles(Set.of(Role.ROLE_ADMIN, Role.ROLE_USER))
                .build();
    }
}
