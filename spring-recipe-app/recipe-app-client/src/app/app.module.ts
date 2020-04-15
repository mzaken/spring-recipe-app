import { RecipeService } from './services/recipe.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipeListComponent } from './recipes/recipes-list/recipes-list.component';
import { RecipeDetailComponent } from './recipes/recipe-detail/recipe-detail.component';
import { RouterModule } from '@angular/router';
import { NewRecipeComponent } from './recipes/new-recipe/new-recipe.component';
import { RecipeImageComponent } from './recipes/recipe-image/recipe-image.component';
import { ImageService } from './services/image.service';
import { IngredientService } from './services/ingredient.service';
import { IngredientListComponent } from './ingredients/ingredient-list/ingredient-list.component';
import { IngredientDetailComponent } from './ingredients/ingredient-detail/ingredient-detail.component';
import { IngredientAddComponent } from './ingredients/ingredient-add/ingredient-add.component';


@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipeDetailComponent,
    NewRecipeComponent,
    RecipeImageComponent,
    IngredientListComponent,
    IngredientDetailComponent,
    IngredientAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: 'ingredients/:ingredientId/edit', component: IngredientAddComponent},
      { path: 'ingredients/:ingredientId', component: IngredientDetailComponent},
      { path: 'recipes/:id/ingredients/new', component: IngredientAddComponent},
      { path: 'recipes/:id/update', component: NewRecipeComponent},
      { path: 'recipes/:id/ingredients', component: IngredientListComponent},
      { path: 'recipes/:id/image', component: RecipeImageComponent},
      { path: 'recipes/new', component: NewRecipeComponent},
      { path: 'recipes/:id', component: RecipeDetailComponent},
      { path: '', component: RecipeListComponent }
    ])
  ],
  providers: [RecipeService, ImageService, IngredientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
