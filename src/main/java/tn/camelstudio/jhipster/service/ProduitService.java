package tn.camelstudio.jhipster.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camelstudio.jhipster.domain.Produit;
import tn.camelstudio.jhipster.repository.ProduitRepository;

/**
 * Service Implementation for managing {@link Produit}.
 */
@Service
@Transactional
public class ProduitService {

    private final Logger log = LoggerFactory.getLogger(ProduitService.class);

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /**
     * Save a produit.
     *
     * @param produit the entity to save.
     * @return the persisted entity.
     */
    public Produit save(Produit produit) {
        log.debug("Request to save Produit : {}", produit);
        return produitRepository.save(produit);
    }

    /**
     * Update a produit.
     *
     * @param produit the entity to save.
     * @return the persisted entity.
     */
    public Produit update(Produit produit) {
        log.debug("Request to update Produit : {}", produit);
        return produitRepository.save(produit);
    }

    /**
     * Partially update a produit.
     *
     * @param produit the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Produit> partialUpdate(Produit produit) {
        log.debug("Request to partially update Produit : {}", produit);

        return produitRepository
            .findById(produit.getId())
            .map(existingProduit -> {
                if (produit.getName() != null) {
                    existingProduit.setName(produit.getName());
                }
                if (produit.getPrice() != null) {
                    existingProduit.setPrice(produit.getPrice());
                }

                return existingProduit;
            })
            .map(produitRepository::save);
    }

    /**
     * Get all the produits.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Produit> findAll() {
        log.debug("Request to get all Produits");
        return produitRepository.findAll();
    }

    /**
     * Get all the produits with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Produit> findAllWithEagerRelationships(Pageable pageable) {
        return produitRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one produit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Produit> findOne(Long id) {
        log.debug("Request to get Produit : {}", id);
        return produitRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the produit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Produit : {}", id);
        produitRepository.deleteById(id);
    }
}
