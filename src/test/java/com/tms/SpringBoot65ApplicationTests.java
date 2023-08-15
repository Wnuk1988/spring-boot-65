package com.tms;

import com.tms.controller.UserController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringBoot65ApplicationTests {
    @Autowired
    public UserController userController;
    @Test
    void contextLoads() {
        Assertions.assertThat(userController).isNotNull();
    }

}
