package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.UserLogin;
import jp.co.axa.apidemo.repositories.UserLoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserLogin service implementation : this facilitates the user login data modification and retrieval
 *
 * @author shriram.singh
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLoginRepository userLoginRepository;

    public UserLoginServiceImpl(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    /**
     * Check if username and password are valid for login
     *
     * @param userName : employee name
     * @param passWord : employee password
     * @return {@link Optional<UserLogin>} employee data
     */
    @Override
    public UserLogin findByUserNameAndPassword(String userName, String passWord) {
        Optional<UserLogin> optLogin = userLoginRepository.findByUsernameAndPassword(userName, passWord);
        return optLogin.isPresent() ? optLogin.get() : null;
    }

    /**
     * Check if username is new user then update username and password in USERLOGIN table
     *
     * @param userLogin : contains username and password send in the request object
     * @return {@link boolean} true in case of success signup and false in case of some issue or user already present
     */
    @Override
    public boolean saveUserNameAndPassword(UserLogin userLogin) {
        // Check if username is already taken
        if (userLoginRepository.findByUsername(userLogin.getUsername()).isPresent()) {
            return false;
        }
        try {
            // Save new user with unique username
            userLoginRepository.save(userLogin);
            return true;
        } catch(Exception e) {
            //ToDO log error message
            return false;
        }
    }
}
