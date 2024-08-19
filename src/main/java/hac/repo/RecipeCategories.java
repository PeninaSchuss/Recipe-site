package hac.repo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.List;

/**
 * This is the RecipeCategories class
 */
@Entity
public class RecipeCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Pattern(regexp = "^(?!\\s*$).+", message = "categoryName must not be empty or only contain whitespace")
    private String categoryName;

    @OneToMany(mappedBy = "recipeCategories", cascade = CascadeType.ALL)
//    @JoinColumn(name = "recipe_categories_id") // Add this line
    private List<RecipeType> recipeTypes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RecipeCategories() {
    }
    public RecipeCategories(String categoryName, List<RecipeType> recipeTypes) {
        this.categoryName = categoryName;
        this.recipeTypes = recipeTypes;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public List<RecipeType> getRecipeTypes() {
        return recipeTypes;
    }
    public void setRecipeTypes(List<RecipeType> recipeTypes) {
        this.recipeTypes = recipeTypes;
    }
    @Override
    public String toString() {
        return "RecipeCategories{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", recipeType=" + recipeTypes +
                '}';
    }
}