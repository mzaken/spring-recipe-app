import { UnitOfMeasure } from '../unit-of-measures/unitOfMeasure';
import { Recipe } from '../recipes/recipe';

export interface Ingredient {
    id: number;
    description: string;
    amount: number;
    uom: UnitOfMeasure;
    recipe: Recipe;
}
