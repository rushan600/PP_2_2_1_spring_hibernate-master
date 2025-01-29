package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        String hql = "SELECT DISTINCT user FROM User user LEFT JOIN FETCH user.car";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public User getUserByCar(String model, int series) {
        String hql = "SELECT DISTINCT user FROM User user LEFT JOIN FETCH user.car car WHERE car.model = :model AND car.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.setMaxResults(1).getSingleResult();
    }
}