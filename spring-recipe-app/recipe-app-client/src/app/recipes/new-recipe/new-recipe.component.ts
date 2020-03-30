import { Difficulty } from './../difficulty';
import { RecipeService } from './../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Recipe } from '../recipe';

@Component({
  selector: 'new-recipe',
  templateUrl: './new-recipe.component.html',
  styleUrls: ['./new-recipe.component.css']
})
export class NewRecipeComponent implements OnInit {
  recipe: Recipe;
  difficulties = Object.values(Difficulty);

  constructor(
      private recipeService: RecipeService, 
      private router: Router,
      private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id;
    this.route.paramMap
      .subscribe(params => {
        id = params.get('id');
        if (id != null) {
          this.recipeService.get(id)
            .subscribe(recipe => this.recipe = recipe);
        }
      })
  }

  submit(form) {

  }

}
