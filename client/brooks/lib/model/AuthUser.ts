export interface AuthUser {
  id: string;
  username: string;
  password: null;
  roles: string[];
  userMetadata: UserMetadata;
}

export interface UserMetadata {
  fullname: Fullname;
  gender: string;
  dob: string;
  address: Address;
  emails: string[];
  phones: string[];
  about: string;
  relatives: Relative[];
  student: Student;
  tutor: Tutor;
}

export interface Address {
  province_city: string;
  ward_district: string;
  home_number: string;
}

export interface Fullname {
  first_name: string;
  last_name: string;
}

export interface Relative {
  id: string;
  relationship: string;
  fullname: Fullname;
  gender: string;
  dob: string;
  address: Address;
  emails: string[];
  phones: string[];
  about: string;
}

export interface Student {
  classes: string[];
  periods: string[];
}

export interface Tutor {
  job: string;
  graduatedSchool: string;
  major: string;
  qualification: string;
  graduatedYear: number;
  grades: string[];
  subjects: string[];
  minimumSalaryRequirement: number;
  teachingClasses: string[];
  periods: string[];
}
