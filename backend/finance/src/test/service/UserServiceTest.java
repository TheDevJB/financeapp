import org.mockito.Mock;

import com.financeapp.finance.repositories.UserRepository;
import com.financeapp.finance.service.UserService;


public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private UserService userService;
}
