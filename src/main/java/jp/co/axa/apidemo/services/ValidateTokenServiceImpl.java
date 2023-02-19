package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.UserLogin;
import jp.co.axa.apidemo.repositories.UserLoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ValidateToken service implementation : this class validate if the token send from the user is valid or not
 *
 * @author shriram.singh
 */
@Service
public class ValidateTokenServiceImpl implements ValidateTokenService{

    private final UserLoginRepository userLoginRepository;

    private static final String TOKEN =  "admin_admin";

    private static final String TOKEN_PATTERN_SEPARATOR =  "_";

    public ValidateTokenServiceImpl(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    /**
     * Check if token is valid
     *
     * @param token : application user token
     * @return {@link boolean} true if valid token else false
     */
    @Override
    public boolean validateToken(String token) {
        String[] splitTokenArr = token.split(TOKEN_PATTERN_SEPARATOR);
        if(!token.equals(TOKEN) && splitTokenArr.length < 2){
           return false;
        }
        Optional<UserLogin> optLogin = userLoginRepository.findByUsernameAndPassword(splitTokenArr[0], splitTokenArr[1]);
        if(optLogin.isPresent()){
            return true;
        }
        return false;
    }
}
