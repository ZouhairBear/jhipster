package tn.camelstudio.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import tn.camelstudio.jhipster.domain.enumeration.Gender;

/**
 * A UserExtra.
 */
@Entity
@Table(name = "user_extra")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserExtra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "cover_picture_url")
    private String coverPictureUrl;

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "instagram_link")
    private String instagramLink;

    @Column(name = "twitter_link")
    private String twitterLink;

    @Column(name = "website_link")
    private String websiteLink;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "two_factor_enabled")
    private Boolean twoFactorEnabled;

    @Column(name = "secret_code")
    private String secretCode;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @JsonIgnoreProperties(value = { "userExtra" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private BankAccount bankAccount;

    @ManyToMany(mappedBy = "userExtras")
    @JsonIgnoreProperties(value = { "userExtras", "category" }, allowSetters = true)
    private Set<Produit> produits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserExtra id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public UserExtra country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCoverPictureUrl() {
        return this.coverPictureUrl;
    }

    public UserExtra coverPictureUrl(String coverPictureUrl) {
        this.setCoverPictureUrl(coverPictureUrl);
        return this;
    }

    public void setCoverPictureUrl(String coverPictureUrl) {
        this.coverPictureUrl = coverPictureUrl;
    }

    public String getFacebookLink() {
        return this.facebookLink;
    }

    public UserExtra facebookLink(String facebookLink) {
        this.setFacebookLink(facebookLink);
        return this;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getInstagramLink() {
        return this.instagramLink;
    }

    public UserExtra instagramLink(String instagramLink) {
        this.setInstagramLink(instagramLink);
        return this;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getTwitterLink() {
        return this.twitterLink;
    }

    public UserExtra twitterLink(String twitterLink) {
        this.setTwitterLink(twitterLink);
        return this;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getWebsiteLink() {
        return this.websiteLink;
    }

    public UserExtra websiteLink(String websiteLink) {
        this.setWebsiteLink(websiteLink);
        return this;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public UserExtra zipCode(String zipCode) {
        this.setZipCode(zipCode);
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public UserExtra birthdate(LocalDate birthdate) {
        this.setBirthdate(birthdate);
        return this;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return this.gender;
    }

    public UserExtra gender(Gender gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public UserExtra phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getTwoFactorEnabled() {
        return this.twoFactorEnabled;
    }

    public UserExtra twoFactorEnabled(Boolean twoFactorEnabled) {
        this.setTwoFactorEnabled(twoFactorEnabled);
        return this;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public String getSecretCode() {
        return this.secretCode;
    }

    public UserExtra secretCode(String secretCode) {
        this.setSecretCode(secretCode);
        return this;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserExtra user(User user) {
        this.setUser(user);
        return this;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public UserExtra bankAccount(BankAccount bankAccount) {
        this.setBankAccount(bankAccount);
        return this;
    }

    public Set<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(Set<Produit> produits) {
        if (this.produits != null) {
            this.produits.forEach(i -> i.removeUserExtra(this));
        }
        if (produits != null) {
            produits.forEach(i -> i.addUserExtra(this));
        }
        this.produits = produits;
    }

    public UserExtra produits(Set<Produit> produits) {
        this.setProduits(produits);
        return this;
    }

    public UserExtra addProduit(Produit produit) {
        this.produits.add(produit);
        produit.getUserExtras().add(this);
        return this;
    }

    public UserExtra removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.getUserExtras().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserExtra)) {
            return false;
        }
        return id != null && id.equals(((UserExtra) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserExtra{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", coverPictureUrl='" + getCoverPictureUrl() + "'" +
            ", facebookLink='" + getFacebookLink() + "'" +
            ", instagramLink='" + getInstagramLink() + "'" +
            ", twitterLink='" + getTwitterLink() + "'" +
            ", websiteLink='" + getWebsiteLink() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", birthdate='" + getBirthdate() + "'" +
            ", gender='" + getGender() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", twoFactorEnabled='" + getTwoFactorEnabled() + "'" +
            ", secretCode='" + getSecretCode() + "'" +
            "}";
    }
}
