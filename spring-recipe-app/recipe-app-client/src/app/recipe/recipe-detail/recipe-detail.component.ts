import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe.service';
import { Recipe } from '../recipe';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {
  recipe: Recipe;

  constructor(private recipeService: RecipeService, private router: ActivatedRoute) { }

  ngOnInit(): void {
    let id;
    this.router.paramMap
    .subscribe(params => {
      id = params.get('id');
      this.recipeService.get(id)
        .subscribe(recipe => this.recipe = recipe);
        console.log(this.recipe);
    });
  }
}
