import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCredentialListComponent } from './user-credential-list.component';

describe('UserCredentialListComponent', () => {
  let component: UserCredentialListComponent;
  let fixture: ComponentFixture<UserCredentialListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCredentialListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCredentialListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
