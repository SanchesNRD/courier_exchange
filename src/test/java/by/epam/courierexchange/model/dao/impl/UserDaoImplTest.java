package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
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
public class UserDaoImplTest {
    @Mock
    private UserDaoImpl userDao;
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
                .setPassword(PasswordEncryption.encode("a12345678"))
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
    void selectAllTrueTest() throws DaoException {
        when(userDao.selectAll()).thenReturn(users);
        List<User> actualUsers = userDao.selectAll();
        assertEquals(users, actualUsers);
    }

    @Test
    void selectAllFalseTest() throws DaoException {
        when(userDao.selectAll()).thenReturn(users);
        List<User> actualUsers = userDao.selectAll();
        assertNotEquals(otherUsers, actualUsers);
    }

    @Test
    void selectAllNotNullTest() throws DaoException {
        when(userDao.selectAll()).thenReturn(users);
        List<User> actualUsers = userDao.selectAll();
        assertNotNull(actualUsers);
    }

    @Test
    void selectByIdTrueTest() throws DaoException {
        when(userDao.selectById(1L)).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.selectById(1L);
        assertEquals(user, actualUser.get());
    }

    @Test
    void selectByIdFalseTest() throws DaoException {
        when(userDao.selectById(1L)).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.selectById(1L);
        assertNotEquals(otherUser, actualUser.get());
    }

    @Test
    void selectByLoginTrueTest() throws DaoException {
        when(userDao.selectByLogin("login")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.selectByLogin("login");
        assertEquals(user, actualUser.get());
    }

    @Test
    void selectByLoginFalseTest() throws DaoException {
        when(userDao.selectByLogin("login")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.selectByLogin("login");
        assertNotEquals(otherUser, actualUser.get());
    }

    @Test
    void selectByMailTrueTest() throws DaoException {
        when(userDao.selectByMail("mail@gmail.com")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.selectByMail("mail@gmail.com");
        assertEquals(user, actualUser.get());
    }

    @Test
    void selectByMailFalseTest() throws DaoException {
        when(userDao.selectByMail("mail@gmail.com")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.selectByMail("mail@gmail.com");
        assertNotEquals(otherUser, actualUser.get());
    }

    @Test
    void deleteByIdTrueTest() throws DaoException {
        when(userDao.deleteById(1L)).thenReturn(true);
        boolean result = userDao.deleteById(1L);
        assertTrue(result);
    }

    @Test
    void deleteByIdFalseTest() throws DaoException {
        when(userDao.deleteById(1L)).thenReturn(true);
        boolean result = userDao.deleteById(1L);
        assertNotEquals(false, result);
    }

    @Test
    void createTrueTest() throws DaoException {
        when(userDao.create(user)).thenReturn(1);
        int result = userDao.create(user);
        assertEquals(1, result);
    }

    @Test
    void createFalseTest() throws DaoException {
        when(userDao.create(user)).thenReturn(1);
        int result = userDao.create(user);
        assertNotEquals(2, result);
    }

    @Test
    void updateTrueTest() throws DaoException {
        when(userDao.update(user)).thenReturn(1);
        int result = userDao.update(user);
        assertEquals(1, result);
    }

    @Test
    void updateFalseTest() throws DaoException {
        when(userDao.update(user)).thenReturn(1);
        int result = userDao.update(user);
        assertNotEquals(2, result);
    }

    @Test
    void updatePasswordByIdTrueTest() throws DaoException {
        when(userDao.updatePasswordById(1L, PasswordEncryption.encode("b1234567"))).thenReturn(1);
        int result = userDao.updatePasswordById(1L, PasswordEncryption.encode("b1234567"));
        assertEquals(1, result);
    }

    @Test
    void updatePasswordByIdFalseTest() throws DaoException {
        when(userDao.updatePasswordById(1L, PasswordEncryption.encode("b1234567"))).thenReturn(1);
        int result = userDao.updatePasswordById(1L, PasswordEncryption.encode("b1234567"));
        assertNotEquals(2, result);
    }

    @Test
    void uploadImgPathTrueTest() throws DaoException {
        when(userDao.uploadImgPath(1L, "otherImg.png")).thenReturn(true);
        boolean result = userDao.uploadImgPath(1L, "otherImg.png");
        assertTrue(result);
    }

    @Test
    void uploadImgPathFalseTest() throws DaoException {
        when(userDao.uploadImgPath(1L, "otherImg.png")).thenReturn(true);
        boolean result = userDao.uploadImgPath(1L, "otherImg.png");
        assertNotEquals(false, result);
    }

    @Test
    void updateStatusTrueTest() throws DaoException {
        when(userDao.updateStatus(1L, UserStatus.COURIER_CONFIRMED)).thenReturn(1);
        int result = userDao.updateStatus(1L, UserStatus.COURIER_CONFIRMED);
        assertEquals(1, result);
    }

    @Test
    void updateStatusFalseTest() throws DaoException {
        when(userDao.updateStatus(1L, UserStatus.COURIER_CONFIRMED)).thenReturn(1);
        int result = userDao.updateStatus(1L, UserStatus.COURIER_CONFIRMED);
        assertNotEquals(2, result);
    }

    @Test
    void findImgPathTrueTest() throws DaoException {
        when(userDao.findImgPath(1L)).thenReturn(Optional.of(user.getImage()));
        Optional<String> actualImg = userDao.findImgPath(1L);
        assertEquals(user.getImage(), actualImg.get());
    }

    @Test
    void findImgPathFalseTest() throws DaoException {
        when(userDao.findImgPath(1L)).thenReturn(Optional.of(user.getImage()));
        Optional<String> actualImg = userDao.findImgPath(1L);
        assertNotEquals(otherUser.getImage(), actualImg.get());
    }
}
