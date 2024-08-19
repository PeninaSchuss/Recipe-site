package hac.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is the RecipeTypeRepository interface
 */
@Repository
public interface RecipeTypeRepository extends JpaRepository<RecipeType, Long> {
    List<RecipeType> findAllByRecipeCategories(RecipeCategories category);
}
