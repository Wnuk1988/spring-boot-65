package com.tms.controller;

import com.tms.domain.Role;
import com.tms.domain.UserInfo;
import com.tms.execption.UserNotFoundException;
import com.tms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@PreAuthorize("hasAnyRole('USER')") // кому разрешено на контроллер
    @GetMapping
    public ResponseEntity<List<UserInfo>> getUsers() {
        //1. Вариант вывода пользователя, кто в системе:
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println(userName);

        // 2. Вариант вывода пользователя, кто в системе:
//        System.out.println(principal.getName());

        List<UserInfo> users = userService.getUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

//    @GetMapping("/all/{role}")
//    public ResponseEntity<List<UserInfo>> getUsersByRole(@PathVariable String role) {
//        List<UserInfo> users = userService.findAllByRole(Role.valueOf(role));
//        if (users.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        }
//    }

    @GetMapping("/last/{lastName}")
    public ResponseEntity<UserInfo> getUserByLastName(@PathVariable String lastName) {
        UserInfo user = userService.findUserByLastName(lastName).orElseThrow(UserNotFoundException::new);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUser(@PathVariable @Parameter(description = "Это id пользователя") Integer id) {
        UserInfo user = userService.getUser(id).orElseThrow(UserNotFoundException::new);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@Hidden - скрывает метод, но не убирает!
    @Operation(summary = "Добовляем пользователя", description = "Мы добовляем пользователя, нужно на вход передать json UserInfo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Мы успешно создали пользователя."),
            @ApiResponse(responseCode = "400", description = "Ошибка на стороне клиента."),
    })
    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserInfo userInfo) {
        userService.createUser(userInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "Test tag", description = "This is our test tag description!")
    @PutMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserInfo userInfo) {
        userService.updateUser(userInfo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
