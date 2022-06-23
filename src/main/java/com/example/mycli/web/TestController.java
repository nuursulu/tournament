package com.example.mycli.web;

import com.example.mycli.MyCliApplication;
import com.example.mycli.exceptions.AuthenticationFailed;
import com.example.mycli.model.UserEntity;
import com.example.mycli.exceptions.AccountBadRequest;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class TestController {


}

