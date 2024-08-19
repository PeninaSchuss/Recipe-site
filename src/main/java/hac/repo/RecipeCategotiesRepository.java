package hac.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the RecipeCategotiesRepository interface
 */
@Repository
public interface RecipeCategotiesRepository extends JpaRepository<RecipeCategories, Long> {
    RecipeCategories findRecipeCategoriesByCategoryName(String categoryName);
}
