import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DataService } from './data.service';
import { Ingredient } from '../recipe/ingredient/ingredient';

@Injectable({
  providedIn: 'root'
})
export class IngredientService extends DataService<Ingredient>{

  constructor(http: HttpClient, route: ActivatedRoute) {
    let recipeId = route.snapshot.paramMap.get('id');
    super('http://localhost:8082/recipe/' + recipeId + '/ingredient', http);
   }
}
