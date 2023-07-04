import userExtra from 'app/entities/user-extra/user-extra.reducer';
import produit from 'app/entities/produit/produit.reducer';
import category from 'app/entities/category/category.reducer';
import bankAccount from 'app/entities/bank-account/bank-account.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  userExtra,
  produit,
  category,
  bankAccount,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
