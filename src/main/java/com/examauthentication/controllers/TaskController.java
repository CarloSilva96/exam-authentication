package com.examauthentication.controllers;

import com.examauthentication.infrastructure.Routes;
import com.examauthentication.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping(Routes.ExamAuth.Task.PATH)
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(taskRepository.findAll());
    }
}
