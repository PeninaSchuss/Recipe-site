package hac.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the IngredientsRepository interface
 */
@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {
    void deleteByRecipeId(Long id);
}
