package com.tms.controller;

import com.tms.domain.Role;
import com.tms.domain.UserInfo;
import com.tms.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Matcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Наш класс")
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    UserService userService;
    static UserInfo userInfo;
    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    public static void beforeAll() {
        userInfo = new UserInfo();
        userInfo.setId(10);
        userInfo.setFirstName("Mikhail");
        userInfo.setLastName("Unuchko");
        userInfo.setRole(Role.USER);
    }

    @Test
    public void getUserTest() throws Exception {
        int userId = 10; // либо anyInt()

        Mockito.when(userService.getUser(userId)).thenReturn(userInfo);

        mockMvc.perform(get("/user/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Mikhail"));
        // что на метод + путь будет отпровлять нужный метод
        // статускод какойбудет возврощаться
        // сколько оъбектов должно возврощаться
        // проверим корректность данных
    }

//    @AfterAll
//    public static void afterAll(){
//        System.out.println("After all!");
//    }
//    @BeforeAll
//    public static void beforeAll(){
//        System.out.println("Before all!");
//    }
//    @BeforeEach
//    public void beforeEach(){
//        System.out.println("Before each!");
//    }
//    @AfterEach
//    public void afterEach(){
//        System.out.println("After each!");
//    }
//    @Test
//    @DisplayName("Наш метод")
//    @RepeatedTest(3)
//    @Tag("user")
//    public void getUserTest(){
//
//    }
//    @Test
//    @Disabled
//    public void getUserSecondTest(){
//
//    }
}

