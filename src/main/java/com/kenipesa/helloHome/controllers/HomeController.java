package com.kenipesa.helloHome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String getHome() {
    return "index";
  }
  
  @GetMapping("/login")
  public String getLogin() {
    return "login";
  }
  
  @GetMapping("/register")
  public String getRegistration() {
    return "register";
  }
}
