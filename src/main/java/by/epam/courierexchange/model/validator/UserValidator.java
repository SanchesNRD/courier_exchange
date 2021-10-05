package by.epam.courierexchange.model.validator;

public class UserValidator {
    private static final String LOGIN_PATTERN = "(?=.*[a-z])[\\w]{4,20}";
    private static final String PASSWORD_PATTER = "(?=.*[\\d])(?=.*[a-z])[\\w]{8,40}";
    private static final String PHONE_PATTERN = "(\\+375|80)(29|25|44|33)[\\d]{7}";
    private static final String EMAIL_PATTERN =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String NAME_PATTERN = "[a-zA-Zа-яА-я]{2,20}";
    private static final String SURNAME_PATTERN = "[a-zA-Zа-яА-я]{2,20}";
    private static final String NUMBER_PATTERN = "[+]?\\d+";
    private static final String TYPE_PATTERN = "[1234]{1}";

    public static boolean loginIsValid(String login){
        return login != null && login.matches(LOGIN_PATTERN);
    }

    public static boolean typeIsValid(String login){
        return login != null && login.matches(TYPE_PATTERN);
    }

    public static boolean numberIsValid(String login){
        return login != null && login.matches(NUMBER_PATTERN);
    }

    public static boolean passwordIsValid(String password){
        return password != null && password.matches(PASSWORD_PATTER);
    }

    public static boolean phoneIsValid(String phone){
        return phone != null && phone.matches(PHONE_PATTERN);
    }

    public static boolean emailIsValid(String email){
        return email != null && email.matches(EMAIL_PATTERN);
    }

    public static boolean nameIsValid(String name){
        return name != null && name.matches(NAME_PATTERN);
    }

    public static boolean surnameIsValid(String surname){
        return surname != null && surname.matches(SURNAME_PATTERN);
    }
}
