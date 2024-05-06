package com.pc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pc.model.User;
import com.pc.service.UserService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*

 It is used for testing Spring MVC controllers.
 With @WebMvcTest, only the MVC components of your application are loaded into the Spring ApplicationContext.
 Other components like repositories and services are mocked automatically.

*/
@SpringBootTest
@AutoConfigureMockMvc
/*@WebMvcTest*/
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean  // @MockBean is particularly useful when you need to mock components like repositories or services in integration tests.
    private UserService userService;


    @Test
    @Order(1)
    public void createUserTest() throws Exception {

     // given
        User user = new User();
        user.setId(101l);
        user.setFirstName("Pradip");
        user.setLastName("c");
        user.setEmail("pc@gmail.com");

        String jsonData = objectMapper.writeValueAsString(user);

        // when
        when( userService.createUser(user)).thenReturn( user );


      // then
      mockMvc.perform(
              post("/create")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content( jsonData ))
             /* .andExpect( status().isCreated()  );*/
              .andExpect( status().isCreated()  );


    }


    @Test
    @Order(2)
    public void getAllUsersTest() throws Exception {
        // given
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(101L);
        user1.setFirstName("Pradip");
        user1.setLastName("c");
        user1.setEmail("pc@gmail.com");
        users.add(user1);

        when(userService.getAllUsers()).thenReturn(users);

        // then
        mockMvc.perform(get("/get"))
                .andExpect(status().isOk());
    }


    @Test
    @Order(3)
    public void deleteUserTest() throws Exception {
        // given
        Long userId = 101L;

        // when
       doNothing( ).when( userService ).deleteUser(userId);

        // then
        mockMvc.perform(delete("/delete/{id}", userId))
                .andExpect(status().isOk());

    }

    @Test
    @Order(4)
    public void updateUserTest() throws Exception {
        // given
        Long userId = 101L;

        User oldUser = new User();
        oldUser.setId(userId);
        oldUser.setFirstName("old_Updated");
        oldUser.setLastName("old_Name");
        oldUser.setEmail("old_updated_email@gmail.com");


        User user = new User();
        user.setId(userId);
        user.setFirstName("Updated");
        user.setLastName("Name");
        user.setEmail("updated_email@gmail.com");

        String jsonData = objectMapper.writeValueAsString(user);

        // when
        when(userService.getUserById(Mockito.anyLong())).thenReturn(oldUser);

        // then
        mockMvc.perform(
                        put("/update/{id}", userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonData))
                .andExpect(status().isOk());
    }



    @Test
    @Order(5)
    public void testGetUserByEmail() throws Exception {
        User user1 = new User();
        user1.setFirstName("sachin");
        user1.setLastName("kumar");
        user1.setEmail("sachin@example.com");

        Mockito.when(userService.getUserByEmail("sachin@example.com")).thenReturn(user1);

        mockMvc.perform(get("/getByEmail/{lastName}","sachin@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @Order(6)
    public void testGetUsersByLastName() throws Exception {
        User user1 = new User();
        user1.setFirstName("sachin");
        user1.setLastName("kumar");
        user1.setEmail("sachin@example.com");

        User user2 = new User();
        user2.setFirstName("virat");
        user2.setLastName("kumar");
        user2.setEmail("virat@example.com");

        List<User> users = Arrays.asList(user1, user2);

        Mockito.when(userService.getUsersByLastName("kumar")).thenReturn(users);

        mockMvc.perform(get("/getByLastName/kumar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




}
