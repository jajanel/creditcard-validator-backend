package com.janelle.creditcard.controller;

import com.janelle.creditcard.model.CreditCard;
import com.janelle.creditcard.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CreditCardController implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(CreditCard.class);

    @Autowired
    private CreditCardRepository creditCardRepository;



    @Override
    public void run(String... args) throws Exception {
        logger.info("The credit card controller has been started");
        deleteCreditCard();
    }

    /**
     * Save a credit card
     * @param creditCard the credit card to save
     * @return the ID of the saved credit card
     */
    @PostMapping("/saveCreditCard")
    public Long saveCreditCard(@RequestBody CreditCard creditCard) throws InterruptedException {
        Long id = 0L;
        if (creditCard != null) {
            Thread.sleep(5000);
            id = creditCardRepository.save(creditCard).getId();
            logger.info("The credit card with the ID {} has been saved into the database", id);
        } else {
            logger.warn("Invalid credit card details provided");
        }
        return id;
    }

    /**
     * Get all credit cards
     * @return all credit cards
     */
    @GetMapping("/getAllCreditCards")
    public Iterable<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    @DeleteMapping("/deleteCreditCard/")
    public void deleteCreditCard() {
        if (creditCardRepository.count() == 0) {
            logger.warn("No credit cards to delete");
        } else {
            logger.info("Deleting all credit cards");
            creditCardRepository.deleteAll();

        }
    }
}