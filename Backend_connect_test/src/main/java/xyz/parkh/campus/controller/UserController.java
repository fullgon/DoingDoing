package xyz.parkh.campus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.campus.dto.ResponseDTO;
import xyz.parkh.campus.dto.UserDTO;
import xyz.parkh.campus.entity.User;
import xyz.parkh.campus.security.TokenProvider;
import xyz.parkh.campus.service.UserService;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {


}
