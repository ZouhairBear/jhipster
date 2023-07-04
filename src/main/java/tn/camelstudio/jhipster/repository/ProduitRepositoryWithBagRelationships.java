package tn.camelstudio.jhipster.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import tn.camelstudio.jhipster.domain.Produit;

public interface ProduitRepositoryWithBagRelationships {
    Optional<Produit> fetchBagRelationships(Optional<Produit> produit);

    List<Produit> fetchBagRelationships(List<Produit> produits);

    Page<Produit> fetchBagRelationships(Page<Produit> produits);
}
