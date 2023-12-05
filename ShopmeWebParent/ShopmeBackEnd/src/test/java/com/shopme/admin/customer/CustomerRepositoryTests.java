package com.shopme.admin.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import com.shopme.common.entity.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTests {

	@Autowired private CustomerRepository repo;
	
	@Test
	public void testListAll() {
		Iterable<Customer> listCustomers = repo.findAll();
		listCustomers.forEach(c -> {
			System.out.printf("%-30s - %-20s %-20s %-20s\n", c.getFullName(),
					c.getCountry().getName(), c.getState(), c.getCity());
		});
	}
	
	@Test
	public void testSortByMultipleColumns() {
		Sort sort = Sort.by("country_name").ascending();
		sort = sort.and(Sort.by("state").ascending());
		sort = sort.and(Sort.by("city").ascending());
		
		Iterable<Customer> listCustomers = repo.findAll(sort);
		
		listCustomers.forEach(c -> {
			System.out.printf("%-30s - %-20s %-20s %-20s\n", c.getFullName(),
					c.getCountry().getName(), c.getState(), c.getCity());
		});		
	}
}
