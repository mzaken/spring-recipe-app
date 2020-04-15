package guru.springframework.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class IngredientController {
	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService uomService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService uomService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.uomService = uomService;
	}

	@GetMapping("/recipe/{recipeid}/ingredient")
	public String showIngredients(@PathVariable String recipeid, Model model) {
		log.debug("Getting ingredient list for recipe id:" + recipeid);
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeid)));

		return "recipe/ingredient/list";
	}

	@GetMapping("recipe/{recipeid}/ingredient/{id}/show")
	public String showIngredientByRecipeIdAndIngredientId(@PathVariable String recipeid, @PathVariable String id,
			Model model) {
		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeid), Long.valueOf(id)));

		return "recipe/ingredient/show";
	}
	
	@GetMapping("recipe/{recipeid}/ingredient/{id}")
	public ResponseEntity<Ingredient> getIngredientByRecipeIdAndIngredientId(@PathVariable String recipeid, @PathVariable String id) {
		
		Ingredient ingredient = ingredientService.getByRecipeIdAndIngredientId(Long.valueOf(recipeid), Long.valueOf(id));
		
		if (ingredient == null) {
			return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(ingredient);
	}
	
	@GetMapping("ingredients/{id}")
	public ResponseEntity<Ingredient> getIngredientById(@PathVariable String id) {
		
		Ingredient ingredient = ingredientService.getIngredientById(Long.valueOf(id));
		
		if (ingredient == null) {
			return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(ingredient);
	}

	@GetMapping("recipe/{recipeid}/ingredient/{id}/update")
	public String updateIngredient(@PathVariable String recipeid, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeid), Long.valueOf(id)));
		model.addAttribute("uomList", uomService.getAllUomCommands());

		return "recipe/ingredient/ingredientform";
	}

	@RequestMapping(value = "/ingredients", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
		Ingredient savedIngredient = ingredientService.saveIngredient(ingredient);
		
		log.debug("saved receipe id:" + savedIngredient.getRecipe().getId());
		log.debug("saved ingredient id:" + savedIngredient.getId());
		
		return savedIngredient;
	}
	
	@DeleteMapping("ingredients/{id}")
	public void deleteIngredient(@PathVariable Long id) {
		ingredientService.deleteById(id);
	}
	
	@GetMapping("recipe/{recipeId}/ingredient/new")
	public String newIngredient(@PathVariable String recipeId, Model model) {
		
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
		if (recipeCommand == null) {
			//TODO: add error handling
		}
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(recipeCommand.getId());

		model.addAttribute("ingredient", ingredientCommand);
		ingredientCommand.setUom(new UnitOfMeasureCommand());
		model.addAttribute("uomList", uomService.getAllUomCommands());
		
		return "recipe/ingredient/ingredientform";
	}
	
	@GetMapping("recipe/{recipeid}/ingredient/{ingredientid}/delete")
	public String deleteIngredient(	@PathVariable String recipeid, 
									@PathVariable String ingredientid,
									Model model) {
		ingredientService.deleteByRecipeIdAndIngredientId(Long.valueOf(recipeid), Long.valueOf(ingredientid));
		
		return "redirect:/recipe/" + recipeid + "/ingredients"; 
		
	}
	
}
