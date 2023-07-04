package tn.camelstudio.jhipster.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camelstudio.jhipster.domain.UserExtra;
import tn.camelstudio.jhipster.repository.UserExtraRepository;

/**
 * Service Implementation for managing {@link UserExtra}.
 */
@Service
@Transactional
public class UserExtraService {

    private final Logger log = LoggerFactory.getLogger(UserExtraService.class);

    private final UserExtraRepository userExtraRepository;

    public UserExtraService(UserExtraRepository userExtraRepository) {
        this.userExtraRepository = userExtraRepository;
    }

    /**
     * Save a userExtra.
     *
     * @param userExtra the entity to save.
     * @return the persisted entity.
     */
    public UserExtra save(UserExtra userExtra) {
        log.debug("Request to save UserExtra : {}", userExtra);
        return userExtraRepository.save(userExtra);
    }

    /**
     * Update a userExtra.
     *
     * @param userExtra the entity to save.
     * @return the persisted entity.
     */
    public UserExtra update(UserExtra userExtra) {
        log.debug("Request to update UserExtra : {}", userExtra);
        return userExtraRepository.save(userExtra);
    }

    /**
     * Partially update a userExtra.
     *
     * @param userExtra the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserExtra> partialUpdate(UserExtra userExtra) {
        log.debug("Request to partially update UserExtra : {}", userExtra);

        return userExtraRepository
            .findById(userExtra.getId())
            .map(existingUserExtra -> {
                if (userExtra.getCountry() != null) {
                    existingUserExtra.setCountry(userExtra.getCountry());
                }
                if (userExtra.getCoverPictureUrl() != null) {
                    existingUserExtra.setCoverPictureUrl(userExtra.getCoverPictureUrl());
                }
                if (userExtra.getFacebookLink() != null) {
                    existingUserExtra.setFacebookLink(userExtra.getFacebookLink());
                }
                if (userExtra.getInstagramLink() != null) {
                    existingUserExtra.setInstagramLink(userExtra.getInstagramLink());
                }
                if (userExtra.getTwitterLink() != null) {
                    existingUserExtra.setTwitterLink(userExtra.getTwitterLink());
                }
                if (userExtra.getWebsiteLink() != null) {
                    existingUserExtra.setWebsiteLink(userExtra.getWebsiteLink());
                }
                if (userExtra.getZipCode() != null) {
                    existingUserExtra.setZipCode(userExtra.getZipCode());
                }
                if (userExtra.getBirthdate() != null) {
                    existingUserExtra.setBirthdate(userExtra.getBirthdate());
                }
                if (userExtra.getGender() != null) {
                    existingUserExtra.setGender(userExtra.getGender());
                }
                if (userExtra.getPhoneNumber() != null) {
                    existingUserExtra.setPhoneNumber(userExtra.getPhoneNumber());
                }
                if (userExtra.getTwoFactorEnabled() != null) {
                    existingUserExtra.setTwoFactorEnabled(userExtra.getTwoFactorEnabled());
                }
                if (userExtra.getSecretCode() != null) {
                    existingUserExtra.setSecretCode(userExtra.getSecretCode());
                }

                return existingUserExtra;
            })
            .map(userExtraRepository::save);
    }

    /**
     * Get all the userExtras.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserExtra> findAll() {
        log.debug("Request to get all UserExtras");
        return userExtraRepository.findAll();
    }

    /**
     * Get all the userExtras with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<UserExtra> findAllWithEagerRelationships(Pageable pageable) {
        return userExtraRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one userExtra by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserExtra> findOne(Long id) {
        log.debug("Request to get UserExtra : {}", id);
        return userExtraRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the userExtra by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserExtra : {}", id);
        userExtraRepository.deleteById(id);
    }
}
