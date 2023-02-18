package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> retrieveEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployee(Long employeeId) {
		Optional<Employee> optEmp = employeeRepository.findById(employeeId);
		return optEmp.get();
	}

	public String saveEmployee(Employee employee) {
		try {
			employeeRepository.save(employee);
			return "Save successful";
		} catch (Exception E) {
			// ToDo log error message
			return "Save failed";
		}

	}

	public String deleteEmployee(Long employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
			return "Employee deleted successful";
		} catch (Exception E) {
			// ToDo log error message
			return "Employee deletion successfulfailed";
		}
	}

	public String updateEmployee(Employee employee) {
		try {
			employeeRepository.save(employee);
			return "Employee details updated successful";
		} catch (Exception E) {
			// ToDo log error message
			return "Employee deatils update failed";
		}
	}
}