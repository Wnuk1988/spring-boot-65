package com.tms.repository;

import com.tms.domain.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // CRUD  операции

    // CREATE
    public void save(UserInfo userInfo) {
        entityManager.getTransaction().begin();
        entityManager.persist(userInfo);
        entityManager.getTransaction().commit();
    }

    // DELETE
    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(UserInfo.class, id));
        entityManager.getTransaction().commit();
    }

    //READ
    public UserInfo findById(int id) {
        UserInfo userInfo = entityManager.find(UserInfo.class, id);
        return userInfo;
    }

    public List<UserInfo> findAll() {
        Query query = entityManager.createQuery("FROM user_info");
        return query.getResultList();
    }

    //UPDATE
    public void updateUser(UserInfo userInfo) {
        entityManager.getTransaction().begin();
        entityManager.merge(userInfo);
        entityManager.getTransaction().commit();
    }
}
