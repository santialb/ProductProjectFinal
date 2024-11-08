package com.example.finalExam.security;

import com.example.finalExam.repositories.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NewUserHandler implements Command<LoginRequest, LoginResponse> {
    private final CustomUserRepository customUserRepository;
    private final BCryptPasswordEncoder encoder;
    private final LoginHandler loginHandler;

    public NewUserHandler(CustomUserRepository customUserRepository, BCryptPasswordEncoder encoder, LoginHandler loginHandler) {
        this.customUserRepository = customUserRepository;
        this.encoder = encoder;
        this.loginHandler = loginHandler;
    }

    @Override
    public ResponseEntity<LoginResponse> execute(LoginRequest request) {
        CustomUser user = new CustomUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        customUserRepository.save(user);

        return loginHandler.execute(request);
    }
}
