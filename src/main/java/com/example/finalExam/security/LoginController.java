package com.example.finalExam.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginHandler loginHandler;
    private final NewUserHandler newUserHandler;

    public LoginController(LoginHandler loginHandler, NewUserHandler newUserHandler) {
        this.loginHandler = loginHandler;
        this.newUserHandler = newUserHandler;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return loginHandler.execute(request);
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<LoginResponse> createNewUser(@RequestBody LoginRequest request){
        return newUserHandler.execute(request);
    }
}
