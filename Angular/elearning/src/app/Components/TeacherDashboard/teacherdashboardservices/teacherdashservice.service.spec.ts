import { TestBed } from '@angular/core/testing';

import { TeacherdashserviceService } from './teacherdashservice.service';

describe('TeacherdashserviceService', () => {
  let service: TeacherdashserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeacherdashserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
