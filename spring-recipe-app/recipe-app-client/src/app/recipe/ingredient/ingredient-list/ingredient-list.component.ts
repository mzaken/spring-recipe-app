import { ActivatedRoute, Router } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from '../../recipe';

@Component({
  selector: 'ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.css']
})
export class IngredientListComponent implements OnInit {
  recipe: Recipe;

  constructor(private recipeService: RecipeService,
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
    this.router.navigate([''])
  }

}
