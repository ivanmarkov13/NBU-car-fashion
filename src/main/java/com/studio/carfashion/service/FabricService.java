package com.studio.carfashion.service;

import com.studio.carfashion.model.CarPart;
import com.studio.carfashion.model.Company;
import com.studio.carfashion.model.Fabric;
import com.studio.carfashion.repository.CarPartRepository;
import com.studio.carfashion.repository.FabricRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FabricService {

    private final FabricRepository fabricRepository;
    private final CompanyService companyService;

    public List<Fabric> getFabricsByCompany(Company company) {
        return fabricRepository.findByCompany(company);
    }

    public void registerFabric(String material, Double pricePerSquareMeter, String origin,
                               String description, Double amountInStock, Integer companyId) {

        Company company = companyService.findById(companyId);
        Fabric fabric = new Fabric(0, material, pricePerSquareMeter, origin, description, amountInStock, company);

        fabricRepository.save(fabric);
    }

    public Fabric findById(Integer fabricId) {
        return fabricRepository.findById(fabricId).get();
    }

    public void changeFabricDetails(Integer fabricId, String material, Double pricePerSquareMeter,
            String origin, String description, Double amountInStock, Integer companyId) {

        Company company = companyService.findById(companyId);

        Fabric fabric = findById(fabricId);
        fabric.setMaterial(material);
        fabric.setPricePerSquareMeter(pricePerSquareMeter);
        fabric.setOrigin(origin);
        fabric.setDescription(description);
        fabric.setAmountInStock(amountInStock);
        fabric.setCompany(company);

        fabricRepository.save(fabric);
    }

}
