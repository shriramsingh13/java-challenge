package jp.co.axa.apidemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.co.axa.apidemo.entities.UserLogin;

/**
 * USERLOGIN table repository
 *
 * @author shriram.singh
 */
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    /**
     * find employee data by username and password also this field is not be cached
     * because we want to get realtime data from USERLOGIN table for validation
     *
     * @return {@Link Optional<UserLogin>} user data
     */
    Optional<UserLogin> findByUsernameAndPassword(String username, String password);

    /**
     * find employee data by username and this field is not be cached
     * because we want to get realtime data from USERLOGIN table for validation
     *
     * @return {@Link Optional<UserLogin>} user data
     */
    Optional<UserLogin> findByUsername(String username);

}