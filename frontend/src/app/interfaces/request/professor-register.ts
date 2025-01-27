import { Gender } from '../../enums/gender';
import { Occupation } from '../../enums/occupation';

export interface ProfessorRegister {
  professorData: ProfessorData;
  socials: Social[];
}

interface ProfessorData {
  birthdate: Date;
  gender: Gender;
  contact: string;
  occupation: Occupation;
  biography: string;
  profileImage: string;
}

interface Social {
  name: string;
  url: string;
}
