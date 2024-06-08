import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductserviceComponent } from './productservice.component';

describe('ProductserviceComponent', () => {
  let component: ProductserviceComponent;
  let fixture: ComponentFixture<ProductserviceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductserviceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProductserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
