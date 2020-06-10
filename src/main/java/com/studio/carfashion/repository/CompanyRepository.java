package com.studio.carfashion.repository;

import com.studio.carfashion.model.Company;
import com.studio.carfashion.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    List<Company> findByOwner(Employee owner);


}