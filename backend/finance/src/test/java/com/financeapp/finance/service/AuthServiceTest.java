package com.financeapp.finance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.financeapp.finance.dto.LoginDTO;
import com.financeapp.finance.exception.InvalidCredentialsException;
import com.financeapp.finance.model.User;
import com.financeapp.finance.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private User testUser;
    private LoginDTO loginDTO;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUserId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("encodedPassword");

        loginDTO = new LoginDTO();
        loginDTO.setUsername("testuser");
        loginDTO.setPassword("rawPassword");
    }

    @Test
    void loginUser_Success() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("rawPassword", "encodedPassword")).thenReturn(true);

        User result = authService.loginUser(loginDTO);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepo).findByUsername("testuser");
        verify(passwordEncoder).matches("rawPassword", "encodedPassword");
    }

    @Test
    void loginUser_UserNotFound_ThrowException() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> authService.loginUser(loginDTO));
        verify(userRepo).findByUsername("testuser");
    }

    @Test
    void loginUser_WrongPassword_ThrowException() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("rawPassword", "encodedPassword")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> authService.loginUser(loginDTO));
        verify(userRepo).findByUsername("testuser");
        verify(passwordEncoder).matches("rawPassword", "encodedPassword");
    }
}
