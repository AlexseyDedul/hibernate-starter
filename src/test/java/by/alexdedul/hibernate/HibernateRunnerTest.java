package by.alexdedul.hibernate;

import by.alexdedul.hibernate.entity.*;
import by.alexdedul.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

@SuppressWarnings("unchecked")
class HibernateRunnerTest {

    @Test
    public void checkHQL() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder()
                .name("Google")
                .build();
        session.save(company);

        User user = User.builder()
                .username("alex")
                .build();
        user.setCompany(company);
        session.save(user);

        User user1 = User.builder()
                .username("alex1")
                .build();
        user1.setCompany(company);
        session.save(user1);

        session.createQuery("update User u set u.role = 'ADMIN'").executeUpdate();

        session.createNamedQuery("findUserByNameAndCompany")
                .setParameter("company", "Google")
                .setParameter("username", "alex1")
                .list()
                .forEach(System.out::println);


        session.getTransaction().commit();
    }

    @Test
    public void testManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Chat chat = Chat.builder()
                .name("Chat")
                .build();

        User user = User.builder()
                .username("alexdedul")
                .build();
        session.save(chat);
        session.save(user);

//        UserChat userChat = UserChat.builder()
//                .createdAt(Instant.now())
//                .createdBy("alexdedul")
//                .build();

        UserChat userChat = new UserChat();

        userChat.setCreatedAt(Instant.now());
        userChat.setCreatedBy("alexdedul");

        userChat.setUser(user);
        userChat.setChat(chat);

        session.saveOrUpdate(userChat);

        session.getTransaction().commit();
    }

    @Test
    public void testOneToOne(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        User user = User.builder()
                .username("ivanov4@gmail.com")
                .build();

        Profile profile = Profile.builder()
                .language("ru")
                .street("Tay")
                .build();

        session.save(user);

        profile.setUser(user);

        session.save(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkOrphalRemoval(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Google")
                .build();
        session.save(company);
        company.getUsers().removeIf(user -> user.getId() == 2);

        session.getTransaction().commit();
    }

    @Test
    public void addUserAndCompany(){

        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = Company.builder()
                .name("Yandex")
                .build();

        User user = User.builder()
                .username("ivanov3@gmail.com")
                .build();

        company.addUser(user);
        session.save(company);


        session.getTransaction().commit();
    }

    @Test
    public void testOneToMany(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Google")
                .build();
        session.save(company);
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }

    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {
//        User user = User.builder()
//                .username("user")
//                .personalInfo(PersonalInfo.builder()
//                        .firstname("User1")
//                        .lastname("User1")
//                        .birthDate(new Birthday(LocalDate.of(2000, 1, 1)))
//                        .build())
//                .role(Role.USER)
//                .build();
//
//        String sql = """
//                insert into
//                %s
//                (%s)
//                values
//                (%s)
//                """;
//
//        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(table -> table.schema() + "." + table.name())
//                .orElse(user.getClass().getName());
//
//        Field[] fields = user.getClass().getDeclaredFields();
//        String fieldNames = Arrays.stream(fields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                    .map(Column::name)
//                    .orElse(field.getName())
//                ).collect(Collectors.joining(", "));
//
//        String columnValues = Arrays.stream(fields)
//                .map(field -> "?")
//                .collect(Collectors.joining(", "));
//
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres" ,"postgres");
//        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, fieldNames, columnValues));
//
//        for (int i = 0; i < fields.length; i++) {
//            fields[i].setAccessible(true);
//            preparedStatement.setObject(i + 1, fields[i].get(user));
//        }
//
//        System.out.println(preparedStatement);
//
//        preparedStatement.executeUpdate();
//
//        preparedStatement.close();
//
//        connection.close();

    }
}