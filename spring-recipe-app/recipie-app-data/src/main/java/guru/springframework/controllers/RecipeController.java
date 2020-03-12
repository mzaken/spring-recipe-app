/**
 * 
 */
package guru.springframework.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {
	
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe")
	@ResponseBody
	public ResponseEntity<Set<Recipe>> getAll() {
		return ResponseEntity.ok(this.recipeService.getRecipes());
	}
	
	@GetMapping("/recipe/{id}")
	public ResponseEntity<Recipe> showById(@PathVariable String id, Model model) {

		//model.addAttribute("recipe", recipeService.findById(new Long(id)));
		
		//return "recipe/show";
		return ResponseEntity.ok(recipeService.findById(new Long(id)));
		
	}
	
	@GetMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeform";
	}
	
	@GetMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/recipeform";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}
	
	@GetMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/";
	}
}
