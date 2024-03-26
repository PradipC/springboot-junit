package com.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

	
	
	
	
	
}
