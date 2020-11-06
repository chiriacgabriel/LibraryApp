import {Role} from './Role';

export class User {
  id: number;
  name: string;
  lastName: string;
  email: string;
  password: string;
  roleSet: Role;
}
