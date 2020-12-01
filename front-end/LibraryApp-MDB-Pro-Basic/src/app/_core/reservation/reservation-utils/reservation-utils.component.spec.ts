import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationUtilsComponent } from './reservation-utils.component';

describe('ReservationUtilsComponent', () => {
  let component: ReservationUtilsComponent;
  let fixture: ComponentFixture<ReservationUtilsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationUtilsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationUtilsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
