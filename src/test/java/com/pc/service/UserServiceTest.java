package com.pc.service;


import com.pc.model.User;
import com.pc.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*

With @SpringBootTest, the entire application context is loaded,
including all beans and configurations.
It's suitable for end-to-end testing and testing the interaction between different layers of the application.
However, it's slower compared to more focused testing annotations like @WebMvcTest or @DataJpaTest.
*/

@SpringBootTest
public class UserServiceTest {


    @InjectMocks
    UserService userService;

    @Mock // @Mock: It is used in unit tests to create a mock object of a class or interface.
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
        User insertedUser = userService.createUser(user);
        assertEquals("Pradip" , insertedUser.getFirstName());

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


    @Test
    public void testGetUserByEmail() {
        // given
        User user1 = new User();
        user1.setFirstName("sachin");
        user1.setLastName("kumar");
        user1.setEmail("sachin@example.com");

        // when
        Mockito.when(userRepositoryObject.findByEmail("sachin@example.com")).thenReturn(user1);

        // then
        User foundUser = userService.getUserByEmail("sachin@example.com");

        assertEquals("sachin", foundUser.getFirstName());
        assertEquals("kumar", foundUser.getLastName());
        assertEquals("sachin@example.com", foundUser.getEmail());
    }

    @Test
    public void testGetUsersByLastName() {
        // given
        User user1 = new User();
        user1.setFirstName("sachin");
        user1.setLastName("kumar");
        user1.setEmail("sachin@example.com");

        User user2 = new User();
        user2.setFirstName("virat");
        user2.setLastName("kumar");
        user2.setEmail("virat@example.com");

        List<User> users = Arrays.asList(user1, user2);

        // when
        Mockito.when(userRepositoryObject.findByLastName("kumar")).thenReturn(users);

        // then
        List<User> foundUsers = userService.getUsersByLastName("kumar");

        assertEquals(2, foundUsers.size());
        assertEquals("sachin", foundUsers.get(0).getFirstName());
        assertEquals("kumar", foundUsers.get(0).getLastName());
        assertEquals("sachin@example.com", foundUsers.get(0).getEmail());
        assertEquals("virat", foundUsers.get(1).getFirstName());
        assertEquals("kumar", foundUsers.get(1).getLastName());
        assertEquals("virat@example.com", foundUsers.get(1).getEmail());
    }



}
