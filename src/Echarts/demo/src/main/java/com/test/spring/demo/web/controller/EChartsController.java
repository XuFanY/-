package com.test.spring.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EChartsController {

    @RequestMapping(value = "/uradar",method = RequestMethod.GET)
    public String UserRadar() {
        return "UserRadar";
    }

    @RequestMapping(value = "/cradar",method = RequestMethod.GET)
    public String CategoryRadar() {
        return "CategoryRadar";
    }

    @RequestMapping(value = "/cparallel",method = RequestMethod.GET)
    public String CategoryParallel() {
        return "CategoryParallel";
    }

}