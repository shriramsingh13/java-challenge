package jp.co.axa.apidemo.services;


import jp.co.axa.apidemo.configuration.SpringConfigProperties;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Unit test for EmployeeServiceImpl
 *
 * @author shriram.singh
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplUT {

    EmployeeServiceImpl logic;

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @Mock
    private SpringConfigProperties springConfigPropertiesMock;

    @Before
    public void setUp() {
        employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        logic = new EmployeeServiceImpl(employeeRepositoryMock);
    }

    /**
     * Test case 1 : Get all employees data
     * Result : return list of all employees data
     */
    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        long id1 = 1;
        employee1.setId(id1);
        employee1.setName("TestName1");
        employee1.setSalary(200000);
        employee1.setDepartment("IT");

        Employee employee2 = new Employee();
        long id2 = 2;
        employee2.setId(id2);
        employee2.setName("TestName2");
        employee2.setSalary(250000);
        employee2.setDepartment("Mobile");

        employees.add(employee1);
        employees.add(employee2);

        Mockito.when(employeeRepositoryMock.findAll()).thenReturn(employees);
        List<Employee> response = logic.retrieveEmployees();
        for (int i = 0; i < response.size(); i++) {
            Assert.assertEquals(employees.get(i).getId(), response.get(i).getId());
            Assert.assertEquals(employees.get(i).getName(), response.get(i).getName());
            Assert.assertEquals(employees.get(i).getSalary(), response.get(i).getSalary());
            Assert.assertEquals(employees.get(i).getDepartment(), response.get(i).getDepartment());
        }
        // Verify that the employeeService.retrieveEmployees method was called once to get employee data
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findAll();
    }

    /**
     * Test case 2 : Get all employees data but no date found
     * Result : return empty list
     */
    @Test
    public void testGetAllEmployeesButNotDaataFound() throws Exception {
        List<Employee> employees = new ArrayList<>();

        Mockito.when(employeeRepositoryMock.findAll()).thenReturn(employees);
        List<Employee> response = logic.retrieveEmployees();
        Assert.assertEquals(0, response.size());
        // Verify that the employeeService.retrieveEmployees method was called once to get employee data
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findAll();
    }

    /**
     * Test case 3 : Get employee data by id
     * Condition : employee id should be present in employee table
     * Result : return employee data as per id
     */
    @Test
    public void testGetAllEmployeesById() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        long id1 = 1;
        employee1.setId(id1);
        employee1.setName("TestName1");
        employee1.setSalary(200000);
        employee1.setDepartment("IT");

        Employee employee2 = new Employee();
        long id2 = 2;
        employee2.setId(id2);
        employee2.setName("TestName2");
        employee2.setSalary(250000);
        employee2.setDepartment("Mobile");

        employees.add(employee1);
        employees.add(employee2);

        Mockito.when(employeeRepositoryMock.findById(employee2.getId())).thenReturn(Optional.of(employee2));
        Employee response = logic.getEmployee(employee2.getId());

        Assert.assertEquals(employee2.getId(), response.getId());
        Assert.assertEquals(employee2.getName(), response.getName());
        Assert.assertEquals(employee2.getSalary(), response.getSalary());
        Assert.assertEquals(employee2.getDepartment(), response.getDepartment());

        // Verify that the employeeService.retrieveEmployees method was called once to get employee data
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employee2.getId());
    }


    /**
     * Test case 4 : Get employee data by id but no data found
     * Condition : employee id should not be present in employee table
     * Result : return null
     * @throws Exception
     */
    @Test
    public void testGetAllEmployeesByIdButNoDataFound() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        long id1 = 1;
        employee1.setId(id1);
        employee1.setName("TestName1");
        employee1.setSalary(200000);
        employee1.setDepartment("IT");

        Employee employee2 = new Employee();
        long id2 = 2;
        employee2.setId(id2);
        employee2.setName("TestName2");
        employee2.setSalary(250000);
        employee2.setDepartment("Mobile");

        employees.add(employee1);
        employees.add(employee2);

        Mockito.when(employeeRepositoryMock.findById(employee2.getId())).thenReturn(Optional.empty());
        Employee response = logic.getEmployee(employee2.getId());
        boolean isDataFound = response == null ? true : false;
        Assert.assertTrue(isDataFound);
        // Verify that the employeeService.retrieveEmployees method was called once to get employee data
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employee2.getId());
    }

    /**
     * Test case 5 : Search employee data by id or name
     * Condition : employee id or name should be present in employee table
     * Result : return list of top 10 employee data matching with id or name
     */
    @Test
    public void testSearchEmployeesIdOrNameContains() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        long id1 = 1;
        employee1.setId(id1);
        employee1.setName("TestName1");
        employee1.setSalary(200000);
        employee1.setDepartment("IT");

        Employee employee2 = new Employee();
        long id2 = 2;
        employee2.setId(id2);
        employee2.setName("TestName2");
        employee2.setSalary(250000);
        employee2.setDepartment("Mobile");

        //employees.add(employee1);
        employees.add(employee2);

        Mockito.when(employeeRepositoryMock.findTop10ByIdOrNameContaining(employee2.getId(), employee2.getName(),
                PageRequest.of(0, 10) )).thenReturn(employees);
        List<Employee> response = logic.searchEmployees(employee2.getId(), employee2.getName());

        for (int i = 0; i < response.size(); i++) {
            Assert.assertEquals(employees.get(i).getId(), response.get(i).getId());
            Assert.assertEquals(employees.get(i).getName(), response.get(i).getName());
            Assert.assertEquals(employees.get(i).getSalary(), response.get(i).getSalary());
            Assert.assertEquals(employees.get(i).getDepartment(), response.get(i).getDepartment());
        }
    }

    /**
     * Test case 6 : Search employee data by id or name but not match found
     * Condition : employee id or name should not be present in employee table
     * Result : return empty list
     */
    @Test
    public void testSearchEmployeesIdOrNameContainsButNoDataFound() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        long id1 = 1;
        employee1.setId(id1);
        employee1.setName("TestName1");
        employee1.setSalary(200000);
        employee1.setDepartment("IT");

        Employee employee2 = new Employee();
        long id2 = 2;
        employee2.setId(id2);
        employee2.setName("TestName2");
        employee2.setSalary(250000);
        employee2.setDepartment("Mobile");

        //employees.add(employee1);
        employees.add(employee2);

        Mockito.when(employeeRepositoryMock.findTop10ByIdOrNameContaining(employee2.getId(), employee2.getName(),
                PageRequest.of(0, 10) )).thenReturn(Collections.emptyList());
        List<Employee> response = logic.searchEmployees(employee2.getId(), employee2.getName());
        Assert.assertEquals(0, response.size());
    }

    //Save , Delete and Update test cases are already been added EmployeeControllerUT, they are pretty much same
}
