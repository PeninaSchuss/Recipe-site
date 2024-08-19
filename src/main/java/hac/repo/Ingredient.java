package hac.repo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * This is the Ingredient class
 */
@Entity
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    private String quantity;
    private String ingredientName;


    @ManyToOne
    @JoinColumn(name="recipe_id", nullable=false)
    private Recipe recipe;

    public Ingredient() {}

    public Ingredient(String quantity, String ingredientName) {
        this.quantity = quantity;
        this.ingredientName = ingredientName;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getIngredientName() {
        return ingredientName;
    }
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", quantity='" + quantity + '\'' +
                ", ingredientName='" + ingredientName + '\'' +
                '}';
    }

}

