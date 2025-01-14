import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

// primeng
import { TabsModule } from 'primeng/tabs';
import { ChangePasswordComponent } from '../../components/change-password/change-password.component';
import { ChangeUserDataComponent } from '../../components/change-user-data/change-user-data.component';

@Component({
    selector: 'app-account',
    imports: [
        CommonModule,
        ChangePasswordComponent,
        ChangeUserDataComponent,

        // primeng
        TabsModule,
    ],
    templateUrl: './account.component.html',
    styleUrl: './account.component.scss',
})
export class AccountComponent {}
