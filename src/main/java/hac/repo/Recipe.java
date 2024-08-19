package hac.repo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * This is the Recipe class
 */
@Entity
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Pattern(regexp = "^(?!\\s*$).+", message = "recipeName must not be empty or only contain whitespace")
    private String recipeName;
    @NotEmpty(message = "Recipe Type is mandatory")
    private String recipeType;

    @NotEmpty(message = "Prep Time is mandatory")
    private String prepTime;

    @Pattern(regexp = "^(?!\\s*$).+", message = "prepInstructions must not be empty or only contain whitespace")
    private String prepInstructions;

    @NotEmpty(message = "Difficulty Level  is mandatory")
    private String difficultyLevel;

    @NotEmpty(message = "Dish Type is mandatory")
    private String dishType;

    @DateTimeFormat(pattern = "dd MMMM yyyy")
    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy="recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    public Recipe() {}

    public Recipe(String recipeName,String recipeType, String prepTime, String prepInstructions, String difficultyLevel, String dishType) {
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.prepTime = prepTime;
        this.prepInstructions = prepInstructions;
        this.difficultyLevel = difficultyLevel;
        this.dishType = dishType;
        this.creationDate = LocalDate.now();
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getPrepTime() {
        return prepTime;
    }
    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getRecipeType() {
        return recipeType;
    }
    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getPrepInstructions() {
        return prepInstructions;
    }
    public void setPrepInstructions(String prepInstructions) {
        this.prepInstructions = prepInstructions;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public String getDishType() {
        return dishType;
    }
    public void setDishType(String dishType) {
        this.dishType = dishType;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString(){
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", recipeType='" + recipeType + '\'' +
                ", prepTime='" + prepTime + '\'' +
                ", prepInstructions='" + prepInstructions + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", dishType='" + dishType + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}

