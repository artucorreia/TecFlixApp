import { Component, inject, Input, signal } from '@angular/core';

// interfaces
import { PageMetadata } from '../../interface/response/pagination';

// primeng
import { PaginatorModule, PaginatorState } from 'primeng/paginator';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-paginator',
    imports: [PaginatorModule],
    templateUrl: './paginator.component.html',
    styleUrl: './paginator.component.scss',
})
export class PaginatorComponent {
    private _http: ActivatedRoute = inject(ActivatedRoute);
    private _router: Router = inject(Router);

    @Input() public pageMetadata!: PageMetadata;

    public first = signal(0);
    public rows = signal(10);
    public totalElements = signal(10);

    onPageChange(event: PaginatorState) {
        this.first.update((value) => (value = event.first || 0));
        this.rows.update((value) => (value = event.rows || 10));

        let queryParams = this.getQueryParams(event);
        this._router.navigate(['/search'], { queryParams: queryParams });
    }

    ngOnChanges() {
        this.first.update((value) => this.pageMetadata.number);
        this.rows.update((value) => this.pageMetadata.size);
        this.totalElements.update((value) => this.pageMetadata.totalElements);
    }

    private getQueryParams(event: PaginatorState): {
        term: string;
        tags: string;
        page?: number;
        size?: number;
        direction: string;
    } {
        let queryParams: {
            term: string;
            tags: string;
            page?: number;
            size?: number;
            direction: string;
        } = {
            term: '',
            tags: '',
            page: event.page,
            size: event.rows,
            direction: 'totalReviews,desc',
        };
        this._http.queryParams.subscribe((params) => {
            (queryParams.term = params['term']),
                (queryParams.tags = params['tags']),
                (queryParams.direction = params['direction']);
        });
        return queryParams;
    }
}
