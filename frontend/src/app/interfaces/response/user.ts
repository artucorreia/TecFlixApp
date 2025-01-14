import { Role } from '../../enums/role';
import { Course } from './course';
import { ProfessorData } from './professor-data';
import { Social } from './social';

export interface User {
    id: string;
    name: string;
    email: string | null;
    role: Role | null;
    createdAt: Date | null;
    active: boolean | null;
    professorData: ProfessorData | null;
    enrolledCourses: Course[] | null;
    socials: Social[] | null;
    coursesTaught: Course[] | null;
    // payments: Payment[];
}
