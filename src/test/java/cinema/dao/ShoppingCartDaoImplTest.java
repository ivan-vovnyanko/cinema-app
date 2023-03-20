package cinema.dao;

import cinema.dao.impl.RoleDaoImpl;
import cinema.dao.impl.ShoppingCartDaoImpl;
import cinema.dao.impl.UserDaoImpl;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.Role;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.User;
import java.util.ArrayList;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartDaoImplTest extends AbstractTest {
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private RoleDao roleDao;
    private User user;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl(getSessionFactory());
        shoppingCartDao = new ShoppingCartDaoImpl(getSessionFactory());
        roleDao = new RoleDaoImpl(getSessionFactory());
        Role role = new Role();
        role.setRoleName(Role.RoleName.USER);
        roleDao.add(role);
        user = new User();
        user.setEmail("demo@i.ua");
        user.setPassword("DemoPassword123");
        user.setRoles(Set.of(role));
        userDao.add(user);
    }

    @Test
    void getShoppingCartByUser_Ok() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.add(shoppingCart);
        Assertions.assertEquals(shoppingCart, shoppingCartDao.getByUser(user));
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {ShoppingCart.class, User.class, Ticket.class, Role.class,
                MovieSession.class, Movie.class, CinemaHall.class};
    }
}