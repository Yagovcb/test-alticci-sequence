package com.praxis.praxis_users.domain.repository;

import com.praxis.praxis_users.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndEmail(String username, String email);

    boolean existsUserByUsernameAndEmail(String username, String email);

    List<User> findAllByActive(Boolean isEnabled);
}
