import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NonAdminHomeComponent } from './non-admin-home.component';

describe('NonAdminHomeComponent', () => {
  let component: NonAdminHomeComponent;
  let fixture: ComponentFixture<NonAdminHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NonAdminHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NonAdminHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
