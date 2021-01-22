package edu.university.program.controller;

import edu.university.program.model.Graduates;
import edu.university.program.service.GraduatesService;
import edu.university.program.service.RoleService;
import edu.university.program.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/graduates")
public class GraduatesController {

    private final GraduatesService graduatesService;
    private final UserService userService;

    public GraduatesController(GraduatesService graduatesService, UserService userService) {
        this.graduatesService = graduatesService;
        this.userService = userService;
    }

    @GetMapping("/create")
    private String create(Model model){
        model.addAttribute("graduated", new Graduates());
        return "create-graduated";
    }

    @PostMapping("/create")
    private String create(@Validated @ModelAttribute("graduated") Graduates graduates, BindingResult result){
        if (result.hasErrors()){
            return "create-graduated";
        }
        Graduates newGraduates = graduatesService.create(graduates);
        return "redirect:/graduates/all";
    }

    @GetMapping("/{id}/read")
    private String read(@PathVariable("id") long id, Model model){
        Graduates graduated = graduatesService.readById(id);
        model.addAttribute("graduated", graduated);
        return "/graduated-info";
    }

    //TODO: create 'update', get and post mapping

    @GetMapping("/{id}/delete")
    private String delete(@PathVariable("id") long id){
        graduatesService.delete(id);
        return "redirect:/graduates/all";
    }

    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("graduates", graduatesService.getAll());
        return "graduates-list";
    }
}
