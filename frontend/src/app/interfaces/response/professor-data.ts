import { Gender } from '../../enums/gender';
import { Occupation } from '../../enums/occupation';
import { User } from './user';

export interface ProfessorData {
    id: string;
    user: User;
    cpf: string;
    birthdate: Date;
    gender: Gender;
    contact: string;
    occupation: Occupation;
    biography: string;
    profileImage: string;
    createdAt: Date;
}
