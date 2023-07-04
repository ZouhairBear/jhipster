import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './user-extra.reducer';

export const UserExtraDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const userExtraEntity = useAppSelector(state => state.userExtra.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userExtraDetailsHeading">
          <Translate contentKey="jhipsterApp.userExtra.detail.title">UserExtra</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.id}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="jhipsterApp.userExtra.country">Country</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.country}</dd>
          <dt>
            <span id="coverPictureUrl">
              <Translate contentKey="jhipsterApp.userExtra.coverPictureUrl">Cover Picture Url</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.coverPictureUrl}</dd>
          <dt>
            <span id="facebookLink">
              <Translate contentKey="jhipsterApp.userExtra.facebookLink">Facebook Link</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.facebookLink}</dd>
          <dt>
            <span id="instagramLink">
              <Translate contentKey="jhipsterApp.userExtra.instagramLink">Instagram Link</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.instagramLink}</dd>
          <dt>
            <span id="twitterLink">
              <Translate contentKey="jhipsterApp.userExtra.twitterLink">Twitter Link</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.twitterLink}</dd>
          <dt>
            <span id="websiteLink">
              <Translate contentKey="jhipsterApp.userExtra.websiteLink">Website Link</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.websiteLink}</dd>
          <dt>
            <span id="zipCode">
              <Translate contentKey="jhipsterApp.userExtra.zipCode">Zip Code</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.zipCode}</dd>
          <dt>
            <span id="birthdate">
              <Translate contentKey="jhipsterApp.userExtra.birthdate">Birthdate</Translate>
            </span>
          </dt>
          <dd>
            {userExtraEntity.birthdate ? <TextFormat value={userExtraEntity.birthdate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="gender">
              <Translate contentKey="jhipsterApp.userExtra.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.gender}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="jhipsterApp.userExtra.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.phoneNumber}</dd>
          <dt>
            <span id="twoFactorEnabled">
              <Translate contentKey="jhipsterApp.userExtra.twoFactorEnabled">Two Factor Enabled</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.twoFactorEnabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="secretCode">
              <Translate contentKey="jhipsterApp.userExtra.secretCode">Secret Code</Translate>
            </span>
          </dt>
          <dd>{userExtraEntity.secretCode}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.userExtra.user">User</Translate>
          </dt>
          <dd>{userExtraEntity.user ? userExtraEntity.user.login : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.userExtra.bankAccount">Bank Account</Translate>
          </dt>
          <dd>{userExtraEntity.bankAccount ? userExtraEntity.bankAccount.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/user-extra" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-extra/${userExtraEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default UserExtraDetail;
