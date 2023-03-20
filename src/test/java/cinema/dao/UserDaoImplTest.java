package cinema.dao;

import cinema.dao.impl.RoleDaoImpl;
import cinema.dao.impl.UserDaoImpl;
import cinema.model.Role;
import cinema.model.User;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDaoImplTest extends AbstractTest {
    private static final String EMAIL = "demo@i.ua";
    private static final String PASSWORD = "DemoPassword123";
    private UserDao userDao;
    private RoleDao roleDao;

    @BeforeEach
    void setUp() {
        roleDao = new RoleDaoImpl(getSessionFactory());
        Role role = new Role();
        role.setRoleName(Role.RoleName.USER);
        roleDao.add(role);
        userDao = new UserDaoImpl(getSessionFactory());
    }

    @Test
    void findUserByEmail_Ok() {
        User user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRoles(Set.of(roleDao.getByName(Role.RoleName.USER).get()));
        userDao.add(user);
        Assertions.assertEquals(user, userDao.findByEmail(EMAIL).get());
    }

    @Test
    void findUserByEmail_RandomEmail_NotOk() {
        Assertions.assertThrows(NoSuchElementException.class, () ->
                userDao.findByEmail("bob@i.ua").get());
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {User.class, Role.class};
    }
}