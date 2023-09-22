package com.tms.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tms.domain.UserInfo;
import com.tms.security.filter.JwtAuthenticationFilter;
import com.tms.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    static List<UserInfo> users = new ArrayList<>();

    @BeforeAll
    public  static  void beforeALL(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(5);
        users.add(userInfo);
    }

    @Test
    public void getUsersTest() throws Exception {
        // моки (если юс верни колл)
        Mockito.when(userService.getUsers()).thenReturn(users);

        // логика, посылает запрос
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(5)));
    }
}
