package xyz.parkh.campus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.parkh.campus.dto.ResponseDTO;
import xyz.parkh.campus.entity.TestEntity;
import xyz.parkh.campus.repository.TestRepository;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/hello")
    public String test1(Integer id) {
        System.out.println("TestController.test1");
        String message = "hello ";
        if (id != null) {
            Optional<TestEntity> name = testRepository.findById(id);
            message = message + name.get().getName();
        }
        return message;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(String msg) {
        ArrayList<String> list = new ArrayList<String>();
        if (msg == null) {
            list.add("test");
        } else {
            list.add(msg);
        }
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

}
