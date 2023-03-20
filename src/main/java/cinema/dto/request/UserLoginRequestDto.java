package cinema.dto.request;

public class UserLoginRequestDto {
    private String email;
    private String password;

    public UserLoginRequestDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
