package com.kenipesa.helloHome.controllers;

import com.kenipesa.helloHome.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {
  @Autowired
  PasswordEncoder encoder;
  
  @Autowired
  ApplicationUserRepository applicationUserRepository;
  
  @Autowired
  ExpensesRepository expensesRepository;
  
  @PostMapping("/register")
  public RedirectView createUser(String username, String password, String firstName, String lastName) {
    ApplicationUser applicationUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName);
    applicationUserRepository.save(applicationUser);
    Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser, null, new ArrayList<>());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new RedirectView("/user/addExpenses");
  }
  
  @GetMapping("/user/addExpenses")
  public String getAddExpenses(Principal p, Model m) {
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
    m.addAttribute("currentUser", applicationUser);
    return "addExpenses";
  }
  
  @PostMapping("/user/addExpenses")
  public RedirectView createAddExpenses(int annualIncome, int creditPayment, int entertainment, int utilities,
                                        int insurance, int vehicle, int misc, Principal p) {
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
    Expenses expenses = expensesRepository.findByBuyerId(applicationUser.getId());
    if (expenses == null) {
      expenses = new Expenses(annualIncome, creditPayment, entertainment, utilities, insurance, vehicle, misc,
       applicationUser);
      applicationUser.setExpense(expenses);
    } else {
      expenses.setAnnualIncome(annualIncome);
      expenses.setCreditPayment(creditPayment);
      expenses.setEntertainment(entertainment);
      expenses.setUtilities(utilities);
      expenses.setInsurance(insurance);
      expenses.setVehicle(vehicle);
      expenses.setMisc(misc);
    }
    expensesRepository.save(expenses);
    return new RedirectView("/user/profile");
  }
  
  @GetMapping("/user/profile")
  public String getUserProfile(Principal p, Model m) {
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
    m.addAttribute("currentUser", applicationUser);
    return "profile";
  }
}
