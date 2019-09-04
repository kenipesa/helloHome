package com.kenipesa.helloHome.controllers;

import com.kenipesa.helloHome.models.ApplicationUser;
import com.kenipesa.helloHome.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
  @Autowired
  ApplicationUserRepository applicationUserRepository;
  
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
  
  @GetMapping()
  public String getError(Principal p, Model m) {
    if (p != null) {
      ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
      m.addAttribute("currentUser", applicationUser);
    }
    m.addAttribute("user", p);
    return "error";
  }
  
  @GetMapping("/aboutUs")
  public String getAboutUs(Principal p, Model m) {
    if (p != null) {
      ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
      m.addAttribute("currentUser", applicationUser);
    }
    m.addAttribute("user", p);
    return "aboutUs";
  }
}
