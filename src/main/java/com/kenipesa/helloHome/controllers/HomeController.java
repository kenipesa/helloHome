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
  private String [][] team = {
   {"Kevin Couture", "kcouture939/", "kdcouture"},
   {"Nicholas Paro", "nparo/", "paronicholas"},
   {"Peter Lee", "leepj85/", "leepj85"},
   {"Sapana Poudel", "sapana-poudel/", "sapanapoudel"}
  };
  
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
  
  @GetMapping("/aboutUs")
  public String getAboutUs(Principal p, Model m) {
    if (p != null) {
      ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
      m.addAttribute("currentUser", applicationUser);
    }
    m.addAttribute("team", this.team);
    m.addAttribute("user", p);
    return "aboutUs";
  }
}
