package com.kenipesa.helloHome.controllers;

import com.kenipesa.helloHome.libraries.ResultObj;
import com.kenipesa.helloHome.libraries.ZillowAPILib;
import com.kenipesa.helloHome.models.ApplicationUser;
import com.kenipesa.helloHome.models.ApplicationUserRepository;
import com.kenipesa.helloHome.models.Searches;
import com.kenipesa.helloHome.models.SearchesRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

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
    public ModelAndView addSearch(String city, String state, Principal p, ModelMap m, RedirectAttributes redir) {
        ApplicationUser loggedBuyer = applicationUserRepository.findByUsername(p.getName());
        Searches newSearch = searchesRepository.findByBuyerId(loggedBuyer.getId());
        if (newSearch == null) {
            newSearch = new Searches(city, state, loggedBuyer);
            loggedBuyer.addSearch(newSearch);
        }
        searchesRepository.save(newSearch);
        m.addAttribute("buyer", p);
        m.addAttribute("city", city);
        m.addAttribute("state", state);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:resultsNeig");
        redir.addFlashAttribute("city", city);
        redir.addFlashAttribute("state", state);
        return modelAndView;
    }

    @GetMapping("/user/resultsNeig")
    public String getSearchResult(Principal p, ModelMap m) {
        ApplicationUser buyer = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("currentUser", buyer);
//        m.addAttribute("searchResults", buyer.getSearches());
        m.addAttribute("buyer", p);
        // Zillow API Call
//        JSONObject test = ZillowAPILib.getNeighborhood("wa", "seattle");
//        List<ResultObj> results = ZillowAPILib.getFilteredResults(test);
        List<ResultObj> results = ZillowAPILib.getFilteredResults(ZillowAPILib.getNeighborhood(m.get("state").toString(), m.get("city").toString()));
        // Add to Model
        m.addAttribute("searchResults", results);
        return "resultsNeig";
    }
}
