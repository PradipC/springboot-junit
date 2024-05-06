package com.pc.repository;

import com.pc.model.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 It is used for testing JPA repositories.
 With @DataJpaTest, only JPA-related components are loaded into the Spring ApplicationContext.
 This includes entities, repositories, and related configuration.

*/
@DataJpaTest  // performance wise it is fast compare to SpringBootTest becoz it just create required beans of entities , repositories layer only
public class UserRepositoryTest {

    @Autowired  // InjectMocks only works for classes and not work for Interface
    private UserRepository userRepository;

    @BeforeAll // This will execute before all test , execute only once
    public static void setup(@Autowired UserRepository userRepository) {

        System.out.println("***** This is Before All ");

        // Add two users to the database
        User user1 = new User();
        user1.setFirstName("sachin");
        user1.setLastName("kumar");
        user1.setEmail("sachin@exam ple.com");

        User user2 = new User();
        user2.setFirstName("virat");
        user2.setLastName("kumar");
        user2.setEmail("virat@example.com");

        userRepository.save(user1);
        userRepository.save(user2);
    }


    @BeforeEach  // This will execute before each test
    public void init(){

        System.out.println("***** This is before each ");
    }


    @Test
    public void findByEmailTest() {
        // given - creating the objects , defining the parameters we use this block
        String email = "virat@example.com";



        // when - define the method behaviours of mocking objects


        // then - define the assertions and call the real methods
        User foundUser = userRepository.findByEmail(email);

        assertNotNull(foundUser);
        assertEquals("virat", foundUser.getFirstName());
        assertEquals("kumar", foundUser.getLastName());
        assertEquals("virat@example.com", foundUser.getEmail());
    }

    @Test
    public void findByLastNameTest() {
        // give
        String lastName = "kumar";

        // when

        // then
        List<User> foundUsers = userRepository.findByLastName(lastName);
        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
        assertEquals("kumar", foundUsers.get(0).getLastName());
        assertEquals("kumar", foundUsers.get(1).getLastName());
    }


}
