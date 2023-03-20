package cinema.lib;

import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EmailValidatorTest {
    private EmailValidator emailValidator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
        context = Mockito.mock(ConstraintValidatorContext.class);
    }

    @Test
    void validateEmail_Ok() {
        String email = "bob@i.ua";
        Assertions.assertTrue(emailValidator.isValid(email, context));
    }

    @Test
    void validateEmails_invalidEmail_Ok() {
        String emailWithoutAt = "bobi.ua";
        String emailWithoutDot = "bob@iua";
        String emailWithEmptyStart = "@i.ua";
        String emailWithEmptyEnd = "bob@";
        Assertions.assertFalse(emailValidator.isValid(emailWithoutAt, context));
        Assertions.assertFalse(emailValidator.isValid(emailWithEmptyEnd, context));
        Assertions.assertFalse(emailValidator.isValid(emailWithoutDot, context));
        Assertions.assertFalse(emailValidator.isValid(emailWithEmptyStart, context));
    }
}