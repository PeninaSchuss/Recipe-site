package hac.services;

import hac.controllers.ControllerUtils;
import hac.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ControllersService {

    final int AMOUNT_OF_RECIPES = 3;

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;
    @Autowired
    private RecipeCategotiesRepository recipeCategoriesRepository;
    @Autowired
    private RecipeTypeRepository recipeTypeRepository;


    public Optional<RecipeCategories> getRecipeCategory(String categoryName) {
        return Optional.ofNullable(recipeCategoriesRepository.findRecipeCategoriesByCategoryName(categoryName));
    }

    public Optional<RecipeCategories> getCategoryById(long id) {
        return recipeCategoriesRepository.findById(id);
    }


    public Optional<List<RecipeType>> getTypesOfCategory(RecipeCategories category) {
        return Optional.ofNullable(recipeTypeRepository.findAllByRecipeCategories(category));
    }

    public List<RecipeCategories> getAllRecipeCategories() {
        return recipeCategoriesRepository.findAll();
    }

    public List<RecipeType> getAllRecipeTypes() {
        return recipeTypeRepository.findAll();
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    public Optional<Recipe> getRecipe(long id) {
        return recipeRepository.findById(id);
    }


    @Transactional
    public void saveIngredients(IngredientsList ingredients, Recipe recipe) {
        for (Ingredient ingredient : ingredients.getIngredients()) {
            ingredient.setRecipe(recipe);
            ingredientsRepository.save(ingredient);
        }
    }

    public void deleteRecipeCategory(RecipeCategories category) {
        recipeCategoriesRepository.delete(category);
    }

    public void deleteRecipe(long id) {
        recipeRepository.deleteById(id);
    }
    public void saveType(String recipeType, long id){
        RecipeCategories category = getCategoryById(id).orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
        RecipeType type = new RecipeType();
        type.setRecipeType(recipeType);
        type.setRecipeCategories(category);
        recipeTypeRepository.save(type);
    }

    public RecipeCategories saveRecipeCategory(RecipeCategories recipeCategory) {
        RecipeCategories savedCategories = recipeCategoriesRepository.findRecipeCategoriesByCategoryName(recipeCategory.getCategoryName());
        if (savedCategories==null) {
            savedCategories = recipeCategoriesRepository.save(recipeCategory);
        }
        return savedCategories;
    }

    public long getAmountOfCategories() {
        return recipeCategoriesRepository.count();
    }

    public List<Recipe> getThreeRandomRecipes() {
        return recipeRepository.findRandomRecipes(AMOUNT_OF_RECIPES);
    }

    public void insertCategories()
    {
        ControllerUtils.insertCategories(recipeCategoriesRepository);
    }
    public List<Recipe> searchRecipes(String prepTime, String dishType, String recipeName, String ingredient, String difficultyLevel) {
        return recipeRepository.searchRecipes(prepTime, dishType, recipeName, ingredient, difficultyLevel);
    }
    public List<Recipe> getRecipeByDishType(String dishType) {
        return recipeRepository.findByDishType(dishType);
    }
}
