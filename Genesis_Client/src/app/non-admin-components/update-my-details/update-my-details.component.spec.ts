import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMyDetailsComponent } from './update-my-details.component';

describe('UpdateMyDetailsComponent', () => {
  let component: UpdateMyDetailsComponent;
  let fixture: ComponentFixture<UpdateMyDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateMyDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
