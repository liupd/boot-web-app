package com.wa.net.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Administrator on 16-3-31.
 **/
@Controller
public class FTlController {

    @RequestMapping("/index")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("name", "spring-boot");
        modelMap.addAttribute("user","liupd");
        return "index";
    }

    @RequestMapping("/userlist")
    public String userlist(ModelMap modelMap) {
        modelMap.addAttribute("name", "spring-boot");
        modelMap.addAttribute("user","liupd");
        return "userlist";
    }


    @RequestMapping("/dataToMq")
    public String dataToMq(ModelMap modelMap) {
        modelMap.addAttribute("name", "spring-boot");
        modelMap.addAttribute("user","liupd");
        return "dataToMq";
    }

    @RequestMapping("/uploadfile")
    public String uploadfile(ModelMap modelMap) {
        modelMap.addAttribute("name", "spring-boot");
        modelMap.addAttribute("user","liupd");
        return "uploadfile";
    }
}
