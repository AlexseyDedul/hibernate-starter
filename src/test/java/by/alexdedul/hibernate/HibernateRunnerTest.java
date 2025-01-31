package by.alexdedul.hibernate;

import by.alexdedul.hibernate.entity.Birthday;
import by.alexdedul.hibernate.entity.Role;
import by.alexdedul.hibernate.entity.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {
        User user = User.builder()
                .username("user")
                .firstname("User1")
                .lastname("User1")
                .birthDate(new Birthday(LocalDate.of(2000, 1, 1)))
                .role(Role.USER)
                .build();

        String sql = """
                insert into
                %s
                (%s)
                values
                (%s)
                """;

        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();
        String fieldNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                    .map(Column::name)
                    .orElse(field.getName())
                ).collect(Collectors.joining(", "));

        String columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres" ,"postgres");
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, fieldNames, columnValues));

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            preparedStatement.setObject(i + 1, fields[i].get(user));
        }

        System.out.println(preparedStatement);

        preparedStatement.executeUpdate();

        preparedStatement.close();

        connection.close();

    }
}