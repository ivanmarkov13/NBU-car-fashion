package com.studio.carfashion.service;

import com.studio.carfashion.model.CarPart;
import com.studio.carfashion.model.Company;
import com.studio.carfashion.repository.CarPartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarPartService {

    private final CarPartRepository carPartRepository;
    private final CompanyService companyService;

    public List<CarPart> getPartsByCompany(Company company) {
        return carPartRepository.findByCompany(company);
    }

    public void registerPart(String partName, Double priceMultiplier, Integer companyId) {
        Company company = companyService.findById(companyId);
        CarPart carPart = new CarPart(0, partName, priceMultiplier, company);
        carPartRepository.save(carPart);
    }

    public CarPart findById(Integer carPartId) {
        return carPartRepository.findById(carPartId).get();
    }

    public void changePartDetails(Integer partId, String partName, Double priceMultiplier, Integer companyId) {

        Company company = companyService.findById(companyId);

        CarPart carPart = findById(partId);
        carPart.setName(partName);
        carPart.setPriceMultiplier(priceMultiplier);
        carPart.setCompany(company);

        carPartRepository.save(carPart);
    }
}
