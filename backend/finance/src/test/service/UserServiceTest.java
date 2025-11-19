
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.financeapp.finance.model.User;
import com.financeapp.finance.repositories.UserRepository;
import com.financeapp.finance.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setUserId(1);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPhone("000-000-000");
        testUser.setPassword("jimbrown1");
    }

    @Test
    void getUserById_WhenUserExists_ReturnUser() {
        when(userRepo.findById(1)).thenReturn(Optional.of(testUser));

        User result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals("testuser", result.getUsername());
        verify(userRepo, times(1)).findById(1);
    }

    @Test
    void getUserByUsername_WhenUserExists_ReturnUser() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(testUser));

        User result = userService.getUserByUsername("testuser");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepo.findByUsername("testuser"));
    }
}
