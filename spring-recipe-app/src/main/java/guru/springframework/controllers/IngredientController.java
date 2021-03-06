package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
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

	@GetMapping("/recipe/{recipeid}/ingredients")
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

	@GetMapping("recipe/{recipeid}/ingredient/{id}/update")
	public String updateIngredient(@PathVariable String recipeid, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeid), Long.valueOf(id)));
		model.addAttribute("uomList", uomService.getAllUomCommands());

		return "recipe/ingredient/ingredientform";
	}

	@PostMapping("recipe/{recipeid}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

		log.debug("saved receipe id:" + savedCommand.getRecipeId());
		log.debug("saved ingredient id:" + savedCommand.getId());

		return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
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
