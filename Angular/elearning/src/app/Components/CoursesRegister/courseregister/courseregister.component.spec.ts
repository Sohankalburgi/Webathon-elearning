import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseregisterComponent } from './courseregister.component';

describe('CourseregisterComponent', () => {
  let component: CourseregisterComponent;
  let fixture: ComponentFixture<CourseregisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CourseregisterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseregisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
