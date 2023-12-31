package tn.camelstudio.jhipster.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.camelstudio.jhipster.web.rest.TestUtil;

class UserExtraTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserExtra.class);
        UserExtra userExtra1 = new UserExtra();
        userExtra1.setId(1L);
        UserExtra userExtra2 = new UserExtra();
        userExtra2.setId(userExtra1.getId());
        assertThat(userExtra1).isEqualTo(userExtra2);
        userExtra2.setId(2L);
        assertThat(userExtra1).isNotEqualTo(userExtra2);
        userExtra1.setId(null);
        assertThat(userExtra1).isNotEqualTo(userExtra2);
    }
}
