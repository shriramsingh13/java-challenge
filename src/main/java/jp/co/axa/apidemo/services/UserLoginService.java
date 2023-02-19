package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.UserLogin;

/**
 * UserLogin service interface
 *
 * @author shriram.singh
 */
public interface UserLoginService {

    UserLogin findByUserNameAndPassword(String username, String password);

    boolean saveUserNameAndPassword(UserLogin userLogin);
}
