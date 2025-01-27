package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public List<User> getUserByModelAndSeries(String model, int series) {
      String  sql = "SELECT c.empUser FROM Car c WHERE c.model = : model AND c.series = : series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
      query.setParameter("model", model).setParameter("series", series);
      return query.getResultList();
   }
}
