package com.lab3.database;

import com.lab3.accounts.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserProfile getUserByLogin(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return (UserProfile) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public void insertUser(UserProfile userProfile) throws HibernateException {
        session.save(userProfile);
    }
}
