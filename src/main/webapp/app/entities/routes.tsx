import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import UserExtra from './user-extra';
import Produit from './produit';
import Category from './category';
import BankAccount from './bank-account';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="user-extra/*" element={<UserExtra />} />
        <Route path="produit/*" element={<Produit />} />
        <Route path="category/*" element={<Category />} />
        <Route path="bank-account/*" element={<BankAccount />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
