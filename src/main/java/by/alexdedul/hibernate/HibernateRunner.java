package by.alexdedul.hibernate;

import by.alexdedul.hibernate.dao.CompanyRepository;
import by.alexdedul.hibernate.dao.PaymentRepository;
import by.alexdedul.hibernate.dao.UserRepository;
import by.alexdedul.hibernate.dto.PaymentFilter;
import by.alexdedul.hibernate.dto.UserCreateDto;
import by.alexdedul.hibernate.entity.*;
import by.alexdedul.hibernate.mapper.CompanyReadMapper;
import by.alexdedul.hibernate.mapper.UserCreateMapper;
import by.alexdedul.hibernate.mapper.UserReadMapper;
import by.alexdedul.hibernate.service.UserService;
import by.alexdedul.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {

        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            session.beginTransaction();

            CompanyReadMapper companyReadMapper = new CompanyReadMapper();
            UserReadMapper userReadMapper = new UserReadMapper(companyReadMapper);
            CompanyRepository companyRepository = new CompanyRepository(session);
            UserCreateMapper userCreateMapper = new UserCreateMapper(companyRepository);

            UserRepository userRepository = new UserRepository(session);
            UserService userService = new UserService(userRepository, userReadMapper, userCreateMapper);

            userService.findUserById(1L).ifPresent(System.out::println);

            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstname("Alex")
                            .lastname("Dul")
//                            .birthDate(new Birthday(LocalDate.now()))
                            .build(),
                    "alexDul11@gmail.com",
                    Role.USER,
                    1
            );

            System.out.println(userService.createUser(userCreateDto));

            session.getTransaction().commit();
        }

    }
}
