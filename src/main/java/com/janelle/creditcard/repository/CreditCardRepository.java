package com.janelle.creditcard.repository;

import com.janelle.creditcard.model.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {

}
