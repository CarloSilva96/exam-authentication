package com.examauthentication.repositories;

import com.examauthentication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT user FROM User user WHERE user.email = :login OR user.cpf = :login")
    UserDetails getUserByLogin(@Param("login") String login);

    boolean existsUserByEmail(String email);
    boolean existsUserByCpf(String cpf);
}
