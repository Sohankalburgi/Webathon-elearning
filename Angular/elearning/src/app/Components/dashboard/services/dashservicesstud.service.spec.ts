import { TestBed } from '@angular/core/testing';

import { DashservicesstudService } from './dashservicesstud.service';

describe('DashservicesstudService', () => {
  let service: DashservicesstudService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DashservicesstudService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
