package com.example.demo3.controller;

import com.example.demo3.utils.GenerateNumber;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/number")
public class IssueController {

    private static final String ERROR = "Не выбран метод";

    String result = null;
    GenerateNumber generator = new GenerateNumber();


    @GetMapping
    public Response showStatus() {
        return new Response(ERROR);
    }

    //произвольная генерация номера
    @RequestMapping(method = RequestMethod.GET, value = "/random")
    @ResponseBody
    public Response showStatus2() throws IOException {
        result = generator.generateNumber(1);
        return new Response<>(true, result);
    }

    //последовательная генерация номера
    @RequestMapping(method = RequestMethod.GET, value = "/next")
    @ResponseBody
    public Response showStatus3() throws IOException {
        result = generator.generateNumber(2);
        return new Response<>(true, result);
    }
}
