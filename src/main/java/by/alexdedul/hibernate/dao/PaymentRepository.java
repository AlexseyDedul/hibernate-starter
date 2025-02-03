package by.alexdedul.hibernate.dao;

import by.alexdedul.hibernate.entity.Payment;

import javax.persistence.EntityManager;

public class PaymentRepository extends BaseRepository<Long, Payment> {
    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }
}
