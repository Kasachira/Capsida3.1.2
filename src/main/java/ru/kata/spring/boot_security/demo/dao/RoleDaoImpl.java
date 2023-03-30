package ru.kata.spring.boot_security.demo.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getDBRole(String role) {
        Query q = (Query) entityManager.createQuery(
                "select u from Role u where u.role = :role");
        q.setParameter("role", role);

        try {
            return (Role) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void addDBRole(Role role) {
        entityManager.persist(role);
    }

}
