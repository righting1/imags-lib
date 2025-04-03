package com.rightings.backed.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RequestMapping("/test")
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello1")
    public String hello1() {
        fun();
        return "hello";
    }

    void fun(){
        //DataModel model = new FileDataModel(new File("path/to/your/data.csv"));


    }

}
