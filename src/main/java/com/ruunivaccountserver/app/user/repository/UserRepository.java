package com.ruunivaccountserver.app.user.repository;

import com.ruunivaccountserver.app.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailAndPassword(String email,String password);
}
