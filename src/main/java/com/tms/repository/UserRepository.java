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
        int firstElement = 0;
        UserInfo userInfo = null;
        Session session = sessionFactory.openSession();
        Query<UserInfo> query = session.createQuery("FROM user_info u WHERE u.id =: userId", UserInfo.class);
        query.setParameter("userId", id);
        List<UserInfo> resultList = query.getResultList();
        session.close();
        if (resultList != null && !resultList.isEmpty()){
            userInfo = resultList.get(firstElement);
        }
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
        //  не можем сохранить через Query, только если перенести из другой таблицы
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(userInfo);
        session.getTransaction().commit();
        session.close();
    }

    //UPDATE
    public void updateUser(UserInfo userInfo) {
        Session session = sessionFactory.openSession();
        Query<UserInfo> query = session.createQuery("UPDATE user_info set firstName=:firstName, lastName=:lastName," +
                "updatedAt=:updatedAt, role=:role WHERE id=:id");
        query.setParameter("id", userInfo.getId());
        query.setParameter("firstName", userInfo.getFirstName());
        query.setParameter("lastName", userInfo.getLastName());
        query.setParameter("updatedAt", userInfo.getUpdatedAt());
        query.setParameter("role", userInfo.getRole());

        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    // DELETE объект передаем
    public void delete(UserInfo userInfo) {
        Session session = sessionFactory.openSession();
        Query<UserInfo> query = session.createQuery("DELETE user_info WHERE id=:id");
        query.setParameter("id", userInfo.getId());
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
