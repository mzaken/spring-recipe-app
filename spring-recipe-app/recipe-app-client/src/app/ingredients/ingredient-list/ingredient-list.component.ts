import { IngredientService } from './../../services/ingredient.service';
import { Ingredient } from './../ingredient';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/recipes/recipe';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.css']
})
export class IngredientListComponent implements OnInit {
  recipe: Recipe;

  constructor(private recipeService: RecipeService,
              private ingredientServie: IngredientService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    let id: number;
    this.route.paramMap
      .subscribe(params => {
        id = +params.get('id');
        this.recipeService.get(id)
          .subscribe(recipe => this.recipe = recipe);
      });
  }

  newIngredient() {
    this.router.navigate(['/recipes', this.recipe.id, 'ingredients', 'new']);
  }

  deleteIngredient(id : number){
    this.ingredientServie.delete(id)
      .subscribe( () => {
        this.recipe.ingredients = this.recipe.ingredients.filter(ingredient => ingredient.id != id);
        console.log(this.recipe.ingredients);
      });
  }
}
