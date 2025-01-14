import { Component, inject, signal, WritableSignal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

// services
import { CourseService } from '../../services/api/course.service';
import { ApiUtilService } from '../../services/api/api-util.service';

// components
import { TagSelectorComponent } from '../../components/tag-selector/tag-selector.component';
import { ContentResearchComponent } from '../../components/content-research/content-research.component';

// interfaces
import { Pagination } from '../../interfaces/response/pagination';
import { Course } from '../../interfaces/response/course';

@Component({
    selector: 'app-search',
    imports: [TagSelectorComponent, ContentResearchComponent],
    templateUrl: './search.component.html',
    styleUrl: './search.component.scss',
})
export class SearchComponent {
    private _http: ActivatedRoute = inject(ActivatedRoute);

    private _tecflixApi: CourseService = inject(CourseService);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);

    public data: WritableSignal<{
        title: string;
        pagination: Pagination<Course>;
    }> = signal({
        title: '',
        pagination: {
            content: [],
            page: null,
            links: null,
        },
    });

    ngOnInit() {
        this._http.queryParams.subscribe((params) => {
            let pageOptions: {
                page?: number;
                size?: number;
                direction?: string;
            } = {
                page: params['page'],
                size: params['size'],
                direction: params['direction'],
            };
            let searchOptions: { term?: string; tags?: string } = {
                term: params['term'],
                tags: params['tags'],
            };
            this.search(pageOptions, searchOptions);
        });
    }

    public search(
        pageOptions: {
            page?: number;
            size?: number;
            direction?: string;
        },
        params: { term?: string; tags?: string }
    ) {
        this._tecflixApi.search(pageOptions, params).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    console.log(response);
                    return;
                }
                this.data.set({
                    title: 'Cursos',
                    pagination: response,
                });
            },
            error: (error) => {
                console.log('unexpected error', error);
            },
        });
    }
}
