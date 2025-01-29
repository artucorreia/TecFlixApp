export interface ProfessorRegister {
  professorData: ProfessorData;
  socials: Social[] | null;
}

interface ProfessorData {
  birthdate: string | null;
  gender: string | null;
  contact: string | null;
  occupation: string | null;
  biography: string | null;
  profileImage: string | null;
}

interface Social {
  name: string | null;
  url: string | null;
}
