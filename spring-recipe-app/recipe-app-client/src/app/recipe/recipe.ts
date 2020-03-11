
export interface Recipe {
    id: number;
    description: string;
    prepTime: number;
    cookTime: number;
    servings: number;
    source: string;
    url: string;
    imgUrl: string;
    notes: string;
    categories: string[];
    image: string;
    difficulty: string;
}