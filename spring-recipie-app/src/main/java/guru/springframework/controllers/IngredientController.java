package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {
	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	
	public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeid}/ingredients")
	public String showIngredients(@PathVariable String recipeid, Model model) {
		log.debug("Getting ingredient list for recipe id:" + recipeid);
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeid)));

		return "recipe/ingredient/list";
	}

	@GetMapping
	@RequestMapping("recipe/{recipeid}/ingredient/{id}/show")
	public String showIngredientByRecipeIdAndIngredientId(	@PathVariable String recipeid,
															@PathVariable String id, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
				Long.valueOf(recipeid), Long.valueOf(id)));
		
		return "recipe/ingredient/show";
	}

}
