package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.services.ValidateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Rest Controller to listen request related to employee data retrieval and modification
 *
 * @author shriram.singh
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ValidateTokenService validateTokenService;


    /**
     * Retrieve all employee data
     *
     * @return {@Link List<Employee>} list of employee data
     */
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        try {
            return employeeService.retrieveEmployees();
        } catch (Exception e) {
            //ToDo log error message
            return Collections.emptyList();
        }
    }

    /**
     * Retrieve employee data as the employee Id
     *
     * @param employeeId
     * @return {@Link Employee}  employee data
     */
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        try {
            return employeeService.getEmployee(employeeId);
        } catch (Exception e) {
            //ToDo log error message
            return null;
        }
    }

    /**
     * Search employee data by their Id and Name and return top 10 matched search result
     *
     * @param employee : employee details
     * @return {@Link List<Employee>} list of employee data
     */
    @PostMapping("/searchEmployees")
    public List<Employee> searchEmployeesList(@RequestBody Employee employee) {
        try {
            return employeeService.searchEmployees(employee.getId(), employee.getName());
        } catch (Exception e) {
            //ToDo log error message
            return Collections.emptyList();
        }
    }

    /**
     * Save employee data
     *
     * @param employee
     * @param authorization : auth token
     * @return {@Link String} success or failure message
     */
    @PostMapping("/saveEmployee")
    public boolean saveEmployee(@RequestBody Employee employee, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!validateTokenService.validateToken(authorization)) {
            return false;
        }
        return employeeService.saveEmployee(employee);
    }

    /**
     * Delete employee data as per their employeeId
     *
     * @param employee
     * @param authorization : auth token
     * @return {@Link String} success or failure message
     */
    @DeleteMapping("/deleteEmployees")
    public boolean deleteEmployee(@RequestBody Employee employee, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!validateTokenService.validateToken(authorization)) {
            return false;
        }
        return employeeService.deleteEmployee(employee.getId());
    }

    /**
     * Update employee data as per their employeeId
     *
     * @param employee
     * @param authorization : auth token
     * @return {@Link String} success or failure message
     */
    @PutMapping("/updateEmployee")
    public boolean updateEmployee(@RequestBody Employee employee, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        try {
            if (!validateTokenService.validateToken(authorization)) {
                return false;
            }
            Employee emp = employeeService.getEmployee(employee.getId());
            if (emp != null) {
                return employeeService.updateEmployee(employee);
            } else {
                return false;
            }
        } catch (Exception e) {
            //ToDo log error message
            return false;
        }
    }
}
