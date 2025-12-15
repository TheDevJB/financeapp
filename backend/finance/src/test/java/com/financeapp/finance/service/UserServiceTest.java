package com.financeapp.finance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.financeapp.finance.exception.EmailAlreadyExistsException;
import com.financeapp.finance.exception.EmailDoesNotExistException;
import com.financeapp.finance.exception.InvalidCredentialsException;
import com.financeapp.finance.exception.UserDoesNotExistException;
import com.financeapp.finance.exception.UsernameAlreadyExistsException;
import com.financeapp.finance.model.User;
import com.financeapp.finance.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUserId(1L);
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword");
        testUser.setPhone("123-456-7890");
    }

    @Test
    void getUserById_WhenExists_ReturnUser() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(testUser));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        verify(userRepo).findById(1L);
    }

    @Test
    void getUserById_WhenNotExists_ThrowException() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserDoesNotExistException.class, () -> userService.getUserById(1L));
    }

    @Test
    void getUserByUsername_WhenExists_ReturnUser() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(testUser));

        User result = userService.getUserByUsername("testuser");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void getUserByUsername_WhenNotExists_ThrowException() {
        when(userRepo.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UserDoesNotExistException.class, () -> userService.getUserByUsername("unknown"));
    }

    @Test
    void getUserByEmail_WhenExists_ReturnUser() {
        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));

        User result = userService.getUserByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void getUserByEmail_WhenNotExists_ThrowException() {
        when(userRepo.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        assertThrows(EmailDoesNotExistException.class, () -> userService.getUserByEmail("unknown@example.com"));
    }

    @Test
    void createUser_Success() {
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepo.findByUsername(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepo.save(any(User.class))).thenReturn(testUser);

        User result = userService.createUser("John", "Doe", "newuser", "new@example.com", "password", "123456");

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(userRepo).save(any(User.class));
    }

    @Test
    void createUser_EmailExists_ThrowException() {
        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));

        assertThrows(EmailAlreadyExistsException.class,
                () -> userService.createUser("John", "Doe", "newuser", "test@example.com", "password", "123456"));
    }

    @Test
    void createUser_UsernameExists_ThrowException() {
        when(userRepo.findByEmail("new@example.com")).thenReturn(Optional.empty());
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(testUser));

        assertThrows(UsernameAlreadyExistsException.class,
                () -> userService.createUser("John", "Doe", "testuser", "new@example.com", "password", "123456"));
    }

    @Test
    void setPassword_Success() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.encode("newPassword")).thenReturn("newEncodedPassword");
        when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.setPassword("testuser", "newPassword");

        assertEquals("newEncodedPassword", result.getPassword());
        verify(userRepo).save(testUser);
    }

    @Test
    void validatePassword_Success() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("rawPassword", "encodedPassword")).thenReturn(true);

        User result = userService.validatePassword("testuser", "rawPassword");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void validatePassword_InvalidCredentials_ThrowException() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class,
                () -> userService.validatePassword("testuser", "wrongPassword"));
    }
}
