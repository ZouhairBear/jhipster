package tn.camelstudio.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A BankAccount.
 */
@Entity
@Table(name = "bank_account")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "rib")
    private String rib;

    @JsonIgnoreProperties(value = { "user", "bankAccount", "produits" }, allowSetters = true)
    @OneToOne(mappedBy = "bankAccount")
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BankAccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRib() {
        return this.rib;
    }

    public BankAccount rib(String rib) {
        this.setRib(rib);
        return this;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public UserExtra getUserExtra() {
        return this.userExtra;
    }

    public void setUserExtra(UserExtra userExtra) {
        if (this.userExtra != null) {
            this.userExtra.setBankAccount(null);
        }
        if (userExtra != null) {
            userExtra.setBankAccount(this);
        }
        this.userExtra = userExtra;
    }

    public BankAccount userExtra(UserExtra userExtra) {
        this.setUserExtra(userExtra);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        return id != null && id.equals(((BankAccount) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccount{" +
            "id=" + getId() +
            ", rib='" + getRib() + "'" +
            "}";
    }
}
