package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.ShoppingCartDao;
import cinema.exception.DataProcessingException;
import cinema.model.ShoppingCart;
import cinema.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl extends AbstractDao<ShoppingCart> implements ShoppingCartDao {
    private static final Logger logger = LogManager.getLogger(ShoppingCartDao.class);

    public ShoppingCartDaoImpl(SessionFactory factory) {
        super(factory, ShoppingCart.class);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = factory.openSession()) {
            Query<ShoppingCart> getByUser = session.createQuery(
                    "SELECT DISTINCT sc FROM ShoppingCart sc "
                            + "left join fetch sc.tickets t "
                            + "left join fetch t.movieSession ms "
                            + "left join fetch ms.cinemaHall "
                            + "left join fetch ms.movie "
                            + "left join fetch sc.user u "
                            + "left join fetch u.roles "
                            + "WHERE u = :user", ShoppingCart.class);
            getByUser.setParameter("user", user);
            return getByUser.getSingleResult();
        } catch (Exception e) {
            logger.error("Not found shopping cart for user " + user);
            throw new DataProcessingException("Not found shopping cart for user " + user, e);
        }
    }
}
