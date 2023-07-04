import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IBankAccount } from 'app/shared/model/bank-account.model';
import { getEntities as getBankAccounts } from 'app/entities/bank-account/bank-account.reducer';
import { IProduit } from 'app/shared/model/produit.model';
import { getEntities as getProduits } from 'app/entities/produit/produit.reducer';
import { IUserExtra } from 'app/shared/model/user-extra.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { getEntity, updateEntity, createEntity, reset } from './user-extra.reducer';

export const UserExtraUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const bankAccounts = useAppSelector(state => state.bankAccount.entities);
  const produits = useAppSelector(state => state.produit.entities);
  const userExtraEntity = useAppSelector(state => state.userExtra.entity);
  const loading = useAppSelector(state => state.userExtra.loading);
  const updating = useAppSelector(state => state.userExtra.updating);
  const updateSuccess = useAppSelector(state => state.userExtra.updateSuccess);
  const genderValues = Object.keys(Gender);

  const handleClose = () => {
    navigate('/user-extra');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUsers({}));
    dispatch(getBankAccounts({}));
    dispatch(getProduits({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...userExtraEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user.toString()),
      bankAccount: bankAccounts.find(it => it.id.toString() === values.bankAccount.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          gender: 'MALE',
          ...userExtraEntity,
          user: userExtraEntity?.user?.id,
          bankAccount: userExtraEntity?.bankAccount?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jhipsterApp.userExtra.home.createOrEditLabel" data-cy="UserExtraCreateUpdateHeading">
            <Translate contentKey="jhipsterApp.userExtra.home.createOrEditLabel">Create or edit a UserExtra</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="user-extra-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('jhipsterApp.userExtra.country')}
                id="user-extra-country"
                name="country"
                data-cy="country"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.coverPictureUrl')}
                id="user-extra-coverPictureUrl"
                name="coverPictureUrl"
                data-cy="coverPictureUrl"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.facebookLink')}
                id="user-extra-facebookLink"
                name="facebookLink"
                data-cy="facebookLink"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.instagramLink')}
                id="user-extra-instagramLink"
                name="instagramLink"
                data-cy="instagramLink"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.twitterLink')}
                id="user-extra-twitterLink"
                name="twitterLink"
                data-cy="twitterLink"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.websiteLink')}
                id="user-extra-websiteLink"
                name="websiteLink"
                data-cy="websiteLink"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.zipCode')}
                id="user-extra-zipCode"
                name="zipCode"
                data-cy="zipCode"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.birthdate')}
                id="user-extra-birthdate"
                name="birthdate"
                data-cy="birthdate"
                type="date"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.gender')}
                id="user-extra-gender"
                name="gender"
                data-cy="gender"
                type="select"
              >
                {genderValues.map(gender => (
                  <option value={gender} key={gender}>
                    {translate('jhipsterApp.Gender.' + gender)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('jhipsterApp.userExtra.phoneNumber')}
                id="user-extra-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.twoFactorEnabled')}
                id="user-extra-twoFactorEnabled"
                name="twoFactorEnabled"
                data-cy="twoFactorEnabled"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('jhipsterApp.userExtra.secretCode')}
                id="user-extra-secretCode"
                name="secretCode"
                data-cy="secretCode"
                type="text"
              />
              <ValidatedField id="user-extra-user" name="user" data-cy="user" label={translate('jhipsterApp.userExtra.user')} type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="user-extra-bankAccount"
                name="bankAccount"
                data-cy="bankAccount"
                label={translate('jhipsterApp.userExtra.bankAccount')}
                type="select"
              >
                <option value="" key="0" />
                {bankAccounts
                  ? bankAccounts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/user-extra" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default UserExtraUpdate;
