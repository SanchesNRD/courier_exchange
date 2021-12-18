package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;
import by.epam.courierexchange.util.PasswordEncryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserServiceImpl userService;
    private User user;
    private User otherUser;
    private List<User> users;
    private List<User> otherUsers;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder()
                .setId(1)
                .setLogin("Login")
                .setName("Name")
                .setSurname("Surname")
                .setPassword(PasswordEncryption.encode("a12345678"))
                .setMail("mail@gmail.com")
                .setPhone("+375291234567")
                .setImage("avatar.png")
                .setUserStatus(UserStatus.CONFIRMED)
                .build();
        otherUser = new User.UserBuilder()
                .setId(2)
                .setLogin("OtherLogin")
                .setName("OtherName")
                .setSurname("OtherSurname")
                .setPassword(PasswordEncryption.encode("b12345678"))
                .setMail("OtherMail@gmail.com")
                .setPhone("+375291234567")
                .setImage("otherAvatar.png")
                .setUserStatus(UserStatus.CONFIRMED)
                .build();
        users = new ArrayList<>();
        users.add(user);
        users.add(otherUser);
        otherUsers = new ArrayList<>();
    }

    @Test
    void authorizationTrueTest() throws ServiceException {
        when(userService.authorization("Login", PasswordEncryption.encode("a12345678")))
                .thenReturn(Optional.of(user));
        Optional<User> actualUser = userService.authorization("Login", PasswordEncryption.encode("a12345678"));
        assertEquals(user, actualUser.get());
    }

    @Test
    void authorizationFalseTest() throws ServiceException {
        when(userService.authorization("Login", PasswordEncryption.encode("a12345678")))
                .thenReturn(Optional.of(user));
        Optional<User> actualUser = userService.authorization("Login", PasswordEncryption.encode("a12345678"));
        assertNotEquals(otherUser, actualUser.get());
    }

    @Test
    void registrationTrueTest() throws ServiceException {
        when(userService.registration("Login", PasswordEncryption.encode("a12345678"),
                "Name", "Surname", "maeil@gmail.com", "+375291234567")).thenReturn(1);
        int result = userService.registration("Login", PasswordEncryption.encode("a12345678"),
                "Name", "Surname", "maeil@gmail.com", "+375291234567");
        assertEquals(1, result);
    }

    @Test
    void registrationFalseTest() throws ServiceException {
        when(userService.registration("Login", PasswordEncryption.encode("a12345678"),
                "Name", "Surname", "maeil@gmail.com", "+375291234567")).thenReturn(1);
        int result = userService.registration("Login", PasswordEncryption.encode("a12345678"),
                "Name", "Surname", "maeil@gmail.com", "+375291234567");
        assertNotEquals(2, result);
    }

    @Test
    void updatePasswordTrueTest() throws ServiceException {
        when(userService.updatePassword("1", PasswordEncryption.encode("b12345678"))).thenReturn(true);
        boolean result = userService.updatePassword("1", PasswordEncryption.encode("b12345678"));
        assertTrue(result);
    }

    @Test
    void updatePasswordFalseTest() throws ServiceException {
        when(userService.updatePassword("1", PasswordEncryption.encode("b12345678"))).thenReturn(true);
        boolean result = userService.updatePassword("1", PasswordEncryption.encode("b12345678"));
        assertNotEquals(false, result);
    }

    @Test
    void selectByMailTrueTest() throws ServiceException {
        when(userService.selectByMail("mail@gmail.com")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userService.selectByMail("mail@gmail.com");
        assertEquals(user, actualUser.get());
    }

    @Test
    void selectByMailFalseTest() throws ServiceException {
        when(userService.selectByMail("mail@gmail.com")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userService.selectByMail("mail@gmail.com");
        assertNotEquals(otherUser, actualUser.get());
    }

    @Test
    void uploadImgPathTrueTest() throws ServiceException {
        when(userService.uploadImgPath(1, "otherImg.png")).thenReturn(true);
        boolean result = userService.uploadImgPath(1, "otherImg.png");
        assertTrue(result);
    }

    @Test
    void uploadImgPathFalseTest() throws ServiceException {
        when(userService.uploadImgPath(1, "otherImg.png")).thenReturn(true);
        boolean result = userService.uploadImgPath(1, "otherImg.png");
        assertNotEquals(false, result);
    }

    @Test
    void findImgPathTrueTest() throws ServiceException {
        when(userService.findImgPath(1)).thenReturn(user.getImage());
        String actualImg = userService.findImgPath(1);
        assertEquals("avatar.png", actualImg);
    }

    @Test
    void findImgPathFalseTest() throws ServiceException {
        when(userService.findImgPath(1)).thenReturn(user.getImage());
        String actualImg = userService.findImgPath(1);
        assertNotEquals("otherAvatar.png", actualImg);
    }

    @Test
    void changePasswordTrueTest() throws ServiceException {
        when(userService.changePassword(user, PasswordEncryption.encode("a12345678"),
                PasswordEncryption.encode("b12345678"))).thenReturn(Optional.of(otherUser));
        Optional<User> actualUser = userService.changePassword(user, PasswordEncryption.encode("a12345678"),
                PasswordEncryption.encode("b12345678"));
        assertEquals(otherUser, actualUser.get());
    }

    @Test
    void changePasswordFalseTest() throws ServiceException {
        when(userService.changePassword(user, PasswordEncryption.encode("a12345678"),
                PasswordEncryption.encode("b12345678"))).thenReturn(Optional.of(otherUser));
        Optional<User> actualUser = userService.changePassword(user, PasswordEncryption.encode("a12345678"),
                PasswordEncryption.encode("b12345678"));
        assertNotEquals(user, actualUser.get());
    }

    @Test
    void selectAllTrueTest() throws ServiceException {
        when(userService.selectAll()).thenReturn(users);
        List<User> actualUser = userService.selectAll();
        assertEquals(users, actualUser);
    }

    @Test
    void selectAllFalseTest() throws ServiceException {
        when(userService.selectAll()).thenReturn(users);
        List<User> actualUser = userService.selectAll();
        assertNotEquals(otherUsers, actualUser);
    }
}
