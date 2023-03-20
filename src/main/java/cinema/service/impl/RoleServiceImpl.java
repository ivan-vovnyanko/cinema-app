package cinema.service.impl;

import cinema.dao.RoleDao;
import cinema.model.Role;
import cinema.service.OrderService;
import cinema.service.RoleService;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LogManager.getLogger(OrderService.class);
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getByName(Role.RoleName roleName) {
        return roleDao.getByName(roleName).orElseThrow(() -> {
            logger.error("Can't find role by name " + roleName);
            throw new NoSuchElementException("Can't find role by name " + roleName); });
    }
}
