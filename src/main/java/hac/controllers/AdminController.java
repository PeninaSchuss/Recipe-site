package hac.controllers;

import hac.repo.*;

import hac.services.ControllersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** this is a test controller, delete/replace it when you start working on your project */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ControllersService controllersService;


    /**
     * This method is used to get the recipe types based on the category name
     * @param categoryName - categoryName
     * @return recipeTypesNames
     */

    @GetMapping("/get-dish-types")
    public @ResponseBody
    List<String> getRecipeTypes(@RequestParam("categoryName") String categoryName) {

        RecipeCategories category = controllersService.getRecipeCategory(categoryName).orElseThrow(() -> new RuntimeException("Category not found"));
        List<RecipeType> recipeTypes = controllersService.getTypesOfCategory(category).orElseThrow(() -> new RuntimeException("Recipe types not found"));
        List<String> recipeTypesNames = new ArrayList<>();
        for (RecipeType recipeType : recipeTypes) {
            recipeTypesNames.add(recipeType.getRecipeType());
        }
        return recipeTypesNames;
    }

    /**
     * This method is get the add-recipe page
     * @param model - model
     * @param recipe - recipe
     * @return add-recipe
     */
    @GetMapping("/add-recipe")
    public String addRecipe(Model model, Recipe recipe) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/add-recipe";
    }

    /**
     * This method is handle the refresh page
     * @param model - model
     * @param recipe - recipe
     * @return add-recipe
     */
    @GetMapping("/add-recipe-to-db")
    public String addRecipeToDB(Model model, Recipe recipe) {
        return "redirect:/index/Please add recipe from the form";
    }

    /**
     * This method is used to add the recipe to the database
     * @param recipe - recipe
     * @param recipeBindingResult - recipeBindingResult
     * @param model - model
     * @param id - id
     * @return add-recipe
     */
    @PostMapping("/add-recipe-to-db")
    public String addRecipeToDB(@Valid Recipe recipe, BindingResult recipeBindingResult, Model model) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        if (recipeBindingResult.hasErrors()) {
            return "sitePages/add-recipe";
        }
        recipe.setCreationDate(LocalDate.now());
        Recipe savedRecipe;
        try{
            savedRecipe = controllersService.saveRecipe(recipe);
        }
        catch (Exception e){
            return "redirect:/index/Recipe was not added";
        }
        return "redirect:/admin/add-ingredients/" + savedRecipe.getId();
    }

    /**
     * This method is used to get the add-ingredients page
     * @param model - model
     * @param ingredients - ingredients
     * @param id - id
     * @return add-ingredients
     */
    @GetMapping("/add-ingredients/{id}")
    public String addIngredients(Model model, IngredientsList ingredients, @PathVariable("id") long id) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/add-ingredients";
    }

    /**
     * This method is used to add the ingredients to the database
     * @param ingredients - ingredients
     * @param ingredientsBindingResult - ingredientsBindingResult
     * @param model - model
     * @param id - id
     * @return redirect:/index/Recipe was added successfully
     */
    @PostMapping("/add-ingredients")
    public String addIngredientsToDB( IngredientsList ingredients, Model model, @RequestParam("id") long id) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        Recipe recipe = controllersService.getRecipe(id).orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
        try {
            controllersService.saveIngredients(ingredients, recipe);
        }
        catch (Exception e){
            return "redirect:/index/Recipe was not added";
        }
        return "redirect:/index/Recipe was added successfully";
    }

    /**
     * This method is used to get the delete-category page
     * @param model - model
     * @return delete-category
     */
    @GetMapping("/deleteFromBar")
    public String handlegetDelete(Model model) {
        RecipeCategories recipeCategories = new RecipeCategories();
        model.addAttribute("recipeCategories", recipeCategories);
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/delete-category";
    }

    /**
     * This method is used to handle the delete-category page
     * @param model - model
     * @return delete-category
     */
    @GetMapping("/DeleteCategory")
    public String handlegetDeleteCategory(Model model) {
        return "redirect:/index/Delete category from the form";
    }

    /**
     * This method is used to delete the category from the database
     * @param recipeCategories - recipeCategories
     * @param recipeBindingResult - recipeBindingResult
     * @param model - model
     * @return delete-category
     */
    @PostMapping("/DeleteCategory")
    public String handleDeleteCategoryFromDB(@Valid RecipeCategories recipeCategories, BindingResult recipeBindingResult, Model model) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        if (recipeBindingResult.hasErrors()) {
            return "sitePages/delete-category";
        }
        if(controllersService.getRecipeCategory(recipeCategories.getCategoryName()).isEmpty()){
            return "redirect:/index/the category is not in the database";
        }
        controllersService.deleteRecipeCategory(controllersService.getRecipeCategory(recipeCategories.getCategoryName()).get());
        return "redirect:/index/Category was deleted successfully";
    }

    /**
     * This method is used to get the add-category page
     * @param model - model
     * @return add-category
     */
    @GetMapping("/addToBar")
    public String showAddToBarForm(Model model) {
        RecipeCategories recipeCategories = new RecipeCategories();
        model.addAttribute("recipeCategories", recipeCategories);
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/add-category";
    }

    /**
     * This method handle the refresh addToBarToDB page
     * @param model - model
     * @return add-category
     */
    @GetMapping("/addToBarToDB")
    public String showAddToBarToDBForm(Model model) {
        return "redirect:/index/Please add category from the form";
    }


    /**
     * This method is used to add the category to the database
     * @param recipeCategories - recipeCategories
     * @param recipeBindingResult - recipeBindingResult
     * @param model - model
     * @return add-category
     */
    @PostMapping("/addToBarToDB")
    public String addBarToDB(@Valid RecipeCategories recipeCategory, BindingResult recipeBindingResult, Model model) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        if (recipeBindingResult.hasErrors()) {
            return "sitePages/add-category";
        }
        RecipeCategories savedCategories;
        try{
            savedCategories = controllersService.saveRecipeCategory(recipeCategory);
        }
        catch (Exception e){
            return "redirect:/index/Category was not added";
        }
        return "redirect:/admin/addType/" + savedCategories.getId();
    }

    /**
     * This method is used to get the add-type page
     * @param model - model
     * @param recipeType - recipeType
     * @param id - id
     * @return add-type
     */
    @GetMapping("/addType/{id}")
    public String addType(Model model, RecipeType recipeType, @PathVariable("id") long id) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/add-type";
    }

    /**
     * This method handle the refresh addType page
     * @param model - model
     * @param recipeType - recipeType
     * @return add-type
     */
    @GetMapping("/addType")
    public String addType(Model model, RecipeType recipeType) {
        return "redirect:/index/Please add type from the form";
    }

    /**
     * This method is used to add the type to the database
     * @param recipeType - recipeType
     * @param model - model
     * @param id - id
     * @return add-type
     */
    @PostMapping("/addType")
    public String addTypeToDB( @RequestParam("recipeType") String recipeType, Model model, @RequestParam("id") long id) {

        try{
            controllersService.saveType(recipeType, id);
        }
        catch (Exception e){
            return "redirect:/index/Type was not added";
        }
        return "redirect:/index/Category was added successfully to the bar";
    }

    /**
     * This method handle the refresh deleteRecipe page
     * @param model - model
     * @return delete-recipe
     */
    @GetMapping("/DeleteRecipe")
    public String showDeleteRecipeForm(Model model) {
        return "redirect:/index/Please delete recipe from the form";
    }

    /**
     * This method delete the recipe
     * @param model - model
     * @param id - id
     * @return delete-recipe
     */
    @PostMapping("/DeleteRecipe")
    public String deleteRecipe(Model model, @RequestParam("id") Long id) {
        try{
            controllersService.deleteRecipe(id);
        }
        catch (Exception e){
            return "redirect:/index/Recipe was not deleted";
        }
        return "redirect:/index/Recipe was deleted successfully";
    }
}
