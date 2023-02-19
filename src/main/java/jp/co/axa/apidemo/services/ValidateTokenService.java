package jp.co.axa.apidemo.services;


/**
 * ValidateToken service define the application token validation method
 *
 * @author shriram.singh
 */
public interface ValidateTokenService {

    boolean validateToken(String token);
}
