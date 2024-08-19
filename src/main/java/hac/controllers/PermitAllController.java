package hac.controllers;
import hac.repo.*;
import hac.services.ControllersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class PermitAllController {

    @Autowired
    private ControllersService controllersService;

    /**
     * This is the first page
     * @param model Model
     * @return index.html
     */
    @GetMapping("/")
    public String firstPage(Model model) {
        List<Recipe> recipes = controllersService.getThreeRandomRecipes();
        model.addAttribute("recipes", recipes);
        if (controllersService.getAmountOfCategories() > 0) {
            model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
            model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
            return "sitePages/index";
        }
        controllersService.insertCategories();
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/index";
    }

    /**
     * Get the index page - home page
     * @param model Model
     * @return index.html
     */
    @GetMapping("/index" )
    public String index(Model model) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        List<Recipe> recipes = controllersService.getThreeRandomRecipes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/index";
    }

    /**
     * The index page with a message
     * @param model Model
     * @param message String
     * @return index.html
     */
    @GetMapping("/index/{message}")
    public String indexMessage(Model model, @PathVariable("message") String message){
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        List<Recipe> recipes = controllersService.getThreeRandomRecipes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("message", message);
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/index";
    }

    /**
     * Get the 403 page that handle the login error
     * @param model Model
     * @return 403.html
     */
    @GetMapping("/403")
    public String error(Model model) {
        return "errors/403";
    }

    /**
     * Search recipes by the given parameters
     * @param prepTime String
     * @param dishType String
     * @param recipeName String
     * @param ingredient String
     * @param difficultyLevel String
     * @param model Model
     * @return search-results.html
     */
    @PostMapping("/search")
    public String searchRecipes(@RequestParam(value = "prepTime") String prepTime,
                                @RequestParam("dishType") String dishType,
                                @RequestParam("recipeName") String recipeName,
                                @RequestParam("ingredient") String ingredient,
                                @RequestParam("difficultyLevel") String difficultyLevel,
                                Model model) {
        List<Recipe> searchResults = controllersService.searchRecipes(prepTime, dishType, recipeName, ingredient, difficultyLevel);
        model.addAttribute("recipeNames", searchResults);
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("type", "Search Results");
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/search-results";
    }

    /**
     * Get the recipe List page by the wanted dish type
     * @param dishType String
     * @param model Model
     * @return search-results.html
     */
    @GetMapping("/recipe/{dishType}")
    public String recipe(@PathVariable("dishType") String dishType, Model model) {
        List<Recipe> recipeNames = controllersService.getRecipeByDishType(dishType);
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        model.addAttribute("recipeNames", recipeNames);
        model.addAttribute("type", dishType);
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/search-results";
    }

    /**
     * See the recipe by the given id
     * @param model Model
     * @param id Long
     * @return see-recipe.html
     */
    @PostMapping("/seeRecipe")
    public String seeRecipe(Model model, @RequestParam("id") Long id) {
        model.addAttribute("recipeCategory", controllersService.getAllRecipeCategories());
        Recipe recipe = controllersService.getRecipe(id).orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
        model.addAttribute("recipe", recipe);
        model.addAttribute("dishTypes", controllersService.getAllRecipeTypes());
        return "sitePages/see-recipe";
    }

    /**
     * Get the error page
     * @return error.html
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}

