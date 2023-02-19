package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.configuration.SpringConfigProperties;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

/**
 * Employee service implementation : this facilitates the employee data modification and retrieval
 *
 * @author shriram.singh
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    private SpringConfigProperties springConfigProperties;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieve all employee data
     *
     * @return {@Link List<Employee>} list of employee data
     */
    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Retrieve employee data as the employee Id
     *
     * @param employeeId
     * @return {@Link Employee}  employee data
     */
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.isPresent() ? optEmp.get() : null;
    }

    /**
     * Search employee data by their Id and Name and return top 10 matched search result
     *
     * @param id : employee id
     * @param name : employee name
     * @return {@Link List<Employee>} list of employee data
     */
    public List<Employee> searchEmployees(Long id, String name) {
        return employeeRepository.findTop10ByIdOrNameContaining(id, name, PageRequest.of(0, 10) );
    }

    /**
     * Save employee data
     *
     * @param employee
     * @return {@Link String} success or failure message
     */
    public boolean saveEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception E) {
            // ToDo log error message
            return false;
        }

    }

    /**
     * Delete employee data as per their employeeId
     *
     * @param employeeId
     * @return {@Link String} success or failure message
     */
    public boolean deleteEmployee(Long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
            return true;
        } catch (Exception E) {
            // ToDo log error message
            return false;
        }
    }

    /**
     * Update employee data as per their employeeId
     *
     * @param employee
     * @return {@Link String} success or failure message
     */
    public boolean updateEmployee(Employee employee) {
        try {
            String h2DBUrl = springConfigProperties.getUrl();
            String h2DBUser = springConfigProperties.getUser();
            String h2DBPassword = !StringUtils.isEmpty(springConfigProperties.getPassword()) ?
                    springConfigProperties.getPassword() : "" ;
            Connection connection = DriverManager.getConnection(h2DBUrl, h2DBUser, h2DBPassword);
            PreparedStatement statement = connection.prepareStatement("UPDATE EMPLOYEE SET EMPLOYEE_NAME=?, EMPLOYEE_SALARY=?, DEPARTMENT=? WHERE ID=?");
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getSalary());
            statement.setString(3, employee.getDepartment());
            statement.setLong(4, employee.getId());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            // ToDo log error message
            return  false;
        }
    }
}