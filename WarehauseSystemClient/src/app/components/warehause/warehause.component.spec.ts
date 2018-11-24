import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehauseComponent } from './warehause.component';

describe('WarehauseComponent', () => {
  let component: WarehauseComponent;
  let fixture: ComponentFixture<WarehauseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarehauseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarehauseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
