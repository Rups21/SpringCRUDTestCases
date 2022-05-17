package com.rupali.CRUDTest;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class EmployeeRepositoryTest2 {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void saveEmployeeTest()
	{
		Employee emp =new Employee();
		
		emp.setFirstname("Rupali");
		emp.setLastname("Shinde");
		emp.setEmail("rns@gmail.com");
		
		employeeRepository.save(emp);
		
	
		Assertions.assertThat(emp.getId()).isGreaterThan(0);
		
		
	}
}
