import { HttpClient } from '@angular/common/http';
import { RecipeService } from './../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe/recipe';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  recipes: Recipe[];

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.recipeService.getAll()
      .subscribe(response => {
        this.recipes = response 
        console.log(this.recipes);
      });
      
  }

}
