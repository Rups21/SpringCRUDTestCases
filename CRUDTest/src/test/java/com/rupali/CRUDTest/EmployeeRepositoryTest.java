package com.rupali.CRUDTest;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployeeTest()
	{
//		Employee emp =new Employee();
//		
//		emp.setFirstname("Rupali");
//		emp.setLastname("Shinde");
//		emp.setEmail("rns@gmail.com");
		
		Employee employee = Employee.builder().id((long)1).firstname("Rupali").lastname("Shinde").email("rns@gmail.com").build();
		
		
		employeeRepository.save(employee);
		
	
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
		Assertions.assertThat(employee.getFirstname()).isEqualTo("Rupali");
		System.out.println("Employee Id" + employee.getId());
		
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)

	public void getEmployeeTest()
	{
		Employee employee = employeeRepository.getById(1L);
		System.out.println("getEmployeeTest()::Employee Id" + employee.getId());

		Assertions.assertThat(employee.getId()).isEqualTo(1);
		
	}
	
	@Test
	@Order(3)
	@Rollback(value = false)

	public void getEmployeeListTest()
	{
		List<Employee> employees = employeeRepository.findAll();
		
		System.out.println("List of employee: "+ employees.size());
		Assertions.assertThat(employees.size()).isGreaterThan(0);
		
		
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)

	public void updateEmployeeTest()
	{
		Employee employee = employeeRepository.getById(1L);
		
		
		employee.setEmail("rupali@gmail.com");
		
		Employee employeeUpdated = employeeRepository.save(employee);
				
		System.out.println("updateEmployeeTest()::Employee email" + employeeUpdated.getEmail());

		Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("rupali@gmail.com");
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)

	public void deleteEmployeeTest() {
		Employee employee = employeeRepository.getById(1L);
		
		employeeRepository.delete(employee);
				
		Employee emp1 = null;
		
		Optional<Employee>optionalEmployee = employeeRepository.findByEmail("rupali@gmail.com");
		
		if(optionalEmployee.isPresent()) {
			emp1 = optionalEmployee.get();
		}
		
		Assertions.assertThat(emp1).isNull();
		
		
	}
}
