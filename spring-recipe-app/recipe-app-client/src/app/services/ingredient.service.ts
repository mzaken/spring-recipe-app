import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DataService } from './data.service';
import { Ingredient } from '../ingredients/ingredient';

@Injectable({
  providedIn: 'root'
})
export class IngredientService extends DataService<Ingredient> {
  private recipeId;

  constructor(http: HttpClient, route: ActivatedRoute) {
    super('http://localhost:8082/ingredients', http);
   }
}
