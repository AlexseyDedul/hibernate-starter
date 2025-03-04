package by.alexdedul.hibernate.dao;

import by.alexdedul.hibernate.entity.User;

import javax.persistence.EntityManager;

public class UserRepository extends BaseRepository<Long, User>{
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
