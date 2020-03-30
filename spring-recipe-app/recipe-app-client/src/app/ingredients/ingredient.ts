import { UnitOfMeasure } from '../recipes/unitOfMeasure';

export interface Ingredient {
    id: number;
    description: string;
    amount: number;
    uom: UnitOfMeasure;
}
