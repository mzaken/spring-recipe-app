import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  url = "http://localhost:8082/recipe/";
  constructor(private http: HttpClient) { }

  uploadImage(image) {
    //this.http.post(url, image, {})
  }
}
