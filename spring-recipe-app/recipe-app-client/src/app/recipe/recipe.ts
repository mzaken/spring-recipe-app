import { Notes } from './recipe-detail/notes';

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
    categories: string[];
    image: string;
    directions: string;
    difficulty: string;
}