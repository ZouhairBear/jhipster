import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUserExtra } from 'app/shared/model/user-extra.model';
import { getEntities } from './user-extra.reducer';

export const UserExtra = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const userExtraList = useAppSelector(state => state.userExtra.entities);
  const loading = useAppSelector(state => state.userExtra.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="user-extra-heading" data-cy="UserExtraHeading">
        <Translate contentKey="jhipsterApp.userExtra.home.title">User Extras</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="jhipsterApp.userExtra.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/user-extra/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterApp.userExtra.home.createLabel">Create new User Extra</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {userExtraList && userExtraList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.country">Country</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.coverPictureUrl">Cover Picture Url</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.facebookLink">Facebook Link</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.instagramLink">Instagram Link</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.twitterLink">Twitter Link</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.websiteLink">Website Link</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.zipCode">Zip Code</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.birthdate">Birthdate</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.gender">Gender</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.twoFactorEnabled">Two Factor Enabled</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.secretCode">Secret Code</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.user">User</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.userExtra.bankAccount">Bank Account</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {userExtraList.map((userExtra, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/user-extra/${userExtra.id}`} color="link" size="sm">
                      {userExtra.id}
                    </Button>
                  </td>
                  <td>{userExtra.country}</td>
                  <td>{userExtra.coverPictureUrl}</td>
                  <td>{userExtra.facebookLink}</td>
                  <td>{userExtra.instagramLink}</td>
                  <td>{userExtra.twitterLink}</td>
                  <td>{userExtra.websiteLink}</td>
                  <td>{userExtra.zipCode}</td>
                  <td>
                    {userExtra.birthdate ? <TextFormat type="date" value={userExtra.birthdate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    <Translate contentKey={`jhipsterApp.Gender.${userExtra.gender}`} />
                  </td>
                  <td>{userExtra.phoneNumber}</td>
                  <td>{userExtra.twoFactorEnabled ? 'true' : 'false'}</td>
                  <td>{userExtra.secretCode}</td>
                  <td>{userExtra.user ? userExtra.user.login : ''}</td>
                  <td>
                    {userExtra.bankAccount ? <Link to={`/bank-account/${userExtra.bankAccount.id}`}>{userExtra.bankAccount.id}</Link> : ''}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/user-extra/${userExtra.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/user-extra/${userExtra.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/user-extra/${userExtra.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="jhipsterApp.userExtra.home.notFound">No User Extras found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default UserExtra;
