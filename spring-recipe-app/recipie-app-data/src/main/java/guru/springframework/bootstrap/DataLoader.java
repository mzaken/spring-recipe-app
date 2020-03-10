/**
 * 
 */
package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Maor Zaken Created on Jan 18, 2020
 */
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

	private final RecipeService recipeService;
	private final UnitOfMeasureService uomService;
	private final CategoryRepository categoryRepository;
	
	public DataLoader(RecipeService recipeService, UnitOfMeasureService uomService,
			CategoryRepository categoryRepository) {
		this.recipeService = recipeService;
		this.uomService = uomService;
		this.categoryRepository = categoryRepository;
	}
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		loadData();
		log.debug("Loading bootstrap data");
	}

	private List<Recipe> loadData() {
		List<Recipe> recipes = new ArrayList<>();
		
		
		Optional<UnitOfMeasure> uomEach = uomService.findByDescription("Each");
		if (!uomEach.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> uomTablespoon = uomService.findByDescription("Tablespoon");
		if (!uomEach.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> uomTeaspoon = uomService.findByDescription("Teaspoon");
		if (!uomEach.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> uomDash = uomService.findByDescription("Dash");
		if (!uomEach.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> uomPint = uomService.findByDescription("Pint");
		if (!uomEach.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> uomCup = uomService.findByDescription("Cup");
		if (!uomEach.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
	
		UnitOfMeasure eachUom = uomEach.get();
		UnitOfMeasure tablespoonUom = uomTablespoon.get();
		UnitOfMeasure teaspoonUom = uomTeaspoon.get();
		UnitOfMeasure dashUom = uomDash.get();
		UnitOfMeasure pintUom = uomPint.get();
		UnitOfMeasure cupUom = uomCup.get();
		
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if (!americanCategoryOptional.isPresent() ) {
			throw new RuntimeException("Expected Category is missing");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		if (!mexicanCategoryOptional.isPresent() ) {
			throw new RuntimeException("Expected Category is missing");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		Recipe perfectGuacamole = new Recipe();
		perfectGuacamole.setDescription("Perfect Guaccmole");
		perfectGuacamole.setDifficulty(Difficulty.EASY);
		perfectGuacamole.setCookTime(0);
		perfectGuacamole.setPrepTime(10);
		perfectGuacamole.setServings(3);
		perfectGuacamole.getCategories().add(mexicanCategory);
		perfectGuacamole.getCategories().add(americanCategory);
		perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		perfectGuacamole.setSource("Simply Recipes");
		
		perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
				"\n" + 
				"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
				+"\n" + 
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" + 
				"\n" + 
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" + 
				"\n" + 
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." + 
				"\n" +
				"4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
		
		Notes notes = new Notes();
		notes.setNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.");
		perfectGuacamole.setNotes(notes);
		notes.setRecipe(perfectGuacamole);
		
		Ingredient avocado = new Ingredient();
		avocado.setAmount(new BigDecimal(2));
		avocado.setDescription("Ripe Avocado");
		avocado.setUom(eachUom);
		avocado.setRecipe(perfectGuacamole);
		
		Ingredient salt = new Ingredient();
		salt.setDescription("Salt");
		salt.setAmount(new BigDecimal(0.25));
		salt.setUom(teaspoonUom);
		salt.setRecipe(perfectGuacamole);
		
		Ingredient lemonJuice = new Ingredient();
		lemonJuice.setDescription("Lemon Juice");
		lemonJuice.setAmount(new BigDecimal(1));
		lemonJuice.setUom(tablespoonUom);
		lemonJuice.setRecipe(perfectGuacamole);
		 
		Ingredient redOnion = new Ingredient();
		redOnion.setDescription("Red Onion");
		redOnion.setAmount(new BigDecimal(2));
		redOnion.setUom(tablespoonUom);
		redOnion.setRecipe(perfectGuacamole);
		
		Ingredient serranoChiles = new Ingredient();
		serranoChiles.setDescription("Serrano chiles");
		serranoChiles.setAmount(new BigDecimal(2));
		serranoChiles.setUom(eachUom);
		serranoChiles.setRecipe(perfectGuacamole);
		
		Ingredient blackPepper = new Ingredient();
		blackPepper.setDescription("Black Pepper");
		blackPepper.setAmount(new BigDecimal(1));
		blackPepper.setUom(dashUom);
		blackPepper.setRecipe(perfectGuacamole);
		
		Ingredient ripeTomato = new Ingredient();
		ripeTomato.setDescription("Ripe Tomato");
		ripeTomato.setAmount(new BigDecimal(0.5));
		ripeTomato.setUom(eachUom);
		ripeTomato.setRecipe(perfectGuacamole);
		
		perfectGuacamole.getIngredients().add(avocado);
		perfectGuacamole.getIngredients().add(salt);
		perfectGuacamole.getIngredients().add(lemonJuice);
		perfectGuacamole.getIngredients().add(redOnion);
		perfectGuacamole.getIngredients().add(serranoChiles);
		perfectGuacamole.getIngredients().add(blackPepper);
		perfectGuacamole.getIngredients().add(ripeTomato);
		
		recipeService.save(perfectGuacamole);
		
		
		Recipe spicyGrilledChickenTacos = new Recipe();
		//spicyGrilledChickenTacos
		spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
		spicyGrilledChickenTacos.setDifficulty(Difficulty.EASY);
		spicyGrilledChickenTacos.setCookTime(15);
		spicyGrilledChickenTacos.setPrepTime(20);
		spicyGrilledChickenTacos.setServings(5);
		spicyGrilledChickenTacos.getCategories().add(mexicanCategory);
		spicyGrilledChickenTacos.getCategories().add(americanCategory);
		
		spicyGrilledChickenTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" + 
				"\n" + 
				"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" + 
				"\n" + 
				"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" + 
				"\n" + 
				"Spicy Grilled Chicken Tacos\n" + 
				"\n" + 
				"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" + 
				"\n" + 
				"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" + 
				"\n" + 
				"Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" + 
				"\n" + 
				"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
		
		
		Ingredient anchoChiliPowder = new Ingredient();
		anchoChiliPowder.setDescription("Ancho chili powder");
		anchoChiliPowder.setAmount(new BigDecimal(2));
		anchoChiliPowder.setUom(teaspoonUom);
		anchoChiliPowder.setRecipe(spicyGrilledChickenTacos);
		
		Ingredient driedOregano = new Ingredient();
		driedOregano.setDescription("Dried Oregano");
		driedOregano.setAmount(new BigDecimal(1));
		driedOregano.setUom(teaspoonUom);
		driedOregano.setRecipe(spicyGrilledChickenTacos);
		
		Ingredient driedCumin = new Ingredient();
		driedCumin.setDescription("Dried Cumin");
		driedCumin.setAmount(new BigDecimal(1));
		driedCumin.setUom(teaspoonUom);
		driedCumin.setRecipe(spicyGrilledChickenTacos);
		
		Ingredient sugar = new Ingredient();
		sugar.setDescription("Sugar");
		sugar.setAmount(new BigDecimal(1));
		sugar.setUom(teaspoonUom);
		sugar.setRecipe(spicyGrilledChickenTacos);
		
		spicyGrilledChickenTacos.getIngredients().add(anchoChiliPowder);
		spicyGrilledChickenTacos.getIngredients().add(driedOregano);
		spicyGrilledChickenTacos.getIngredients().add(driedCumin);
		spicyGrilledChickenTacos.getIngredients().add(sugar);
		
		spicyGrilledChickenTacos.getCategories().add(mexicanCategory);
		spicyGrilledChickenTacos.getCategories().add(americanCategory);
		
		Notes spicyNotes = new Notes();
		spicyNotes.setNotes("Some Notes");
		spicyNotes.setRecipe(spicyGrilledChickenTacos);
		spicyGrilledChickenTacos.setNotes(spicyNotes);
		spicyGrilledChickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		spicyGrilledChickenTacos.setSource("Simply Recipes");
		
		recipeService.save(spicyGrilledChickenTacos);
		
		return recipes;
	}
}
