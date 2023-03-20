package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.UserDao;
import cinema.exception.DataProcessingException;
import cinema.model.User;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDao.class);

    public UserDaoImpl(SessionFactory factory) {
        super(factory, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM User u INNER JOIN FETCH u.roles "
                            + "WHERE u.email = :email", User.class)
                    .setParameter("email", email).uniqueResultOptional();
        } catch (Exception e) {
            logger.error("User with email " + email + " not found");
            throw new DataProcessingException("User with email " + email + " not found", e);
        }
    }
}
