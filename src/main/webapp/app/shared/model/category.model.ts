import { IProduit } from 'app/shared/model/produit.model';

export interface ICategory {
  id?: number;
  name?: string | null;
  produits?: IProduit[] | null;
}

export const defaultValue: Readonly<ICategory> = {};
