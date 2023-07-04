import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './produit.reducer';

export const ProduitDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const produitEntity = useAppSelector(state => state.produit.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="produitDetailsHeading">
          <Translate contentKey="jhipsterApp.produit.detail.title">Produit</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{produitEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="jhipsterApp.produit.name">Name</Translate>
            </span>
          </dt>
          <dd>{produitEntity.name}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="jhipsterApp.produit.price">Price</Translate>
            </span>
          </dt>
          <dd>{produitEntity.price}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.produit.userExtra">User Extra</Translate>
          </dt>
          <dd>
            {produitEntity.userExtras
              ? produitEntity.userExtras.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {produitEntity.userExtras && i === produitEntity.userExtras.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="jhipsterApp.produit.category">Category</Translate>
          </dt>
          <dd>{produitEntity.category ? produitEntity.category.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/produit" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/produit/${produitEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProduitDetail;
