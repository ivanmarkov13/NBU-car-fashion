package com.studio.carfashion.repository;

import com.studio.carfashion.model.CarPart;
import com.studio.carfashion.model.Company;
import com.studio.carfashion.model.Fabric;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FabricRepository extends CrudRepository<Fabric, Integer> {
    List<Fabric> findByCompany(Company company);
}
