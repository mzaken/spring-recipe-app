import { UnitOfMeasure } from '../unitOfMeasure';

export interface Ingredient {
    id: number;
    description: string;
    amount: number;
    unitOfMeasure: UnitOfMeasure;
}