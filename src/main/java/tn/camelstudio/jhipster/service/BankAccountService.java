package tn.camelstudio.jhipster.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camelstudio.jhipster.domain.BankAccount;
import tn.camelstudio.jhipster.repository.BankAccountRepository;

/**
 * Service Implementation for managing {@link BankAccount}.
 */
@Service
@Transactional
public class BankAccountService {

    private final Logger log = LoggerFactory.getLogger(BankAccountService.class);

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    /**
     * Save a bankAccount.
     *
     * @param bankAccount the entity to save.
     * @return the persisted entity.
     */
    public BankAccount save(BankAccount bankAccount) {
        log.debug("Request to save BankAccount : {}", bankAccount);
        return bankAccountRepository.save(bankAccount);
    }

    /**
     * Update a bankAccount.
     *
     * @param bankAccount the entity to save.
     * @return the persisted entity.
     */
    public BankAccount update(BankAccount bankAccount) {
        log.debug("Request to update BankAccount : {}", bankAccount);
        return bankAccountRepository.save(bankAccount);
    }

    /**
     * Partially update a bankAccount.
     *
     * @param bankAccount the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BankAccount> partialUpdate(BankAccount bankAccount) {
        log.debug("Request to partially update BankAccount : {}", bankAccount);

        return bankAccountRepository
            .findById(bankAccount.getId())
            .map(existingBankAccount -> {
                if (bankAccount.getRib() != null) {
                    existingBankAccount.setRib(bankAccount.getRib());
                }

                return existingBankAccount;
            })
            .map(bankAccountRepository::save);
    }

    /**
     * Get all the bankAccounts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BankAccount> findAll() {
        log.debug("Request to get all BankAccounts");
        return bankAccountRepository.findAll();
    }

    /**
     *  Get all the bankAccounts where UserExtra is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BankAccount> findAllWhereUserExtraIsNull() {
        log.debug("Request to get all bankAccounts where UserExtra is null");
        return StreamSupport
            .stream(bankAccountRepository.findAll().spliterator(), false)
            .filter(bankAccount -> bankAccount.getUserExtra() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one bankAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BankAccount> findOne(Long id) {
        log.debug("Request to get BankAccount : {}", id);
        return bankAccountRepository.findById(id);
    }

    /**
     * Delete the bankAccount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BankAccount : {}", id);
        bankAccountRepository.deleteById(id);
    }
}
