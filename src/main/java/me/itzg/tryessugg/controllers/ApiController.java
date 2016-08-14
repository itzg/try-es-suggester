package me.itzg.tryessugg.controllers;

import me.itzg.tryessugg.model.InterestsBundle;
import me.itzg.tryessugg.services.InterestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Geoff Bourne
 */
@RequestMapping("/api")
@RestController
public class ApiController {

    @Autowired
    private InterestsService interestsService;

    @RequestMapping(value = "interests", method = RequestMethod.POST)
    public void saveInterests(@RequestBody InterestsBundle bundle) {
        interestsService.save(bundle.getInterests());
    }

    @RequestMapping(value = "interests/_suggest", method = RequestMethod.GET)
    public List<String> suggestInterests(@RequestParam("q") String given) {
        return interestsService.suggestInterests(given);
    }
}
