package hac.controllers;

import hac.repo.RecipeCategotiesRepository;
import hac.repo.RecipeTypeRepository;
import hac.services.ControllersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** this is a test controller, delete/replace it when you start working on your project */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ControllersService controllersService;


    /**
     * This is contact page
     * @param model Model
     * @return contact.html
     */
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/contact";
    }
}
