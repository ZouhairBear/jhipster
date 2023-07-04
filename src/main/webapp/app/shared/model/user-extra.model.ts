import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';
import { IBankAccount } from 'app/shared/model/bank-account.model';
import { IProduit } from 'app/shared/model/produit.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IUserExtra {
  id?: number;
  country?: string | null;
  coverPictureUrl?: string | null;
  facebookLink?: string | null;
  instagramLink?: string | null;
  twitterLink?: string | null;
  websiteLink?: string | null;
  zipCode?: string | null;
  birthdate?: string | null;
  gender?: Gender | null;
  phoneNumber?: string | null;
  twoFactorEnabled?: boolean | null;
  secretCode?: string | null;
  user?: IUser | null;
  bankAccount?: IBankAccount | null;
  produits?: IProduit[] | null;
}

export const defaultValue: Readonly<IUserExtra> = {
  twoFactorEnabled: false,
};
