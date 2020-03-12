import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Recipe } from '../recipe/recipe';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class RecipeService extends DataService<Recipe>{

  constructor(http: HttpClient) {
    super('http://localhost:8082/recipe', http);
   }
}
