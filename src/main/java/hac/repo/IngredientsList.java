package hac.repo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * This is the IngredientsList class
 */
@Component
public class IngredientsList {

    @NotEmpty(message = "Input ingredient list cannot be empty.")
    @Valid
    private List<Ingredient> ingredients;
    public IngredientsList(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
      }
    public IngredientsList() {}
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    @Override
    public String toString() {
        return "IngredientsList{" +
                "ingredients=" + ingredients +
                '}';
    }

}
