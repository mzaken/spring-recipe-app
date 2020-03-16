import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../ingredient';
import { IngredientService } from 'src/app/services/ingredient.service';

@Component({
  selector: 'ingredient-detail',
  templateUrl: './ingredient-detail.component.html',
  styleUrls: ['./ingredient-detail.component.css']
})
export class IngredientDetailComponent implements OnInit {
  ingredient: Ingredient;

  constructor(private ingredientService: IngredientService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    let ingredientId;
    this.route.paramMap
      .subscribe(params => {
        ingredientId = params.get('ingredientId');
        this.ingredientService.get(ingredientId)
          .subscribe(ingredient => this.ingredient = ingredient);
      })
  }
}
