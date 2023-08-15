package com.tms.repository;

import com.tms.domain.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

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
        Session session = sessionFactory.openSession();
        UserInfo userInfo = session.find(UserInfo.class,id);
        session.close();
        return userInfo;
    }

    //Read HQL
    public List<UserInfo> findAll() {
        Session session = sessionFactory.openSession();
        Query<UserInfo> query = session.createQuery("from user_info", UserInfo.class);
        List<UserInfo> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    // CREATE
    public void save(UserInfo userInfo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(userInfo);
        session.getTransaction().commit();
        session.close();
    }

    //UPDATE
    public void updateUser(UserInfo userInfo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(userInfo);
        session.getTransaction().commit();
        session.close();
    }

    // DELETE объект передаем
    public void delete(UserInfo userInfo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(userInfo);
        session.getTransaction().commit();
        session.close();
    }
}
