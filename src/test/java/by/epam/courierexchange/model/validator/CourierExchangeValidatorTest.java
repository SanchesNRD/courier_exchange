package by.epam.courierexchange.model.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CourierExchangeValidatorTest {
    private String validLogin;
    private String invalidLogin;
    private String validNumber;
    private String zero;
    private String validPassword;
    private String invalidPassword;
    private String validMail;
    private String validPhone;
    private String invalidPhone;

    @BeforeEach
    public void setUp() {
        validLogin = "Login";
        invalidLogin = "No";
        validNumber = "12";
        zero = "0";
        validPassword = "a12345678";
        invalidPassword = "pass";
        validMail = "courier_exchange@gmail.com";
        validPhone = "+375291234567";
        invalidPhone = "+375991234567";

    }

    @Test
    void loginIsInvalidTest() {
        boolean result = CourierExchangeValidator.loginIsInvalid(invalidLogin);
        assertTrue(result);
    }

    @Test
    void loginIsInvalidTrueLoginTest() {
        boolean result = CourierExchangeValidator.loginIsInvalid(validLogin);
        assertFalse(result);
    }

    @Test
    void numberIsInvalidTrueNumberTest() {
        boolean result = CourierExchangeValidator.numberIsInvalid(validNumber);
        assertFalse(result);
    }

    @Test
    void numberIsInvalidTest() {
        boolean result = CourierExchangeValidator.numberIsInvalid(zero);
        assertTrue(result);
    }

    @Test
    void passwordIsInvalidTest() {
        boolean result = CourierExchangeValidator.passwordIsInvalid(invalidPassword);
        assertTrue(result);
    }

    @Test
    void passwordIsInvalidTruePasswordTest() {
        boolean result = CourierExchangeValidator.passwordIsInvalid(validPassword);
        assertFalse(result);
    }

    @Test
    void emailIsInvalidTest() {
        boolean result = CourierExchangeValidator.emailIsInvalid(validLogin);
        assertTrue(result);
    }

    @Test
    void emailIsInvalidTrueEmailTest() {
        boolean result = CourierExchangeValidator.emailIsInvalid(validMail);
        assertFalse(result);
    }

    @Test
    void phoneIsInvalidTest() {
        boolean result = CourierExchangeValidator.phoneIsInvalid(invalidPhone);
        assertTrue(result);
    }

    @Test
    void phoneIsInvalidTruePhoneTest() {
        boolean result = CourierExchangeValidator.phoneIsInvalid(validPhone);
        assertFalse(result);
    }
}
