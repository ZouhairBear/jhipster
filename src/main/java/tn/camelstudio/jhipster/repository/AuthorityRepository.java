package tn.camelstudio.jhipster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camelstudio.jhipster.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
