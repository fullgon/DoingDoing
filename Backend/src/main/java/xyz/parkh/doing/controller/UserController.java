package xyz.parkh.doing.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.User;
import xyz.parkh.doing.service.UserService;


@Log4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable("userId") String userId) {
        User userByUserId = userService.getUserByUserId(userId);
        log.info("userByUserId = " + userByUserId);
        return userByUserId;
    }

    @GetMapping(value = "/responseEntity/{userId}")
    public ResponseEntity<User> getResponseEntityUserInfo(@PathVariable("userId") String userId) {
        User userByUserId = userService.getUserByUserId(userId);
        log.info("userByUserId = " + userByUserId);
        return ResponseEntity.ok().body(userByUserId);
    }
}
