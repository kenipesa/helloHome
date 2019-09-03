package com.kenipesa.helloHome.controllers;

import com.kenipesa.helloHome.models.ApplicationUser;
import com.kenipesa.helloHome.models.ApplicationUserRepository;
import com.kenipesa.helloHome.models.Searches;
import com.kenipesa.helloHome.models.SearchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class SearchController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    SearchesRepository searchesRepository;

    @GetMapping("/user/searches")
    public String makeSearches() {
        return "searches";
    }

    @PostMapping("/user/results/{id}")
    public RedirectView addSearch(@PathVariable long id,  String city, String state, Principal p, Model model ) {
        ApplicationUser loggedBuyer = applicationUserRepository.findByUsername(p.getName());
        Searches newSearch = new Searches(city, state, loggedBuyer);
        searchesRepository.save(newSearch);
        model.addAttribute("oneBuyer", applicationUserRepository.findById(id).get());
        model.addAttribute("buyer", p);
        return new RedirectView("/user/results");
    }

    @GetMapping("/user/results")
    public String getSearchResult(Principal p, Model model) {
        ApplicationUser buyer = applicationUserRepository.findByUsername(p.getName());
        model.addAttribute("searchResults", buyer.getSearches());
        model.addAttribute("buyer", p);
        return "results";
    }

}
