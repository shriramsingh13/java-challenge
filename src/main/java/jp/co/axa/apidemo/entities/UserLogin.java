package jp.co.axa.apidemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * USERLOGIN table entity
 *
 * @author shriram.singh
 */
@Entity
@Table(name="USERLOGIN")
public class UserLogin {

    /**
     * This field ia an auto generated field
     */
	@Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is for username
     */
    @Getter
    @Setter
    @Column(name="USERNAME")
    private String username;

    /**
     * This field is for password
     */
    @Getter
    @Setter
    @Column(name="PASSWORD")
    private String password;
}
