package com.kenipesa.helloHome.controllers;

import com.kenipesa.helloHome.models.ApplicationUser;
import com.kenipesa.helloHome.models.SearchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class SearchController {
    @Autowired
    ApplicationUser applicationUser;

    @Autowired
    SearchesRepository searchesRepository;

    @GetMapping("/user/searches")
    public String getAllSearches() {
        return "searches";
    }

    @PostMapping("/user/searches")
    public RedirectView makeSearch(String city, String state, Principal p, Model model ) {

    }



}
