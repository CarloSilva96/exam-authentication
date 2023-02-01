package com.examauthentication.controllers;

import com.examauthentication.dtos.user.UserCreateDto;
import com.examauthentication.infrastructure.Routes;
import com.examauthentication.repositories.TaskRepository;
import com.examauthentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @PostMapping(Routes.ExamAuth.User.PATH)
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        userService.saveUser(userCreateDto);
        return ResponseEntity.ok().build();
    }
}
