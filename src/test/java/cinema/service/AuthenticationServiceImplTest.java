package cinema.service;

import static org.mockito.ArgumentMatchers.any;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.impl.AuthenticationServiceImpl;
import java.util.Optional;
import java.util.Set;
import javax.naming.AuthenticationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthenticationServiceImplTest {
    private static final String EMAIL = "demo@i.ua";
    private static final String PASSWORD = "DemoPassword123";
    private AuthenticationService authenticationService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        shoppingCartService = Mockito.mock(ShoppingCartService.class);
        roleService = Mockito.mock(RoleService.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        authenticationService = new AuthenticationServiceImpl(userService, shoppingCartService,
                roleService, passwordEncoder);
    }

    @Test
    void loginUser_Ok() throws AuthenticationException {
        User user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        Mockito.when(userService.findByEmail(any())).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(any(), any())).thenReturn(true);
        Assertions.assertEquals(user, authenticationService.login(EMAIL, PASSWORD));
    }

    @Test
    void registerNewUser_Ok() {
        Role role = new Role();
        role.setRoleName(Role.RoleName.USER);
        User user = new User();
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        user.setRoles(Set.of(role));
        Mockito.when(roleService.getByName(Role.RoleName.USER)).thenReturn(role);
        Mockito.when(userService.add(user)).thenReturn(user);
        Assertions.assertEquals(user, authenticationService.register(EMAIL, PASSWORD));
    }
}