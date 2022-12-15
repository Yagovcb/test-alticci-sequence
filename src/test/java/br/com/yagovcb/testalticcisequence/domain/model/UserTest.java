package br.com.yagovcb.testalticcisequence.domain.model;

import br.com.yagovcb.testalticcisequence.domain.enums.Role;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.yagovcb.testalticcisequence.domain.mock.UserMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    void testGetAuthorities() {
        assertTrue((new User()).getAuthorities().isEmpty());
    }

    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    void testGetAuthoritiesWithUser() {
        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(Role.ROLE_USER);
        assertEquals(1, (new User(123L, "janedoe", "iloveyou", "jane.doe@example.org", true, true,
                LocalDate.ofEpochDay(1L), "42", "Dr Jane Doe", roleSet)).getAuthorities().size());
    }

    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    void testGetAuthoritiesWithCustomUser() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.ROLE_ADMIN);
        roleSet.add(Role.ROLE_USER);
        User user = UserMock.getUserWithFullRoleMock();
        assertEquals(user.getRoles(), roleSet);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User()}
     *   <li>{@link User#isAccountNonExpired()}
     *   <li>{@link User#isAccountNonLocked()}
     *   <li>{@link User#isCredentialsNonExpired()}
     *   <li>{@link User#isEnabled()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        assertTrue(actualUser.isAccountNonExpired());
        assertTrue(actualUser.isAccountNonLocked());
        assertTrue(actualUser.isCredentialsNonExpired());
        assertFalse(actualUser.isEnabled());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals() {
        User user = new User();
        user.setActive(true);
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setMobile(true);
        user.setNumberMobile("42");
        user.setPassword("iloveyou");
        user.setRegistrationDate(LocalDate.ofEpochDay(1L));
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");

        User otherUser = UserMock.getUserWithUser_RoleMock();
        assertNotEquals(otherUser, user);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, otherUser.hashCode());
    }

    @Test
    void testGetterSetter(){
        User user = UserMock.getUserWithUser_RoleMock();
        assertNull(user.getId());
        user.setId(1L);
        assertNotNull(user.getId());
        assertEquals("teste_user", user.getUsername());
        assertEquals("testeUser123", user.getPassword());
        assertEquals("teste@teste.com", user.getEmail());
        assertTrue(user.isActive());
        assertFalse(user.isMobile());
        assertEquals(LocalDate.now(), user.getRegistrationDate());
        assertEquals("11 911112222", user.getNumberMobile());
        assertEquals("Usuario de teste para o sistema com Role User", user.getFullName());
    }
}

