import { Component, Input } from '@angular/core';

// interfaces
import { Class } from '../../interfaces/response/class';

@Component({
    selector: 'app-module-classes',
    imports: [],
    template: `
        <main>
            @for (class of classes; track $index) {
            <div class="card">
                <i class="pi pi-video"></i>
                <span class="class_title">{{ class.title }}</span>
            </div>
            }
        </main>
    `,
    styles: `
        .card {
            padding-top: 1rem;
            padding-left: 1.5rem;

            cursor: default;
        }

        .class_title {
            padding-left: 0.5rem;
        }    
    `,
})
export class ModuleClassesComponent {
    @Input() public classes: Class[] = [];
}
