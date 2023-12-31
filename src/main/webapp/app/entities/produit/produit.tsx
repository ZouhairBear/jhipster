import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduit } from 'app/shared/model/produit.model';
import { getEntities } from './produit.reducer';

export const Produit = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const produitList = useAppSelector(state => state.produit.entities);
  const loading = useAppSelector(state => state.produit.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="produit-heading" data-cy="ProduitHeading">
        <Translate contentKey="jhipsterApp.produit.home.title">Produits</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="jhipsterApp.produit.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/produit/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterApp.produit.home.createLabel">Create new Produit</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {produitList && produitList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="jhipsterApp.produit.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.produit.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.produit.price">Price</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.produit.userExtra">User Extra</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.produit.category">Category</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {produitList.map((produit, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/produit/${produit.id}`} color="link" size="sm">
                      {produit.id}
                    </Button>
                  </td>
                  <td>{produit.name}</td>
                  <td>{produit.price}</td>
                  <td>
                    {produit.userExtras
                      ? produit.userExtras.map((val, j) => (
                          <span key={j}>
                            <Link to={`/user-extra/${val.id}`}>{val.id}</Link>
                            {j === produit.userExtras.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>{produit.category ? <Link to={`/category/${produit.category.id}`}>{produit.category.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/produit/${produit.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/produit/${produit.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/produit/${produit.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="jhipsterApp.produit.home.notFound">No Produits found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Produit;
