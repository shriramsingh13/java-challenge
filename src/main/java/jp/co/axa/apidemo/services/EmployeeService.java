package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

/**
 * Employee service interface
 *
 * @author shriram.singh
 */
public interface EmployeeService {

    List<Employee> retrieveEmployees();

    Employee getEmployee(Long employeeId);

    boolean saveEmployee(Employee employee);

    boolean deleteEmployee(Long employeeId);

    boolean updateEmployee(Employee employee);

    List<Employee> searchEmployees(Long id, String name);
}