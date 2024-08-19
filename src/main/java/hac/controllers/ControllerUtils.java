package hac.controllers;

import hac.repo.RecipeCategories;
import hac.repo.RecipeCategotiesRepository;
import hac.repo.RecipeRepository;
import hac.repo.RecipeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ControllerUtils {

    /**
     * Insert the recipe categories
     * @param recipeCategoriesRepository RecipeCategotiesRepository
     */
    public static void insertCategories(RecipeCategotiesRepository recipeCategoriesRepository) {

        List<RecipeCategories> categories = new ArrayList<>();

        RecipeCategories recipesCategory = new RecipeCategories();
        recipesCategory.setCategoryName("RECIPES");

        RecipeType breakfastType = new RecipeType();
        breakfastType.setRecipeType("Breakfast");
        breakfastType.setRecipeCategories(recipesCategory);

        RecipeType soupsType = new RecipeType();
        soupsType.setRecipeType("Soups");
        soupsType.setRecipeCategories(recipesCategory);

        RecipeType saladsType = new RecipeType();
        saladsType.setRecipeType("Salads");
        saladsType.setRecipeCategories(recipesCategory);

        RecipeType mainDishesType = new RecipeType();
        mainDishesType.setRecipeType("Main Dishes");
        mainDishesType.setRecipeCategories(recipesCategory);

        RecipeType sideDishesType = new RecipeType();
        sideDishesType.setRecipeType("Side Dishes");
        sideDishesType.setRecipeCategories(recipesCategory);

        RecipeType dessertsType = new RecipeType();
        dessertsType.setRecipeType("Desserts");
        dessertsType.setRecipeCategories(recipesCategory);

        RecipeType drinksType = new RecipeType();
        drinksType.setRecipeType("Drinks");
        drinksType.setRecipeCategories(recipesCategory);

        recipesCategory.setRecipeTypes(Arrays.asList(breakfastType, soupsType, saladsType, mainDishesType, sideDishesType, dessertsType, drinksType));

        categories.add(recipesCategory);

        RecipeCategories specialDietsCategory = new RecipeCategories();
        specialDietsCategory.setCategoryName("SPECIAL DIETS");

        RecipeType veganType = new RecipeType();
        veganType.setRecipeType("Vegan");
        veganType.setRecipeCategories(specialDietsCategory);

        RecipeType glutenFreeType = new RecipeType();
        glutenFreeType.setRecipeType("Gluten Free");
        glutenFreeType.setRecipeCategories(specialDietsCategory);

        RecipeType sugarFreeType = new RecipeType();
        sugarFreeType.setRecipeType("Sugar Free");
        sugarFreeType.setRecipeCategories(specialDietsCategory);

        RecipeType lowCalorieType = new RecipeType();
        lowCalorieType.setRecipeType("Low Calorie");
        lowCalorieType.setRecipeCategories(specialDietsCategory);

        specialDietsCategory.setRecipeTypes(Arrays.asList(veganType, glutenFreeType, sugarFreeType, lowCalorieType));

        categories.add(specialDietsCategory);

        recipeCategoriesRepository.saveAll(categories);
    }
}
