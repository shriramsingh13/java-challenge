package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.retrieveEmployees();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		return employeeService.getEmployee(employeeId);
	}

	@PostMapping("/employees")
	public String saveEmployee(Employee employee) {
		return employeeService.saveEmployee(employee);
		// System.out.println("Employee Saved Successfully");
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		return employeeService.deleteEmployee(employeeId);
		// System.out.println("Employee Deleted Successfully");
	}

	@PutMapping("/employees/{employeeId}")
	public String updateEmployee(@RequestBody Employee employee, @PathVariable(name = "employeeId") Long employeeId) {
		Employee emp = employeeService.getEmployee(employeeId);
		if (emp != null) {
			return employeeService.updateEmployee(employee);
		} else {
			return "Invalid employeeId";
		}
	}
}
