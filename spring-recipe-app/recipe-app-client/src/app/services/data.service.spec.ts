import { TestBed } from '@angular/core/testing';
import { DataService } from './data.service';


describe('DataService', () => {
  let service: DataService<null>;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
