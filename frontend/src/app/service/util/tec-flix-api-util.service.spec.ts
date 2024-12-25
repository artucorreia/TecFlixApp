import { TestBed } from '@angular/core/testing';

import { TecFlixApiUtilService } from './tec-flix-api-util.service';

describe('TecFlixApiUtilService', () => {
  let service: TecFlixApiUtilService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TecFlixApiUtilService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
