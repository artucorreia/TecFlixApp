import { Component, inject, signal, WritableSignal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

// services
import { TagService } from '../../services/api/tag.service';
import { ApiUtilService } from '../../services/api/api-util.service';

// interfaces
import { Tag } from '../../interfaces/response/tag';

// primeng
import { MultiSelectModule } from 'primeng/multiselect';

@Component({
    selector: 'app-tag-selector',
    imports: [
        FormsModule,

        // primeng
        MultiSelectModule,
    ],
    templateUrl: './tag-selector.component.html',
    styleUrl: './tag-selector.component.scss',
})
export class TagSelectorComponent {
    private _router: Router = inject(Router);
    private _http: ActivatedRoute = inject(ActivatedRoute);
    private _tagsService: TagService = inject(TagService);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);

    public tags: WritableSignal<Tag[]> = signal([]);
    public selectedTags: WritableSignal<Tag[]> = signal([]);

    ngOnInit() {
        this._tagsService.findAllTags().subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    console.log(response);
                    return;
                }
                this.tags.set(response);
                this.setSelectedTags(response);
            },
            error: (error) => {
                console.log('unexpected error', error);
            },
        });
    }

    private setSelectedTags(tags: Tag[]) {
        this._http.queryParams.subscribe((params) => {
            if (params['tags']) {
                const tagsId: string[] = params['tags'].split(',');
                const result: Tag[] = [];

                for (const id of tagsId) {
                    result.push(tags[Number(id) - 1]);
                }

                this.selectedTags.set(result);
            }
        });
    }

    private setQueryParams(tagsId: string) {
        this._router.navigate([], {
            queryParams: {
                term: this.getTerm(),
                tags: tagsId,
                page: 0,
                size: 10,
                direction: 'totalReviews,desc',
            },
        });
    }

    private getTerm(): string {
        let term = '';
        this._http.queryParams.subscribe((params) => {
            if (params['term']) term = params['term'];
        });
        return term;
    }

    public filter() {
        let tagsId: number[] = [];
        for (const tag of this.selectedTags()) {
            tagsId.push(tag.id);
        }
        this.setQueryParams(tagsId.toString());
    }
}
