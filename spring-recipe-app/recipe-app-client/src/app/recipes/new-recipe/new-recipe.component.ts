import { Category } from './../../categories/category';
import { Difficulty } from '../difficulty';
import { RecipeService } from '../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Recipe } from '../recipe';
import { CategoryService } from 'src/app/services/category.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'new-recipe',
  templateUrl: './new-recipe.component.html',
  styleUrls: ['./new-recipe.component.css']
})
export class NewRecipeComponent implements OnInit {
  recipe: Recipe;
  difficulties = Object.values(Difficulty);
  categories: Category[];
  recipeForm :FormGroup;

  constructor(fb: FormBuilder,
      private recipeService: RecipeService,
      private categoryService: CategoryService,
      private router: Router,
      private route: ActivatedRoute) {
        this.recipeForm = fb.group({
          description: ['', Validators.required],
          categories: fb.array([
            fb.control('')
          ]),
          prepTime: ['', Validators.min(0)],
          cookTime: ['', Validators.min(0)],
          servings: ['', Validators.min(1)],
          source: [''],
          url: [''],
          notes: [''],
          difficulty: ['', Validators.required],
          ingredients:[''],
          directions:['', Validators.required]
        })
      }

  ngOnInit(): void {
    let id;
    this.route.paramMap
      .subscribe(params => {
        id = params.get('id');
        if (id != null) {
          this.recipeService.get(id)
            .subscribe(recipe => {
              this.recipe = recipe
              this.categories = recipe.categories;
            });
        } else {
          this.categoryService.getAll()
            .subscribe(categories => this.categories = categories);
            this.recipe = new Recipe();
        }
      })
  }

  addRecipe(form) {
    console.log(form);
    this.recipe.categories = form.categories
  }

}
