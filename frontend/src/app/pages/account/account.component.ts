import { Component, inject, signal, WritableSignal } from '@angular/core';
import {
    FormBuilder,
    FormControl,
    FormGroup,
    Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';

// services
import { UserService } from '../../services/api/tecflix/user.service';
import { ApiUtilService } from '../../services/api/tecflix/api-util.service';
import { MessageUtilService } from '../../services/util/message-util.service';

// interfaces
import { User } from '../../interfaces/response/user';

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
