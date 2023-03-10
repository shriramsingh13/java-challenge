package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EMPLOYEE table entity
 *
 * @author shriram.singh
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee {

    /**
     * This field ia an auto generated field
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is for employee name
     */
    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    private String name;

    /**
     * This field is for employee salary
     */
    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    /**
     * This field is for employee department
     */
    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;

}
