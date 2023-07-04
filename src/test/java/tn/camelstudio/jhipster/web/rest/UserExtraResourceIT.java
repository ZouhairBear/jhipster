package tn.camelstudio.jhipster.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.camelstudio.jhipster.IntegrationTest;
import tn.camelstudio.jhipster.domain.UserExtra;
import tn.camelstudio.jhipster.domain.enumeration.Gender;
import tn.camelstudio.jhipster.repository.UserExtraRepository;
import tn.camelstudio.jhipster.service.UserExtraService;

/**
 * Integration tests for the {@link UserExtraResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UserExtraResourceIT {

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_COVER_PICTURE_URL = "AAAAAAAAAA";
    private static final String UPDATED_COVER_PICTURE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK_LINK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM_LINK = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER_LINK = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE_LINK = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTHDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMELE;

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TWO_FACTOR_ENABLED = false;
    private static final Boolean UPDATED_TWO_FACTOR_ENABLED = true;

    private static final String DEFAULT_SECRET_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SECRET_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/user-extras";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserExtraRepository userExtraRepository;

    @Mock
    private UserExtraRepository userExtraRepositoryMock;

    @Mock
    private UserExtraService userExtraServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserExtraMockMvc;

    private UserExtra userExtra;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExtra createEntity(EntityManager em) {
        UserExtra userExtra = new UserExtra()
            .country(DEFAULT_COUNTRY)
            .coverPictureUrl(DEFAULT_COVER_PICTURE_URL)
            .facebookLink(DEFAULT_FACEBOOK_LINK)
            .instagramLink(DEFAULT_INSTAGRAM_LINK)
            .twitterLink(DEFAULT_TWITTER_LINK)
            .websiteLink(DEFAULT_WEBSITE_LINK)
            .zipCode(DEFAULT_ZIP_CODE)
            .birthdate(DEFAULT_BIRTHDATE)
            .gender(DEFAULT_GENDER)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .twoFactorEnabled(DEFAULT_TWO_FACTOR_ENABLED)
            .secretCode(DEFAULT_SECRET_CODE);
        return userExtra;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExtra createUpdatedEntity(EntityManager em) {
        UserExtra userExtra = new UserExtra()
            .country(UPDATED_COUNTRY)
            .coverPictureUrl(UPDATED_COVER_PICTURE_URL)
            .facebookLink(UPDATED_FACEBOOK_LINK)
            .instagramLink(UPDATED_INSTAGRAM_LINK)
            .twitterLink(UPDATED_TWITTER_LINK)
            .websiteLink(UPDATED_WEBSITE_LINK)
            .zipCode(UPDATED_ZIP_CODE)
            .birthdate(UPDATED_BIRTHDATE)
            .gender(UPDATED_GENDER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .twoFactorEnabled(UPDATED_TWO_FACTOR_ENABLED)
            .secretCode(UPDATED_SECRET_CODE);
        return userExtra;
    }

    @BeforeEach
    public void initTest() {
        userExtra = createEntity(em);
    }

    @Test
    @Transactional
    void createUserExtra() throws Exception {
        int databaseSizeBeforeCreate = userExtraRepository.findAll().size();
        // Create the UserExtra
        restUserExtraMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userExtra)))
            .andExpect(status().isCreated());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeCreate + 1);
        UserExtra testUserExtra = userExtraList.get(userExtraList.size() - 1);
        assertThat(testUserExtra.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testUserExtra.getCoverPictureUrl()).isEqualTo(DEFAULT_COVER_PICTURE_URL);
        assertThat(testUserExtra.getFacebookLink()).isEqualTo(DEFAULT_FACEBOOK_LINK);
        assertThat(testUserExtra.getInstagramLink()).isEqualTo(DEFAULT_INSTAGRAM_LINK);
        assertThat(testUserExtra.getTwitterLink()).isEqualTo(DEFAULT_TWITTER_LINK);
        assertThat(testUserExtra.getWebsiteLink()).isEqualTo(DEFAULT_WEBSITE_LINK);
        assertThat(testUserExtra.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testUserExtra.getBirthdate()).isEqualTo(DEFAULT_BIRTHDATE);
        assertThat(testUserExtra.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testUserExtra.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUserExtra.getTwoFactorEnabled()).isEqualTo(DEFAULT_TWO_FACTOR_ENABLED);
        assertThat(testUserExtra.getSecretCode()).isEqualTo(DEFAULT_SECRET_CODE);
    }

    @Test
    @Transactional
    void createUserExtraWithExistingId() throws Exception {
        // Create the UserExtra with an existing ID
        userExtra.setId(1L);

        int databaseSizeBeforeCreate = userExtraRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserExtraMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userExtra)))
            .andExpect(status().isBadRequest());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUserExtras() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);

        // Get all the userExtraList
        restUserExtraMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userExtra.getId().intValue())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].coverPictureUrl").value(hasItem(DEFAULT_COVER_PICTURE_URL)))
            .andExpect(jsonPath("$.[*].facebookLink").value(hasItem(DEFAULT_FACEBOOK_LINK)))
            .andExpect(jsonPath("$.[*].instagramLink").value(hasItem(DEFAULT_INSTAGRAM_LINK)))
            .andExpect(jsonPath("$.[*].twitterLink").value(hasItem(DEFAULT_TWITTER_LINK)))
            .andExpect(jsonPath("$.[*].websiteLink").value(hasItem(DEFAULT_WEBSITE_LINK)))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].twoFactorEnabled").value(hasItem(DEFAULT_TWO_FACTOR_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].secretCode").value(hasItem(DEFAULT_SECRET_CODE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUserExtrasWithEagerRelationshipsIsEnabled() throws Exception {
        when(userExtraServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUserExtraMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(userExtraServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUserExtrasWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(userExtraServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUserExtraMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(userExtraRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getUserExtra() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);

        // Get the userExtra
        restUserExtraMockMvc
            .perform(get(ENTITY_API_URL_ID, userExtra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userExtra.getId().intValue()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.coverPictureUrl").value(DEFAULT_COVER_PICTURE_URL))
            .andExpect(jsonPath("$.facebookLink").value(DEFAULT_FACEBOOK_LINK))
            .andExpect(jsonPath("$.instagramLink").value(DEFAULT_INSTAGRAM_LINK))
            .andExpect(jsonPath("$.twitterLink").value(DEFAULT_TWITTER_LINK))
            .andExpect(jsonPath("$.websiteLink").value(DEFAULT_WEBSITE_LINK))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE))
            .andExpect(jsonPath("$.birthdate").value(DEFAULT_BIRTHDATE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.twoFactorEnabled").value(DEFAULT_TWO_FACTOR_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.secretCode").value(DEFAULT_SECRET_CODE));
    }

    @Test
    @Transactional
    void getNonExistingUserExtra() throws Exception {
        // Get the userExtra
        restUserExtraMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUserExtra() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);

        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();

        // Update the userExtra
        UserExtra updatedUserExtra = userExtraRepository.findById(userExtra.getId()).get();
        // Disconnect from session so that the updates on updatedUserExtra are not directly saved in db
        em.detach(updatedUserExtra);
        updatedUserExtra
            .country(UPDATED_COUNTRY)
            .coverPictureUrl(UPDATED_COVER_PICTURE_URL)
            .facebookLink(UPDATED_FACEBOOK_LINK)
            .instagramLink(UPDATED_INSTAGRAM_LINK)
            .twitterLink(UPDATED_TWITTER_LINK)
            .websiteLink(UPDATED_WEBSITE_LINK)
            .zipCode(UPDATED_ZIP_CODE)
            .birthdate(UPDATED_BIRTHDATE)
            .gender(UPDATED_GENDER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .twoFactorEnabled(UPDATED_TWO_FACTOR_ENABLED)
            .secretCode(UPDATED_SECRET_CODE);

        restUserExtraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUserExtra.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUserExtra))
            )
            .andExpect(status().isOk());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
        UserExtra testUserExtra = userExtraList.get(userExtraList.size() - 1);
        assertThat(testUserExtra.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testUserExtra.getCoverPictureUrl()).isEqualTo(UPDATED_COVER_PICTURE_URL);
        assertThat(testUserExtra.getFacebookLink()).isEqualTo(UPDATED_FACEBOOK_LINK);
        assertThat(testUserExtra.getInstagramLink()).isEqualTo(UPDATED_INSTAGRAM_LINK);
        assertThat(testUserExtra.getTwitterLink()).isEqualTo(UPDATED_TWITTER_LINK);
        assertThat(testUserExtra.getWebsiteLink()).isEqualTo(UPDATED_WEBSITE_LINK);
        assertThat(testUserExtra.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testUserExtra.getBirthdate()).isEqualTo(UPDATED_BIRTHDATE);
        assertThat(testUserExtra.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testUserExtra.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUserExtra.getTwoFactorEnabled()).isEqualTo(UPDATED_TWO_FACTOR_ENABLED);
        assertThat(testUserExtra.getSecretCode()).isEqualTo(UPDATED_SECRET_CODE);
    }

    @Test
    @Transactional
    void putNonExistingUserExtra() throws Exception {
        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();
        userExtra.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserExtraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userExtra.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userExtra))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserExtra() throws Exception {
        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();
        userExtra.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserExtraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userExtra))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserExtra() throws Exception {
        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();
        userExtra.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserExtraMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userExtra)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserExtraWithPatch() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);

        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();

        // Update the userExtra using partial update
        UserExtra partialUpdatedUserExtra = new UserExtra();
        partialUpdatedUserExtra.setId(userExtra.getId());

        partialUpdatedUserExtra
            .country(UPDATED_COUNTRY)
            .instagramLink(UPDATED_INSTAGRAM_LINK)
            .twitterLink(UPDATED_TWITTER_LINK)
            .websiteLink(UPDATED_WEBSITE_LINK)
            .zipCode(UPDATED_ZIP_CODE)
            .birthdate(UPDATED_BIRTHDATE)
            .gender(UPDATED_GENDER)
            .twoFactorEnabled(UPDATED_TWO_FACTOR_ENABLED);

        restUserExtraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserExtra.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserExtra))
            )
            .andExpect(status().isOk());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
        UserExtra testUserExtra = userExtraList.get(userExtraList.size() - 1);
        assertThat(testUserExtra.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testUserExtra.getCoverPictureUrl()).isEqualTo(DEFAULT_COVER_PICTURE_URL);
        assertThat(testUserExtra.getFacebookLink()).isEqualTo(DEFAULT_FACEBOOK_LINK);
        assertThat(testUserExtra.getInstagramLink()).isEqualTo(UPDATED_INSTAGRAM_LINK);
        assertThat(testUserExtra.getTwitterLink()).isEqualTo(UPDATED_TWITTER_LINK);
        assertThat(testUserExtra.getWebsiteLink()).isEqualTo(UPDATED_WEBSITE_LINK);
        assertThat(testUserExtra.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testUserExtra.getBirthdate()).isEqualTo(UPDATED_BIRTHDATE);
        assertThat(testUserExtra.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testUserExtra.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUserExtra.getTwoFactorEnabled()).isEqualTo(UPDATED_TWO_FACTOR_ENABLED);
        assertThat(testUserExtra.getSecretCode()).isEqualTo(DEFAULT_SECRET_CODE);
    }

    @Test
    @Transactional
    void fullUpdateUserExtraWithPatch() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);

        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();

        // Update the userExtra using partial update
        UserExtra partialUpdatedUserExtra = new UserExtra();
        partialUpdatedUserExtra.setId(userExtra.getId());

        partialUpdatedUserExtra
            .country(UPDATED_COUNTRY)
            .coverPictureUrl(UPDATED_COVER_PICTURE_URL)
            .facebookLink(UPDATED_FACEBOOK_LINK)
            .instagramLink(UPDATED_INSTAGRAM_LINK)
            .twitterLink(UPDATED_TWITTER_LINK)
            .websiteLink(UPDATED_WEBSITE_LINK)
            .zipCode(UPDATED_ZIP_CODE)
            .birthdate(UPDATED_BIRTHDATE)
            .gender(UPDATED_GENDER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .twoFactorEnabled(UPDATED_TWO_FACTOR_ENABLED)
            .secretCode(UPDATED_SECRET_CODE);

        restUserExtraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserExtra.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserExtra))
            )
            .andExpect(status().isOk());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
        UserExtra testUserExtra = userExtraList.get(userExtraList.size() - 1);
        assertThat(testUserExtra.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testUserExtra.getCoverPictureUrl()).isEqualTo(UPDATED_COVER_PICTURE_URL);
        assertThat(testUserExtra.getFacebookLink()).isEqualTo(UPDATED_FACEBOOK_LINK);
        assertThat(testUserExtra.getInstagramLink()).isEqualTo(UPDATED_INSTAGRAM_LINK);
        assertThat(testUserExtra.getTwitterLink()).isEqualTo(UPDATED_TWITTER_LINK);
        assertThat(testUserExtra.getWebsiteLink()).isEqualTo(UPDATED_WEBSITE_LINK);
        assertThat(testUserExtra.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testUserExtra.getBirthdate()).isEqualTo(UPDATED_BIRTHDATE);
        assertThat(testUserExtra.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testUserExtra.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUserExtra.getTwoFactorEnabled()).isEqualTo(UPDATED_TWO_FACTOR_ENABLED);
        assertThat(testUserExtra.getSecretCode()).isEqualTo(UPDATED_SECRET_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingUserExtra() throws Exception {
        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();
        userExtra.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserExtraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userExtra.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userExtra))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserExtra() throws Exception {
        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();
        userExtra.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserExtraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userExtra))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserExtra() throws Exception {
        int databaseSizeBeforeUpdate = userExtraRepository.findAll().size();
        userExtra.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserExtraMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(userExtra))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserExtra in the database
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserExtra() throws Exception {
        // Initialize the database
        userExtraRepository.saveAndFlush(userExtra);

        int databaseSizeBeforeDelete = userExtraRepository.findAll().size();

        // Delete the userExtra
        restUserExtraMockMvc
            .perform(delete(ENTITY_API_URL_ID, userExtra.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserExtra> userExtraList = userExtraRepository.findAll();
        assertThat(userExtraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
