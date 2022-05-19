package xyz.parkh.doing.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String get() {
        log.info("Server Execution Successful");

        return "Server Execution Successful";
    }

}
