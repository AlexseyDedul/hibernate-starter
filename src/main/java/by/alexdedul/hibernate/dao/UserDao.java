package by.alexdedul.hibernate.dao;

import by.alexdedul.hibernate.dto.PaymentFilter;
import by.alexdedul.hibernate.entity.QPayment;
import by.alexdedul.hibernate.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static by.alexdedul.hibernate.entity.QCompany.*;
import static by.alexdedul.hibernate.entity.QUser.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {

        return new JPAQuery<User>(session).select(user).from(user).fetch();
    }

    public List<User> findAllByFirstName(Session session, String firstName) {

        return new JPAQuery<User>(session).select(user).from(user)
                .where(user.personalInfo().firstname.eq(firstName)).fetch();
    }

    public List<User> findAllByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session).select(user).from(company)
                .join(company.users, user)
                .where(company.name.eq(companyName))
                .fetch();
    }

    public Double findAveragePaymentAmountByFirstNameAndLastName(Session session, PaymentFilter filter) {
        /*List<Predicate> predicates = new ArrayList<>();
        if(filter.getFirstName() != null) {
            predicates.add(user.personalInfo().firstname.eq(filter.getFirstName()));
        }
        if(filter.getLastName() != null) {
            predicates.add(user.personalInfo().lastname.eq(filter.getLastName()));
        }*/

        var qPredicate = QPredicate.builder()
                .add(filter.getFirstName(), user.personalInfo().firstname::eq)
                .add(filter.getLastName(), user.personalInfo().lastname::eq)
                .buildAnd();

        return new JPAQuery<Double>(session)
                .select(QPayment.payment.amount.avg())
                .from(QPayment.payment)
                .join(QPayment.payment.receiverId(), user)
                .where(qPredicate)
                .fetchOne();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
