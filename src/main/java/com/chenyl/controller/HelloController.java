package com.chenyl.controller;


import com.chenyl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cookily on 2017/7/28.
 */
@RestController
public class HelloController {

    /*@Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;*/

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String say() {
        return girlProperties.getCupSize();
    }

    //show/123
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id1) {
        return "id" + id1;
    }

    //find?id=123
    //@RequestMapping(value = "/find", method = RequestMethod.GET)
    @GetMapping(value = "/find")
    public String find(@RequestParam(value = "id",required = false,defaultValue = "0") Integer id) {
        return "id" + id;
    }

}
