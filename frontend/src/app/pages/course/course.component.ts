import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';

@Component({
    selector: 'app-course',
    imports: [HeaderComponent, FooterComponent],
    templateUrl: './course.component.html',
    styleUrl: './course.component.scss',
})
export class CourseComponent {}
