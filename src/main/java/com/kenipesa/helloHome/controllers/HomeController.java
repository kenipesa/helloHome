package com.kenipesa.helloHome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

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
  
  @GetMapping("/aboutUs")
  public String getAboutUs(Principal p, Model m) {
    m.addAttribute("user", p);
    return "aboutUs";
  }
}
