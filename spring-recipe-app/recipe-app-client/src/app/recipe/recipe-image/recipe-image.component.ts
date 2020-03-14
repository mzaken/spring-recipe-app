import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'recipe-image',
  templateUrl: './recipe-image.component.html',
  styleUrls: ['./recipe-image.component.css']
})
export class RecipeImageComponent implements OnInit {
  private recipeId: number;

  constructor(private imageService: ImageService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap
      .subscribe(params => this.recipeId = +params.get('id'));
  }

  uploadImage(imageInput) {


    console.log(imageInput.files[0]);
    this.imageService.uploadImage(imageInput.files[0], this.recipeId)
      .subscribe(response => console.log(response));
  }
}
