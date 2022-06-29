package com.example.mycli.controller;

import com.example.mycli.config.jwt.JwtProvider;
import com.example.mycli.exceptions.accountException.AccountCheckLoginPassword;
import com.example.mycli.exceptions.accountException.AccountConflict;
import com.example.mycli.exceptions.accountException.AccountCreated;
import com.example.mycli.exceptions.accountException.AccountNotFound;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.service.UserService;
import com.example.mycli.dto.AuthRequest;
import com.example.mycli.dto.AuthResponse;
import com.example.mycli.dto.RegRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final UserEntityRepository userEntityRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegRequest registrationRequest) {
        String login = registrationRequest.getLogin();
        UserEntity userEntity = userService.findByLogin(login);
        if (userEntity == null) {
            createUser(login, registrationRequest.getPassword());
            throw new AccountCreated(login);
        } else {
            throw new AccountConflict(login);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody @Valid AuthRequest authRequest,
                                       HttpServletResponse httpServletResponse) {
        String login = authRequest.getLogin();
        if (userEntityRepository.findByLogin(authRequest.getLogin()) == null) throw new AccountNotFound(login);
        UserEntity userEntity = userService.findByLoginAndPassword(login, authRequest.getPassword());
        if (userEntity != null) {
            String token = jwtProvider.generateToken(login);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully authenticated, your token: " +
            new AuthResponse(token).getToken());
        } else {
            throw new AccountCheckLoginPassword();
        }
    }
    @GetMapping("/users")
    public List<UserEntity> users() {
        if (userEntityRepository.findAll() != null) {
            return userEntityRepository.findAll();
        } else {
            throw new AccountCheckLoginPassword();
        }

    }

    @PostMapping("/logout")
    public void logout() {}

    private void createUser(String login, String password) {
        UserEntity user = new UserEntity();
        user.setPassword(password);
        user.setLogin(login);
        userService.saveUser(user);
    }

}

