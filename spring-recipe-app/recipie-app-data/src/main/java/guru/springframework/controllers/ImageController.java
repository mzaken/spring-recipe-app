package guru.springframework.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {
	
	private final RecipeService recipeService;
	private final ImageService imageService;

	public ImageController(RecipeService recipeService, ImageService imageService) {
		this.recipeService = recipeService;
		this.imageService = imageService;
	}

	@GetMapping("/recipe/{recipeId}/image")
	public String showImageForm(@PathVariable String recipeId, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
		
		return "/recipe/imageUploadForm.html";
	}
	
//	@PostMapping("/recipe/{recipeId}/image")
//	public String uploadImage(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file) {
//		imageService.saveImage(Long.valueOf(recipeId), file);
//		log.debug("uploading image");
//		
//		return "redirect:/recipe/"+recipeId+"/show";
//	}
	
	@PostMapping("/recipe/{recipeId}/image")
	public ResponseEntity uploadImage(@PathVariable String recipeId, @RequestParam("file") MultipartFile file) {
		String message = null;
		imageService.saveImage(Long.valueOf(recipeId), file);
		log.debug("uploading image");
		
		message = "Successfuly uploaded";
		return null;
	}

}
