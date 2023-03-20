package cinema.lib;

import cinema.dto.request.UserRegisterRequestDto;
import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FieldsValueMatchValidatorTest {
    private static final String EMAIL = "demo@i.ua";
    private static final String PASSWORD = "DemoPassword";
    private static final String SALT = "SALT";
    private FieldsValueMatchValidator fieldsValueMatchValidator;
    private FieldsValueMatch constraintAnnotation;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        fieldsValueMatchValidator = new FieldsValueMatchValidator();
        context = Mockito.mock(ConstraintValidatorContext.class);
        constraintAnnotation = Mockito.mock(FieldsValueMatch.class);
        Mockito.when(constraintAnnotation.field()).thenReturn("password");
        Mockito.when(constraintAnnotation.fieldMatch()).thenReturn("repeatPassword");
        fieldsValueMatchValidator.initialize(constraintAnnotation);
    }

    @Test
    void validatePasswords_Ok() {
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        userRegisterRequestDto.setEmail(EMAIL);
        userRegisterRequestDto.setPassword(PASSWORD);
        userRegisterRequestDto.setRepeatPassword(PASSWORD);
        Assertions.assertTrue(fieldsValueMatchValidator.isValid(userRegisterRequestDto, context));
    }

    @Test
    void validatePassword_differentPasswords_NotOk() {
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        userRegisterRequestDto.setEmail(EMAIL);
        userRegisterRequestDto.setPassword(PASSWORD);
        userRegisterRequestDto.setRepeatPassword(PASSWORD + SALT);
        Assertions.assertFalse(fieldsValueMatchValidator.isValid(userRegisterRequestDto, context));
    }
}