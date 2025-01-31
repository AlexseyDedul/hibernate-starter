package by.alexdedul.hibernate;

import by.alexdedul.hibernate.converter.BirthdayConverter;
import by.alexdedul.hibernate.entity.Birthday;
import by.alexdedul.hibernate.entity.Role;
import by.alexdedul.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAttributeConverter(new BirthdayConverter(), true);
        configuration.addAnnotatedClass(User.class);
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession()){
            session.beginTransaction();
            User user = User.builder()
                    .username("admin")
                    .firstname("Alex")
                    .lastname("Dedul")
                    .birthDate(new Birthday(LocalDate.of(2000, 1, 1)))
                    .role(Role.ADMIN)
                    .build();

//            session.save(user);
//            session.update(user);
//            session.saveOrUpdate(user);
//            session.delete(user);
            User user1 = session.get(User.class, "admin");
            System.out.println(user1);
            session.getTransaction().commit();
        }
    }
}
