import { IUserExtra } from 'app/shared/model/user-extra.model';

export interface IBankAccount {
  id?: number;
  rib?: string | null;
  userExtra?: IUserExtra | null;
}

export const defaultValue: Readonly<IBankAccount> = {};
