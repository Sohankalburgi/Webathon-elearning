import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourselComponent } from './coursel.component';

describe('CourselComponent', () => {
  let component: CourselComponent;
  let fixture: ComponentFixture<CourselComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CourselComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourselComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
