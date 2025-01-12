import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentResearchComponent } from './content-research.component';

describe('ContentResearchComponent', () => {
  let component: ContentResearchComponent;
  let fixture: ComponentFixture<ContentResearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContentResearchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContentResearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
