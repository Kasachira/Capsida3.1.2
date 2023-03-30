package ru.kata.spring.boot_security.demo.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
    @Override
    public User getUserById(Long id) {

        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        Query q = (Query) entityManager.createQuery(
                "select u from User u where u.name = :name");
        q.setParameter("name", name);

        try {
            return (User) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("select u from User as u").getResultList();
    }

    @Override
    public void del(User user) {
        entityManager.remove(entityManager.contains(user)
                ? user
                : entityManager.merge(user));
    }
}

