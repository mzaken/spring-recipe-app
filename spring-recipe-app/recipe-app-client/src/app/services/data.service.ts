import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { PageNotFoundError } from '../common/page-not-found-error';
import { BadRequestError } from '../common/bad-request-error';
import { AppError } from '../common/app-error';

export class DataService<T> {

  constructor(private url: string, private http: HttpClient) { }

  getAll() {
    return this.http.get<T[]>(this.url)
            .pipe(
              catchError(this.handleError));
  }

  create(resource) {
    return this.http.post(this.url, JSON.stringify(resource))
            .pipe(
              catchError(this.handleError));
  }

  update(resource) {
    return this.http.put(this.url + '/' + resource.id, JSON.stringify(resource))
            .pipe(
              catchError(this.handleError));
  }

  delete(id: number) {
    return this.http.delete(this.url + '/' + id)
            .pipe(
              catchError(this.handleError));
  }


  private handleError(error: Response) {
    if (error.status === 404) 
      return throwError(new PageNotFoundError(error));

    if (error.status === 400) 
      return throwError(new BadRequestError(error));

    return throwError(new AppError(error));
  }
}
