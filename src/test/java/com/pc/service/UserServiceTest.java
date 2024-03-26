package com.pc.service;


import com.pc.model.User;
import com.pc.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {


    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepositoryObject;


    @Test
    public void createUserTest() {
        //given
        User user = new User();
        user.setId(101l);
        user.setFirstName("Pradip");
        user.setLastName("c");
        user.setEmail("pc@gmail.com");

        // when
        Mockito.when( userRepositoryObject.save(Mockito.any(User.class))).thenReturn( user );

        // then
        userService.createUser(user);
    }


    @Test
    public void createUserExceptionTest() {
        //given
        User user = null;

        // when
        Mockito.when( userRepositoryObject.save(Mockito.any(User.class))).thenReturn( null );

        // then

      RuntimeException returnException = Assertions.assertThrows( RuntimeException.class , () -> {
              userService.createUser(user);
            }
        );

      assertTrue( returnException.getMessage().contains( "User creation failed"  )  );

    }




}
