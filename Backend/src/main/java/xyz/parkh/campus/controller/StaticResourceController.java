package xyz.parkh.campus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("static")
public class StaticResourceController {

    // static/test
    @RequestMapping()
    public String test() {
        System.out.println("TestController.test");
        return "testView.html";
    }

    // static/static/test
    @RequestMapping("2")
    public String test2() {
        System.out.println("TestController.2");
        return "testView.html";
    }

    // static/test
    @RequestMapping("3")
    public String test3() {
        System.out.println("TestController.3");
        return "/testView.html";
    }

    // static/test
    @RequestMapping("/4")
    public String test4() {
        System.out.println("TestController.4");
        return "/testView.html";
    }
}

