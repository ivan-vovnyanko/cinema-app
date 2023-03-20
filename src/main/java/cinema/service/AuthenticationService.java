package cinema.service;

import cinema.model.User;
import javax.naming.AuthenticationException;

public interface AuthenticationService {
    User register(String email, String password);

    User login(String email, String password) throws AuthenticationException;
}
