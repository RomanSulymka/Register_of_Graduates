package edu.university.program.controller;

import edu.university.program.model.User;
import edu.university.program.service.RoleService;
import edu.university.program.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(RoleService roleService, UserService userService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            return "create-user";
        }
        user.setRole(roleService.readById(2));
        User newUser = userService.create(user);
        return "redirect:/users/all";
        //return "redirect:/all/users/" + newUser.getId();
    }

    @GetMapping("/{id}/read")
    public String read(@PathVariable("id") long id, Model model){
        User user = userService.readById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") long id, Model model){
        User user = userService.readById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAll());
        return "update-user";
    }

    @PostMapping("/update")
    public String update(@PathVariable long id, @RequestParam("oldPassword") String oldPassword,
                         @RequestParam("roleId") long roleId, Model model,
                         @Validated @ModelAttribute("user") User user, BindingResult result){
        User oldUser = userService.readById(id);
        if (result.hasErrors()){
            user.setRole(oldUser.getRole());
            model.addAttribute("roles", roleService.getAll());
            return "update-user";
        }

        if (oldUser.getRole().getName().equals("USER")){
            user.setRole(oldUser.getRole());
        }else{
            user.setRole(roleService.readById(roleId));
        }
        userService.update(user);
        return "redirect:/users/all";
    }

    @GetMapping("/{id}/delete")
    private String delete(@PathVariable("id") long id){
        userService.delete(id);
        return "redirect:/users/all";
    }


    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }
}
