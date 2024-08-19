package hac.repo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * This is the RecipeRepository interface
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByRecipeName(String recipeName);
    @Query("SELECT r FROM Recipe r JOIN r.ingredients i WHERE i.ingredientName LIKE %:ingredient% AND r.dishType = :dishType")
    List<Recipe> findByIngredientAndDishType(@Param("ingredient") String ingredient, @Param("dishType") String dishType);
    List<Recipe> findByDishType(@Param("dishType") String dishType);
    @Query("SELECT r FROM Recipe r JOIN r.ingredients i WHERE " +
            "(COALESCE(:ingredient, '') = '' OR i.ingredientName LIKE %:ingredient%) AND " +
            "(COALESCE(:dishType, '') = '' OR r.dishType = :dishType) AND " +
            "(COALESCE(:recipeName, '') = '' OR r.recipeName LIKE %:recipeName%) AND " +
            "(COALESCE(:prepTime, '') = '' OR r.prepTime <= :prepTime) AND " +
            "(COALESCE(:difficultyLevel, '') = '' OR r.difficultyLevel LIKE %:difficultyLevel%)")
    List<Recipe> searchRecipes(@Param("prepTime") String prepTime,
                               @Param("dishType") String dishType,
                               @Param("recipeName") String recipeName,
                               @Param("ingredient") String ingredient,
                               @Param("difficultyLevel") String difficultyLevel);

    @Query(value = "SELECT * FROM Recipe ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Recipe> findRandomRecipes(@Param("limit") int limit);
}