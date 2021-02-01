package edu.university.program.controller;

import edu.university.program.model.Graduates;
import edu.university.program.model.Work;
import edu.university.program.repository.GraduatesRepository;
import edu.university.program.repository.WorkRepository;
import edu.university.program.service.GraduatesService;
import edu.university.program.service.WorkService;
import edu.university.program.upload.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/graduates")
public class GraduatesController {

    private String filePath = "/home/roman/IdeaProjects/Register_of_Graduates/src/main/resources/static/images/";

    private final GraduatesService graduatesService;
    private final GraduatesRepository graduatesRepository;
    private final WorkService workService;
    private final WorkRepository workRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public GraduatesController(GraduatesService graduatesService, GraduatesRepository graduatesRepository,
                               WorkService workService, WorkRepository workRepository) {
        this.graduatesService = graduatesService;
        this.graduatesRepository = graduatesRepository;
        this.workService = workService;
        this.workRepository = workRepository;
    }

    @GetMapping("/create")
    private String create(Model model){
        model.addAttribute("graduated", new Graduates());
        model.addAttribute("work", new Work());

        log.info("Create graduated");
        return "create-graduated";
    }

    //create new Graduated and upload his picture
    @PostMapping("/create")
    private String create(@Validated @ModelAttribute("graduated") Graduates graduates, BindingResult result,
                          @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        graduates.setPhotos(fileName);

        //saved graduates to Repository
        Graduates savedGraduates = graduatesRepository.save(graduates);

        //save pictures in the specified path under their id
        //закоментувати якщо використовуємо зберігання в підпапках з номером id
        String uploadDir = filePath + savedGraduates.getId();

        //якщо потрібно зберігати в загалиний корінь папки без підпапки №id, то використовуємо цей варіант
        //FileUploadUtil.saveFile(filePath, fileName, multipartFile);

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        if (result.hasErrors()){
            return "create-graduated";
        }

        log.info("Create new graduated: " + graduates.getFirstName() + " " + graduates.getLastName());
        Graduates newGraduates = graduatesService.create(graduates);
        return "redirect:/works/create/graduates/" + newGraduates.getId();
    }

    @GetMapping("/{id}/read")
    private String read(@PathVariable("id") long id, Model model){
        Graduates graduated = graduatesService.readById(id);
        Work works = workService.readById(1);

        model.addAttribute("graduated", graduated);
        model.addAttribute("works", works);
        log.info("Read graduated: " + graduated.getFirstName() + " " + graduated.getLastName() );
        return "/graduated-info";
    }

    @GetMapping("/{id}/update")
    private String update(@PathVariable("id") long id, Model model){
        Graduates graduated = graduatesService.readById(id);
        model.addAttribute("graduated", graduated);

        log.info("Update user");
        return "update-graduated";
    }

    @PostMapping("/{id}/update")
    private String update(@PathVariable long id, Model model, BindingResult result,
                          @Validated @ModelAttribute("graduated") Graduates graduated,
                          @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Graduates oldGraduated = graduatesService.readById(id);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        graduated.setPhotos(fileName);

        //saved graduates to Repository
        Graduates savedGraduates = graduatesRepository.save(graduated);

        //save pictures in the specified path under their id
        //закоментувати якщо використовуємо зберігання в підпапках з номером id
        String uploadDir = filePath + savedGraduates.getId();

        //якщо потрібно зберігати в загалиний корінь папки без підпапки №id, то використовуємо цей варіант
        //FileUploadUtil.saveFile(filePath, fileName, multipartFile);

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        graduatesService.update(graduated);

        log.info("Update user:" + graduated.getLastName());
        return "redirect:/graduates"+ id + "/read";
    }

    @GetMapping("/{id}/delete")
    private String delete(@PathVariable("id") long id){
        graduatesService.delete(id);

        log.info("Deleted user by id: " + id);
        return "redirect:/graduates/all";
    }

    @GetMapping("/all")
    private String getAll(ModelMap model, @PageableDefault(size=10) Pageable pageable){

        model.addAttribute("page", graduatesRepository.findAll(pageable));
        model.addAttribute("graduates", graduatesService.findAll(pageable));

        log.info("Get all");
        return "graduates-list";
    }
}
