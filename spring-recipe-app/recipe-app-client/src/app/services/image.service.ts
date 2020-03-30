import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  url: string = "http://localhost:8082/recipes";

  constructor(private http: HttpClient) { }

  uploadImage(file, recipeId) {
    const data: FormData = new FormData();
    data.append('file', file);

    return this.http.post(this.url + '/' + recipeId + '/image', data);
  }
}
