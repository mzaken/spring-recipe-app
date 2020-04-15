import { BadRequestError } from './../../common/bad-request-error';
import { AppError } from './../../common/app-error';
import { Ingredient } from './../ingredient';
import { UnitOfMeasure } from '../../unit-of-measures/unitOfMeasure';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeService } from './../../services/recipe.service';
import { IngredientService } from './../../services/ingredient.service';
import { Component, OnInit } from '@angular/core';
import { UnitOfMeasureService } from 'src/app/services/unit-of-measure.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'ingredient-add',
  templateUrl: './ingredient-add.component.html',
  styleUrls: ['./ingredient-add.component.css']
})
export class IngredientAddComponent implements OnInit {
  ingredientForm = new FormGroup({
    description: new FormControl('', Validators.required),
    amount: new FormControl('', [Validators.required, Validators.min(0)]),
    uom: new FormControl('', Validators.required),
  });

  ingredient: Ingredient;
  uoms: UnitOfMeasure[];

  constructor(private ingredientService: IngredientService,
              private recipeService: RecipeService,
              private uomService: UnitOfMeasureService,
              private route: ActivatedRoute,
              private router: Router) {
    this.ingredient = {} as Ingredient;
    this.uoms = [];
  }

  ngOnInit(): void {
    this.ingredient.id = this.route.snapshot.params.ingredientId;
    if (this.ingredient.id != null) {
      this.ingredientService.get(this.ingredient.id)
        .subscribe(ingredinet => {
          this.ingredient = ingredinet;
          this.ingredientForm.get('description').setValue(ingredinet.description);
          this.ingredientForm.get('amount').setValue(ingredinet.amount);
          this.ingredientForm.get('uom').patchValue(ingredinet.uom);
        });
    }
    else {
      let recipeId = this.route.snapshot.params.id;

      this.recipeService.get(recipeId)
        .subscribe(recipe => this.ingredient.recipe = recipe);

      }

      this.uomService.getAll()
        .subscribe(uoms => this.uoms = uoms);
  }

  addIngredient(ingredientForm: Ingredient) {
    this.ingredient.amount = ingredientForm.amount;
    this.ingredient.description = ingredientForm.description;
    this.ingredient.uom = ingredientForm.uom;
    console.log(JSON.stringify(this.ingredient));
    this.ingredientService.create(this.ingredient)
      .subscribe(
        (ingredient: Ingredient) => {
          this.ingredient.id = ingredient.id
          this.navigateToIngredients();
        },
        (error: AppError) => {
          if (error instanceof BadRequestError) {
            //bad request error
          }
          else throw error;
        });
  }

  navigateToIngredients() {
    this.router.navigate(['recipes', this.ingredient.recipe.id, 'ingredients']);
  }

  compareUoms(uom1: UnitOfMeasure, uom2: UnitOfMeasure) : boolean {
    return uom1 && uom2 ? uom1.id === uom2.id : uom1 == uom2;
  }
}
