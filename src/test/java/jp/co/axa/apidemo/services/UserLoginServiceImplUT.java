package jp.co.axa.apidemo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.entities.UserLogin;
import jp.co.axa.apidemo.repositories.UserLoginRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;


/**
 * Unit test for UserLoginController
 *
 * @author shriram.singh
 */
@RunWith(MockitoJUnitRunner.class)
public class UserLoginServiceImplUT {


    @InjectMocks
    UserLoginServiceImpl logic;

    @Mock
    private UserLoginRepository userLoginRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        userLoginRepository = Mockito.mock(UserLoginRepository.class);
        logic = new UserLoginServiceImpl(userLoginRepository);
    }

    /**
     * Test case 1 : Check if user able to login with valid username and password
     * Condition : username and password should be present in UserLogin table
     * Result : return true on successful login
     * @throws Exception
     */
    @Test
    public void testLoginWIthSuccess() throws Exception {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("testUser1");
        userLogin.setPassword("testPass1");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(userLogin);
        Mockito.when(userLoginRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(userLogin));

        UserLogin response = logic.findByUserNameAndPassword(userLogin.getUsername(), userLogin.getPassword());

        Assert.assertEquals(userLogin.getUsername(), response.getUsername());
        Assert.assertEquals(userLogin.getPassword(), response.getPassword());

        // Verify that the userLoginRepository.findByUserNameAndPassword method was called once to get employee data
        Mockito.verify(userLoginRepository, Mockito.times(1)).findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString());
    }

    /**
     * Test case 2 : Check if user able to login with invalid username and password
     * Condition : username and password should not be present in UserLogin table
     * Result : return false on failed login
     * @throws Exception
     */
    @Test
    public void testLoginWithFailure() throws Exception {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("testUser1");
        userLogin.setPassword("testPass1");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(userLogin);
        Mockito.when(userLoginRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());

        UserLogin response = logic.findByUserNameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        boolean isDataFound = response == null ? true : false;
        Assert.assertTrue(isDataFound);

        // Verify that the userLoginRepository.findByUserNameAndPassword method was called once to get employee data
        Mockito.verify(userLoginRepository, Mockito.times(1)).findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString());
    }
    //SignUp test cases are same as the test cases in UserLoginControllerUT please check it thare
}
