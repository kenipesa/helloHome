package com.kenipesa.helloHome.controllers;

import com.kenipesa.helloHome.models.ApplicationUser;
import com.kenipesa.helloHome.models.ApplicationUserRepository;
import com.kenipesa.helloHome.models.Searches;
import com.kenipesa.helloHome.models.SearchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String makeSearches(Principal p, Model m) {
        ApplicationUser loggedBuyer = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("currentUser" , loggedBuyer);
        m.addAttribute("user", p);
        return "searches";
    }

    @PostMapping("/user/results")
    public RedirectView addSearch(String city, String state, Principal p, Model m ) {
        ApplicationUser loggedBuyer = applicationUserRepository.findByUsername(p.getName());
        Searches newSearch = new Searches(city, state, loggedBuyer);
        searchesRepository.save(newSearch);
        m.addAttribute("buyer", p);
        m.addAttribute("city", city);
        m.addAttribute("state", state);
        return new RedirectView("/user/results");
    }

    @GetMapping("/user/results")
    public String getSearchResult(Principal p, Model m, @ModelAttribute("city") String city,
                                  @ModelAttribute("state") String state) {
        ApplicationUser buyer = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("currentUser", buyer);
        m.addAttribute("searchResults", buyer.getSearches());
        m.addAttribute("buyer", p);
        System.out.println(city);
        System.out.println(state);
        //TODO: place Zilllow API call later
        //ZillowAPILib.getNeighborhood(state, city);
        //TODO: add zillow API result to the model
        return "results";
    }
}
