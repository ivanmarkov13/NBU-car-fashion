package com.studio.carfashion.repository;

import com.studio.carfashion.model.Company;
import com.studio.carfashion.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Employee findByEmail(String email);

    List<Employee> findByCompany(Company company);

}
