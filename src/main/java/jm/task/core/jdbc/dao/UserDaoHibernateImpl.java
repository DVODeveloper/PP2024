package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;


    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void createUsersTable() {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "last_name VARCHAR(255), " +
                    "age TINYINT)";
            session.createSQLQuery(createTableSQL).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            String dropTableSQL = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(dropTableSQL).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userArrayList = new ArrayList<>();
        try (Session session = openSession()) {
            userArrayList = session.createQuery("FROM User", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return userArrayList;
        }
        return userArrayList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
