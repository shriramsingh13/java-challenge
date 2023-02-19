package jp.co.axa.apidemo.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.controllers.UserLoginController;
import jp.co.axa.apidemo.entities.UserLogin;
import jp.co.axa.apidemo.services.UserLoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for UserLoginController
 *
 * @author shriram.singh
 */
@RunWith(MockitoJUnitRunner.class)
public class UserLoginControllerUT {

    @InjectMocks
    UserLoginController userLoginController;

    @Mock
    private UserLoginService userLoginService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userLoginController).build();
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
        Mockito.when(userLoginService.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(userLogin);

        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        // Verify that the employeeService.findByUserNameAndPassword method was called once to get employee data
        Mockito.verify(userLoginService, Mockito.times(1)).findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString());
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
        Mockito.when(userLoginService.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(null);

        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));

        // Verify that the employeeService.findByUserNameAndPassword method was called once to get employee data
        Mockito.verify(userLoginService, Mockito.times(1)).findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString());
    }

    /**
     * Test case 3 : Check if user able to SignUp with valid username and password
     * Condition : username should not be present in UserLogin table and user must be new for SignUp
     * Result : return true on successful SignUp
     * @throws Exception
     */
    @Test
    public void testSignUpWithSuccess() throws Exception {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("testUser1");
        userLogin.setPassword("testPass1");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(userLogin);
        Mockito.when(userLoginService.saveUserNameAndPassword(Mockito.any(UserLogin.class))).thenReturn(true);

        mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        // Verify that the employeeService.findByUserNameAndPassword method was called once to get employee data
        Mockito.verify(userLoginService, Mockito.times(1)).saveUserNameAndPassword((Mockito.any(UserLogin.class)));
    }

    /**
     * Test case 4 : Check if user able to SignUp with invalid username and password
     * Condition : username should be present in UserLogin table and user must not be new for SignUp
     * Result : return false on failed SignUp
     * @throws Exception
     */
    @Test
    public void testSignUpWithFailure() throws Exception {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("testUser1");
        userLogin.setPassword("testPass1");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(userLogin);
        Mockito.when(userLoginService.saveUserNameAndPassword(Mockito.any(UserLogin.class))).thenReturn(false);

        mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));

        // Verify that the employeeService.findByUserNameAndPassword method was called once to get employee data
        Mockito.verify(userLoginService, Mockito.times(1)).saveUserNameAndPassword((Mockito.any(UserLogin.class)));
    }
}
