package edu.university.program.controller;

import edu.university.program.dto.WorkDto;
import edu.university.program.model.Graduates;
import edu.university.program.model.Work;
import edu.university.program.model.WorkStatus;
import edu.university.program.service.GraduatesService;
import edu.university.program.service.WorkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/works")
public class WorkController {

    private final WorkService workService;
    private final GraduatesService graduatesService;


    public WorkController(WorkService workService, GraduatesService graduatesService) {
        this.workService = workService;
        this.graduatesService = graduatesService;
    }

    @GetMapping("/create/graduates/{graduated_id}")
    public String create(@PathVariable("graduated_id") long graduated_id, Model model) {
        model.addAttribute("work", new WorkDto());
        model.addAttribute("graduated", graduatesService.readById(graduated_id));
        return "add-graduated-work";
    }

    @PostMapping("/create/graduates/{graduated_id}")
    public String create(@Validated @ModelAttribute("work") Work work, BindingResult result,
                         @PathVariable("graduated_id") long graduatedId){
        if(result.hasErrors()){
            return "add-graduated-work";
        }

        work.setGraduated(graduatesService.readById(graduatedId));
        work.setWorkStatus(WorkStatus.WORKING);
        workService.create(work);

        return "redirect:/graduates/all";
    }

    @GetMapping("/{id}/graduated")
    public String read(@PathVariable long id, Model model) {
        Graduates graduated = graduatesService.readById(id);
        List<Work> works = workService.getByWorkId(id);

        model.addAttribute("graduated", graduated);
        model.addAttribute("works", works);
        return "work-info";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable long id, Model model) {
        Work work = workService.readById(id);
        model.addAttribute("work", work);
        model.addAttribute("workStatus", WorkStatus.values());
        return "update-work";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id, @Validated @ModelAttribute("work") Work work,
                         BindingResult result) {
        Work oldWork = workService.readById(id);
        if (result.hasErrors()) {
            return "update-work";
        }

        workService.update(work);
        return "redirect:/works/" + id + "/graduated";
    }

    @GetMapping("/{id}/delete/{graduated_id}")
    public String delete(@PathVariable("id") long id, @PathVariable("graduated_id") long graduatedId) {
        workService.delete(id);
        return "redirect:/works/" + graduatedId + "/graduated";
    }
}
