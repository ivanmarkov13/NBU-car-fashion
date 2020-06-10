package com.studio.carfashion.repository;

import com.studio.carfashion.model.CarPart;
import com.studio.carfashion.model.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarPartRepository extends CrudRepository<CarPart, Integer> {

    List<CarPart> findByCompany(Company company);

}
