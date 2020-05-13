import { Injectable } from '@angular/core';
import { DataService } from './data.service';
import { Category } from '../categories/category';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends DataService<Category> {

  constructor(http: HttpClient) {
    super(environment.REST_API_URL + 'categories', http);
   }
}
