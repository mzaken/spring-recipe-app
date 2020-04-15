import { HttpClient } from '@angular/common/http';
import { RecipeService } from '../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe';

@Component({
  selector: 'home',
  templateUrl: './recipes-list.component.html',
  styleUrls: ['./recipes-list.component.css']
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[];

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.recipeService.getAll()
      .subscribe(response => {
        this.recipes = response
      });
  }

  deleteRecipe(id: number) {
    this.recipeService.delete(id)
      .subscribe(() => this.recipes = this.recipes.filter( (recipe) => recipe.id != id));
  }
}
