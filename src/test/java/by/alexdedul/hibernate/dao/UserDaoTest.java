package by.alexdedul.hibernate.dao;

import by.alexdedul.hibernate.dto.PaymentFilter;
import by.alexdedul.hibernate.entity.User;
import by.alexdedul.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoTest {

    private final UserDao userDao = UserDao.getInstance();

    @Test
    public void findAll() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> users = userDao.findAll(session);
        assertTrue(!users.isEmpty());

        session.getTransaction().commit();
    }

    @Test
    public void findAllByFirstName(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> users = userDao.findAllByFirstName(session, "Alex");
        assertTrue(!users.isEmpty());

        session.getTransaction().commit();
    }

    @Test
    public void findAllByCompanyName(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> users = userDao.findAllByCompanyName(session, "Yandex");
        assertTrue(!users.isEmpty());

        session.getTransaction().commit();
    }

    @Test
    public void findAveragePaymentAmountByFirstNameAndLastName(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var filter = PaymentFilter.builder()
                .firstName("Alex")
                .lastName("Dedul")
                .build();

        Double avg = userDao.findAveragePaymentAmountByFirstNameAndLastName(session, filter);
        assertTrue(avg > 0);

        session.getTransaction().commit();
    }
}
