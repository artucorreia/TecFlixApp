import { Component, Input } from '@angular/core';

// components
import { ModuleClassesComponent } from '../module-classes/module-classes.component';

// interfaces
import { Module } from '../../interfaces/response/module';

// primeng
import { SkeletonModule } from 'primeng/skeleton';
import { AccordionModule } from 'primeng/accordion';

@Component({
    selector: 'app-course-modules',
    imports: [
        ModuleClassesComponent,

        // primeng
        SkeletonModule,
        AccordionModule,
    ],
    templateUrl: './course-modules.component.html',
    styleUrl: './course-modules.component.scss',
})
export class CourseModulesComponent {
    @Input() public modules: Module[] = [];
}
