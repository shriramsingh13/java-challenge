package jp.co.axa.apidemo.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.services.ValidateTokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for EmployeeController
 *
 * @author shriram.singh
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerUT {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private ValidateTokenService validateTokenService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    /**
     * Test case 1 : Get all employees data
     * Result : return list of all employees data
     * @throws Exception
     */
    @Test
    public void testGetAllEmployees() throws Exception {
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

        Mockito.when(employeeService.retrieveEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("TestName1"))
                .andExpect(jsonPath("$[0].salary").value(200000))
                .andExpect(jsonPath("$[0].department").value("IT"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("TestName2"))
                .andExpect(jsonPath("$[1].salary").value(250000))
                .andExpect(jsonPath("$[1].department").value("Mobile"));

        // Verify that the employeeService.retrieveEmployees method was called once to get employee data
        Mockito.verify(employeeService, Mockito.times(1)).retrieveEmployees();
    }

    /**
     * Test case 2 : Get all employees data but no date found
     * Result : return empty list
     * @throws Exception
     */
    @Test
    public void testGetAllEmployeesNotDataFound() throws Exception {
        List<Employee> employees = new ArrayList<>();
        Mockito.when(employeeService.retrieveEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        // Verify that the employeeService.retrieveEmployees method was called but not data found
        Mockito.verify(employeeService, Mockito.times(1)).retrieveEmployees();
    }

    /**
     * Test case 3 : Get employee data by id
     * Condition : employee id should be present in employee table
     * Result : return employee data as per id
     * @throws Exception
     */
    @Test
    public void testGetAllEmployeesById() throws Exception {
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

        Mockito.when(employeeService.getEmployee(id2)).thenReturn(employee2);

        mockMvc.perform(get("/api/v1//employees/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("TestName2"))
                .andExpect(jsonPath("$.salary").value(250000))
                .andExpect(jsonPath("$.department").value("Mobile"));

        // Verify that the employeeService.getEmployee method was called once with the employee id
        Mockito.verify(employeeService, Mockito.times(1)).getEmployee(id2);
    }

    /**
     * Test case 4 : Get employee data by id but no data found
     * Condition : employee id should not be present in employee table
     * Result : return null
     * @throws Exception
     */
    @Test
    public void testGetAllEmployeesByIdNotDataFound() throws Exception {
        long id2 = 55;
        Mockito.when(employeeService.getEmployee(id2)).thenReturn(null);

        mockMvc.perform(get("/api/v1/employees/"+id2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotHaveJsonPath());

        // Verify that the employeeService.getEmployee method was called once with the employee id but no data found
        Mockito.verify(employeeService, Mockito.times(1)).getEmployee(id2);
    }


    /**
     * Test case 5 : Search employee data by id or name
     * Condition : employee id or name should be present in employee table
     * Result : return list of top 10 employee data matching with id or name
     * @throws Exception
     */
    @Test
    public void testSearchEmployeesIdOrNameContains() throws Exception {
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

        Employee employee0 = new Employee();
        employee0.setName("TestName");
        Mockito.when(employeeService.searchEmployees(employee0.getId(),employee0.getName())).thenReturn(employees);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee0);
        mockMvc.perform(post("/api/v1/searchEmployees")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("TestName1"))
                .andExpect(jsonPath("$[0].salary").value(200000))
                .andExpect(jsonPath("$[0].department").value("IT"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("TestName2"))
                .andExpect(jsonPath("$[1].salary").value(250000))
                .andExpect(jsonPath("$[1].department").value("Mobile"));

        // Verify that the employeeService.searchEmployees method was called once with the employee id or name
        Mockito.verify(employeeService, Mockito.times(1)).searchEmployees(employee0.getId(),employee0.getName());
    }

    /**
     * Test case 6 : Search employee data by id or name but not match found
     * Condition : employee id or name should not be present in employee table
     * Result : return empty list
     * @throws Exception
     */
    @Test
    public void testSearchEmployeesIdOrNameContainsButNoDataFound() throws Exception {
        List<Employee> employees = new ArrayList<>();
        Employee employee0 = new Employee();
        employee0.setName("TestName");
        Mockito.when(employeeService.searchEmployees(employee0.getId(),employee0.getName())).thenReturn(employees);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee0);
        mockMvc.perform(post("/api/v1/searchEmployees")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        // Verify that the employeeService.searchEmployees method was called once with the employee id or name but not data found
        Mockito.verify(employeeService, Mockito.times(1)).searchEmployees(employee0.getId(),employee0.getName());
    }

    /**
     * Test case 7 : Create employee data with valid token
     * Condition : auth token should be valid and employee data should be inserted in employee table
     * Result : return true on success
     * @throws Exception
     */
    @Test
    public void testCreateEmployeeWithValidToken() throws Exception {
        Employee employee1 = new Employee();
        employee1.setName("TestName1");
        employee1.setSalary(200000);
        employee1.setDepartment("IT");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee1);
        Mockito.when(validateTokenService.validateToken(Mockito.anyString())).thenReturn(true);
        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class))).thenReturn(true);

        mockMvc.perform(post("/api/v1/saveEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", "token")
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        // Verify that the employeeService.saveEmployee method was called once with the employee data
        Mockito.verify(employeeService, Mockito.times(1)).saveEmployee(Mockito.any(Employee.class));
    }

    /**
     * Test case 1 : Create employee data with invalid token
     * Condition : auth token should be invalid
     * Result : return false on invalid auto token found
     * @throws Exception
     */
    @Test
    public void testCreateEmployeeWithInValidToken() throws Exception {
        Employee employee1 = new Employee();
        employee1.setName("TestName1");
        employee1.setSalary(200000);
        employee1.setDepartment("IT");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee1);
        Mockito.when(validateTokenService.validateToken(Mockito.anyString())).thenReturn(false);

        mockMvc.perform(post("/api/v1/saveEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", "token")
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    /**
     * Test case 1 : Delete employee data by id with valid token
     * Condition : auth token should be valid and employee id should be present in employee table
     * Result : return true on success
     * @throws Exception
     */
    @Test
    public void testDeleteEmployeeWithValidToken() throws Exception {
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
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee1);

        // Mocking the token validation service to return true
        Mockito.when(validateTokenService.validateToken(Mockito.anyString())).thenReturn(true);

        // Mocking the employee service to return true for the delete operation
        Mockito.when(employeeService.deleteEmployee(id1)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/deleteEmployees")
                .header("authorization", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        // Verify that the employeeService.deleteEmployee method was called once with the employee id
        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployee(id1);
    }

    /**
     * Test case 1 : Delete employee data by id with invalid token
     * Condition : auth token should be invalid
     * Result : return false on invalid auto token
     * @throws Exception
     */
    @Test
    public void testDeleteEmployeeWithInvalidToken() throws Exception {
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
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee1);

        // Mocking the token validation service to return true
        Mockito.when(validateTokenService.validateToken(Mockito.anyString())).thenReturn(false);

        mockMvc.perform(delete("/api/v1/deleteEmployees")
                .header("authorization", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    /**
     * Test case 1 : Delete employee data by id with invalid token
     * Condition : employee id should be invalid
     * Result : return false on invalid employee id
     * @throws Exception
     */
    @Test
    public void testDeleteEmployeeWithValidTokenAndInvalidID() throws Exception {
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
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee1);

        // Mocking the token validation service to return true
        Mockito.when(validateTokenService.validateToken(Mockito.anyString())).thenReturn(true);

        // Mocking the employee service to return true for the delete operation
        Mockito.when(employeeService.deleteEmployee(id1)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/deleteEmployees")
                .header("authorization", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));

        // Verify that the employeeService.deleteEmployee method was not called once with the employee id
        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployee(id1);
    }

    /**
     * Test case 1 : Update employee data by id with valid token
     * Condition : employee id and auth token should be valid
     * Result : return true on successful update
     * @throws Exception
     */
    @Test
    public void testUpdateEmployeeWithValidToken() throws Exception {
        Employee employee1 = new Employee();
        long id1 = 7;
        employee1.setId(id1);
        employee1.setName("TestName7");
        employee1.setSalary(900000);
        employee1.setDepartment("IT");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee1);
        Mockito.when(validateTokenService.validateToken(Mockito.anyString())).thenReturn(true);
        Mockito.when(employeeService.getEmployee(id1)).thenReturn(employee1);
        Mockito.when(employeeService.updateEmployee(Mockito.any(Employee.class))).thenReturn(true);

        mockMvc.perform(put("/api/v1/updateEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", "token")
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        // Verify that the employeeService.updateEmployee method was called once with the employee id
        Mockito.verify(employeeService, Mockito.times(1)).updateEmployee(Mockito.any(Employee.class));
    }

    /**
     * Test case 1 : Update employee data by id with invalid token
     * Condition : employee id should be valid but auth token should be invalid
     * Result : return false on invalid auth token
     * @throws Exception
     */
    @Test
    public void testUpdateEmployeeWithInValidToken() throws Exception {
        Employee employee1 = new Employee();
        long id1 = 7;
        employee1.setId(id1);
        employee1.setName("TestName7");
        employee1.setSalary(900000);
        employee1.setDepartment("IT");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee1);
        Mockito.when(validateTokenService.validateToken(Mockito.anyString())).thenReturn(false);

        mockMvc.perform(put("/api/v1/updateEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", "token")
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    /**
     * Test case 1 : Update employee data by invalid id but valid token
     * Condition : employee id should be invalid but auth token should be valid
     * Result : return false on invalid employee id
     * @throws Exception
     */
    @Test
    public void testUpdateEmployeeWithUserIdNotFound() throws Exception {
        Employee employee1 = new Employee();
        long id1 = 7;
        employee1.setId(id1);
        employee1.setName("TestName7");
        employee1.setSalary(900000);
        employee1.setDepartment("IT");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee1);
        Mockito.when(validateTokenService.validateToken(Mockito.anyString())).thenReturn(true);
        Mockito.when(employeeService.getEmployee(Mockito.anyLong())).thenReturn(null);

        mockMvc.perform(put("/api/v1/updateEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", "token")
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }
}
