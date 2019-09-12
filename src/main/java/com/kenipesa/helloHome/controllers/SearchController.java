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
        Searches newSearch = new Searches(city.toLowerCase(), state.toLowerCase(), loggedBuyer);
        
        if (searchesRepository.findByCityAndStateAndBuyerId(newSearch.getCity(),
         newSearch.getState(), newSearch.getBuyer().getId()) == null) {
            loggedBuyer.addSearch(newSearch);
            searchesRepository.save(newSearch);
        }
    
        m.addAttribute("buyer", p);
        m.addAttribute("city", city);
        m.addAttribute("state", state);
        // interesting--you're actively avoiding following good REST convention here!
        // It's best if a get request to a particular route always returns the same
        // content, which is why we usually put the ID in the route instead of
        // storing it on the server side. The fact that right now the
        // /user/results route shows me different things depending on which link
        // I clicked means that, for example, I can't save one search in my bookmarks.
        // If I try to, I get an error when I visit that page.
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:results");
        redir.addFlashAttribute("city", city);
        redir.addFlashAttribute("state", state);
        return modelAndView;
    }

    // Following from above, I'd prefer if this route were more like
    // GET /searches/{id} and then loaded that search's results.
    @GetMapping("/user/results")
    public String getSearchResult(Principal p, ModelMap m) {
        ApplicationUser buyer = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("currentUser", buyer);
        m.addAttribute("buyer", p);
        List<ResultObj> results = ZillowAPILib.getFilteredResults(ZillowAPILib.getNeighborhood(m.get("state").toString(), m.get("city").toString()));
        m.addAttribute("searchResults", results);
        m.addAttribute("size", results.size());
        return "results";
    }
}
