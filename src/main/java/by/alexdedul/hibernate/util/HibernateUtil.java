package by.alexdedul.hibernate.util;

import by.alexdedul.hibernate.converter.BirthdayConverter;
import by.alexdedul.hibernate.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Profile.class);
        configuration.addAnnotatedClass(Chat.class);
        configuration.addAnnotatedClass(UserChat.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        return configuration.buildSessionFactory();
    }
}
