package com.alex.bank.repository;


import com.alex.bank.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true,value = "SELECT EXISTS (SELECT 1 FROM users WHERE email=:email)")
    boolean existsByEmailNative(String email);
    @Query("SELECT u FROM User u join fetch u.roles WHERE u.id=:id")
    User findUserById(Long id);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findUserByUsername(String username);
}
