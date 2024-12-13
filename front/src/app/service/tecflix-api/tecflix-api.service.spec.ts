import { TestBed } from '@angular/core/testing';

import { TecflixApiService } from './tecflix-api.service';

describe('TecflixApiService', () => {
  let service: TecflixApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TecflixApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
