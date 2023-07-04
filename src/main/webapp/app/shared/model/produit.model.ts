import { IUserExtra } from 'app/shared/model/user-extra.model';
import { ICategory } from 'app/shared/model/category.model';

export interface IProduit {
  id?: number;
  name?: string | null;
  price?: number | null;
  userExtras?: IUserExtra[] | null;
  category?: ICategory | null;
}

export const defaultValue: Readonly<IProduit> = {};
