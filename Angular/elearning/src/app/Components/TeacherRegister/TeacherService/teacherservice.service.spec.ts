import { TestBed } from '@angular/core/testing';

import { TeacherserviceService } from './teacherservice.service';

describe('TeacherserviceService', () => {
  let service: TeacherserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeacherserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
