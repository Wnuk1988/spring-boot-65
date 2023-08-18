package com.tms.repository;

import com.tms.domain.UserInfo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserRepository {
    public final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // CRUD  операции:

    //READ
    public UserInfo findById(int id) {
        UserInfo userInfo = null;
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserInfo> criteria = cb.createQuery(UserInfo.class);
        Root<UserInfo> root = criteria.from(UserInfo.class);
        criteria.select(root).where(cb.equal(root.get("id"),id));
        List<UserInfo> result = session.createQuery(criteria).getResultList();
        if (!result.isEmpty()){
            userInfo = result.get(0);
        }
        session.close();
        return userInfo;
    }

    // Read
    public List<UserInfo> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserInfo> criteria = cb.createQuery(UserInfo.class);
        criteria.select(criteria.from(UserInfo.class));
        List<UserInfo> resultList = session.createQuery(criteria).getResultList();
        session.close();
        return resultList;
    }

    // CREATE
    public void save(UserInfo userInfo) {
        //  не можем сохранить через Query, только если перенести из другой таблицы
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(userInfo);
        session.getTransaction().commit();
        session.close();
    }

    // UPDATE
    public void updateUser(UserInfo userInfo) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<UserInfo> criteria = cb.createCriteriaUpdate(UserInfo.class);
        Root<UserInfo> root = criteria.getRoot();

        criteria.set("firstName", userInfo.getFirstName());
        criteria.set("lastName",userInfo.getLastName());
        criteria.set("updatedAt", LocalDateTime.now());
        criteria.set("role", userInfo.getRole());
        criteria.where(cb.equal(root.get("id"),userInfo.getId()));

        session.beginTransaction();
        session.createMutationQuery(criteria).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    // DELETE объект передаем
    public void delete(UserInfo userInfo) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<UserInfo> criteria = cb.createCriteriaDelete(UserInfo.class);
        Root<UserInfo> root = criteria.getRoot();

        criteria.where(cb.equal(root.get("id"),userInfo.getId()));

        session.beginTransaction();
        session.createMutationQuery(criteria).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
