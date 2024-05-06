package com.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.model.User;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findByLastName(String lastName);

}
