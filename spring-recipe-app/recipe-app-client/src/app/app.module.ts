import { RecipeService } from './services/recipe.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RecipeDetailComponent } from './recipes/recipe-detail/recipe-detail.component';
import { RouterModule } from '@angular/router';
import { NewRecipeComponent } from './recipes/new-recipe/new-recipe.component';
import { RecipeImageComponent } from './recipes/recipe-image/recipe-image.component';
import { ImageService } from './services/image.service';
import { IngredientService } from './services/ingredient.service';
import { IngredientListComponent } from './ingredients/ingredient-list/ingredient-list.component';
import { IngredientDetailComponent } from './ingredients/ingredient-detail/ingredient-detail.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RecipeDetailComponent,
    NewRecipeComponent,
    RecipeImageComponent,
    IngredientListComponent,
    IngredientDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      { path: 'ingredients/:ingredientId', component: IngredientDetailComponent},
      { path: 'recipes/:id/update', component: NewRecipeComponent},
      { path: 'recipes/:id/ingredient', component: IngredientListComponent},
      { path: 'recipes/:id/image', component: RecipeImageComponent},
      { path: 'recipes/:id', component: RecipeDetailComponent},
      { path: 'recipe/new', component: NewRecipeComponent},
      { path: '', component: HomeComponent }
    ])
  ],
  providers: [RecipeService, ImageService, IngredientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
