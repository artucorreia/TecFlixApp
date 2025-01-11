export interface Pagination<T> {
    content: T[] | any[];
    links: Links | null;
    page: PageMetadata | null;
}

export interface Links {
    self: string;
    first: string;
    last: string;
    next: string;
    prev: string;
}

export interface PageMetadata {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
}
