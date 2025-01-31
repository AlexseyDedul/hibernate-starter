package by.alexdedul.hibernate;

import by.alexdedul.hibernate.entity.*;
import by.alexdedul.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
//        Company company = Company.builder()
//                .name("Meta")
//                .build();
//
//        User user = User.builder()
//                .username("ivanov2@gmail.com")
//                .personalInfo(PersonalInfo.builder()
//                        .firstname("Ivan")
//                        .lastname("Ivanov")
//                        .birthDate(new Birthday(LocalDate.of(2000, 1, 1)))
//                        .build())
//                .role(Role.ADMIN)
//                .company(company)
//                .build();
//
//        log.info("User object in transient state: {}", user);
//
//        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
//            try (Session session = sessionFactory.openSession()) {
//                session.beginTransaction();
//
//
////            session.save(user);
////            session.update(user);
//
////            session.saveOrUpdate(company);
//                Company company1 = session.get(Company.class, 1);
//                user.setCompany(company1);
//                session.saveOrUpdate( user);
////            session.delete(user);
////                User user1 = session.get(User.class, "admin");
////                System.out.println(user1);
//                session.getTransaction().commit();
//            }
//        }
    }
}
