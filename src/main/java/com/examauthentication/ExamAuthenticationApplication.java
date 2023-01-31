package com.examauthentication;

import com.examauthentication.models.Task;
import com.examauthentication.repositories.TaskRepository;
import com.examauthentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ExamAuthenticationApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExamAuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
