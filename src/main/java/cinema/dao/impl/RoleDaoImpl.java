package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    private static final Logger logger = LogManager.getLogger(RoleDao.class);

    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(Role.RoleName roleName) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM Role r "
                            + "WHERE r.roleName = :roleName", Role.class)
                    .setParameter("roleName", roleName).getSingleResult());
        } catch (Exception e) {
            logger.error("Can't get role by name " + roleName);
            throw new DataProcessingException("Can't get role by name " + roleName, e);
        }
    }
}
