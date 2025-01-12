import { Module } from './module';
import { Tag } from './tag';
import { User } from './user';

export interface Course {
    id: string;
    title: string;
    description: string;
    capeImage: string;
    modules: Module[];
    tags: Tag[];
    professor: User;
    totalScoreReviews: number;
    totalReviews: number;
    createdAt: Date; // '2025-01-07T19:00:31.178Z'
    active: boolean;
}
