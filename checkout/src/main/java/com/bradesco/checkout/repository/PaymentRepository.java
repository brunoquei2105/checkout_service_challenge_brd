package com.bradesco.checkout.repository;

import com.bradesco.checkout.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Long, Payment> {

}
