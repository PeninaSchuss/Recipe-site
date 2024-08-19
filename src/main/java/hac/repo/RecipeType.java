package hac.repo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 * This is the RecipeType class
 */
@Entity
public class RecipeType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    private String recipeType;
    @ManyToOne
    @JoinColumn(name="recipeCategories_id", nullable=false)
    private RecipeCategories recipeCategories;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public RecipeType() {}
    public RecipeType(String recipeType, RecipeCategories recipeCategories) {
        this.recipeType = recipeType;
        this.recipeCategories = recipeCategories;
    }
    public String getRecipeType() {
        return recipeType;
    }
    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }
    public RecipeCategories getRecipeCategories() {
        return recipeCategories;
    }
    public void setRecipeCategories(RecipeCategories recipeCategories) {
        this.recipeCategories = recipeCategories;
    }

    @Override
    public String toString() {
        return "RecipeType{" +
                "id=" + id +
                ", recipeType='" + recipeType + '\'' +
                ", recipeCategory=" + recipeCategories +
                '}';
    }
}
