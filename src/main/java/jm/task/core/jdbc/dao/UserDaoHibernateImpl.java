//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//
//public class UserDaoHibernateImpl implements UserDao {
//    private Session sessionFactory;
//
//
//    public UserDaoHibernateImpl(Session sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public void createUsersTable() {
//        try (Session session = sessionFactory.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
//                    "id SERIAL PRIMARY KEY, " +
//                    "name VARCHAR(255), " +
//                    "last_name VARCHAR(255), " +
//                    "age TINYINT)";
//            session.createSQLQuery(createTableSQL).executeUpdate();
//            transaction.commit();
//            System.out.println("Table created");
//        }
//    }
//
//    @Override
//    public void dropUsersTable() {
//        try (Session session = sessionFactory.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            String dropTableSQL = "DROP TABLE IF EXISTS users";
//            session.createSQLQuery(dropTableSQL).executeUpdate();
//            transaction.commit();
//            System.out.println("Table dropped");
//        }
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        try (Session session = sessionFactory.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            User user = new User(name, lastName, age);
//            session.save(user);
//            transaction.commit();
//            System.out.println("User с именем " + name + " добавлен в базу данных.\n");
//        }
//    }
//
//    @Override
//    public void removeUserById(long id) {
//        try (Session session = sessionFactory.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            User user = (User) session.load(User.class, id);
//            if (user != null) {
//                session.delete(user);
//            }
//            transaction.commit();
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        try (Session session = sessionFactory.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Query query = session.createQuery("FROM User");
//            return query.list();
//        }
//    }
//
//    @Override
//    public void cleanUsersTable() {
//        try (Session session = sessionFactory.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.createQuery("delete from User").executeUpdate();
//            transaction.commit();
//            System.out.println("Table cleaned");
//        }
//    }
//}
