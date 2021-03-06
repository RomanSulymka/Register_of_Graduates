package edu.university.program.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    Logger log = LoggerFactory.getLogger(HomeController.class);

    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/", "home"})
    public String home() {

        log.info("Go to home controller");
        return "home";
    }
}
