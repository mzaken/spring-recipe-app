/**
 * 
 */
package guru.springframework.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

/**
 * @author Maor Zaken Created on Jan 18, 2020
 */
public class DataLoader implements CommandLineRunner {

	private final RecipeRepository RecipeRepository;
	private final UnitOfMeasureRepository uomRepository;

	/**
	 * @param recipeRepository
	 * @param uomRepository
	 */
	public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository) {
		RecipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		loadData();
	}

	private void loadData() {
		Recipe perfectGuacamole = new Recipe();
		perfectGuacamole.setDescription(
				"The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
		perfectGuacamole.setDifficulty(Difficulty.MEDIUM);
		
		Ingredient avocado = new Ingredient();
		avocado.setAmount(new BigDecimal(1));
		avocado.setDescription("Ripe Avocado");
		avocado.setUnitOfMeasure(uomRepository.findByDescription("One").get());
		perfectGuacamole.getIngredients().add(avocado);
		
		Ingredient lemonJuice = new Ingredient();
		lemonJuice.setDescription("Lemon Juice");
		lemonJuice.setAmount(new BigDecimal(1));
		lemonJuice.setUnitOfMeasure(uomRepository.findByDescription("Splash").get());
		perfectGuacamole.getIngredients().add(lemonJuice);
	}
}
