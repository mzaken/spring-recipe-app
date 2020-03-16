import { Category } from './category';
import { Notes } from './notes';
import { Ingredient } from './ingredient/ingredient';

export interface Recipe {
    id: number;
    description: string;
    prepTime: number;
    cookTime: number;
    servings: number;
    source: string;
    url: string;
    imgUrl: string;
    notes: Notes;
    categories: Category[];
    image: string;
    directions: string;
    difficulty: string;
    ingredients: Ingredient[];
}