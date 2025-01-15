import { User } from './user';

export interface Review {
    id: number;
    comment: string;
    createdAt: Date;
    user: User;
    score: number;
}
