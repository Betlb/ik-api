package com.magaza.dukkan.repository;

import com.magaza.dukkan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> getUserByUsernameEquals(String username);

}
