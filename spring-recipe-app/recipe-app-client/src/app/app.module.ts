import { RecipeService } from './services/recipe.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RecipeDetailComponent } from './recipe/recipe-detail/recipe-detail.component';
import { RouterModule } from '@angular/router';
import { NewRecipeComponent } from './recipe/new-recipe/new-recipe.component';
import { RecipeImageComponent } from './recipe/recipe-image/recipe-image.component';
import { ImageService } from './services/image.service';
import { IngredientListComponent } from './recipe/ingredient/ingredient-list/ingredient-list.component';
import { IngredientDetailComponent } from './recipe/ingredient/ingredient-detail/ingredient-detail.component';
import { IngredientService } from './services/ingredient.service';


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
      { path: 'recipe/:id/ingredient/:ingredientId', component: IngredientDetailComponent},
      { path: 'recipe/:id/update', component: NewRecipeComponent},
      { path: 'recipe/:id/ingredient', component: IngredientListComponent},
      { path: 'recipe/:id/image', component: RecipeImageComponent},
      { path: 'recipe/:id', component: RecipeDetailComponent},
      { path: 'recipe/new', component: NewRecipeComponent},
      { path: '', component: HomeComponent }
    ])
  ],
  providers: [RecipeService, ImageService, IngredientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
