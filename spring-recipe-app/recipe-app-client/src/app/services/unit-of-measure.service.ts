import { DataService } from './data.service';
import { Injectable } from '@angular/core';
import { UnitOfMeasure } from '../unit-of-measures/unitOfMeasure';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UnitOfMeasureService extends DataService<UnitOfMeasure>{

  constructor(http: HttpClient) {
    super('http://localhost:8082/uoms', http);
   }
}
