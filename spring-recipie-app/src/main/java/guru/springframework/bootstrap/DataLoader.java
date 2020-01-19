/**
 * 
 */
package guru.springframework.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;

/**
 * @author Maor Zaken Created on Jan 18, 2020
 */
@Component
public class DataLoader implements CommandLineRunner {

	private final RecipeService recipeService;
	private final UnitOfMeasureService uomService;
	
	public DataLoader(RecipeService recipeService, UnitOfMeasureService uomService) {
		this.recipeService = recipeService;
		this.uomService = uomService;
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
		perfectGuacamole.setCookTime(0);
		perfectGuacamole.setPrepTime(15);
		perfectGuacamole.setServings(3);
		
		
		Ingredient avocado = new Ingredient();
		avocado.setAmount(new BigDecimal(1));
		avocado.setDescription("Ripe Avocado");
		avocado.setUnitOfMeasure(uomService.findByDescription("One").get());
		perfectGuacamole.getIngredients().add(avocado);
		
		Ingredient lemonJuice = new Ingredient();
		lemonJuice.setDescription("Lemon Juice");
		lemonJuice.setAmount(new BigDecimal(1));
		lemonJuice.setUnitOfMeasure(uomService.findByDescription("Splash").get());
		perfectGuacamole.getIngredients().add(lemonJuice);
		
		recipeService.save(perfectGuacamole);
	}
}
