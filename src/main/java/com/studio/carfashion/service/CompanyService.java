package com.studio.carfashion.service;

import com.studio.carfashion.model.Company;
import com.studio.carfashion.model.Employee;
import com.studio.carfashion.repository.CompanyRepository;
import com.studio.carfashion.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public Company findById(Integer id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.get();
    }

    public void registerCompany(String name) {

        Employee owner = employeeRepository.findByEmail("ivan.markov13@yahoo.com");
        Company company = new Company(0, name, owner, LocalDate.now(), Collections.emptyList(), Collections.emptyList());
        owner.setCompany(company);

        companyRepository.save(company);
        employeeRepository.save(owner);
    }

    public void changeName(int companyId, String name) {
        Company company = companyRepository.findById(companyId).get();
        company.setName(name);
        companyRepository.save(company);
    }

    public List<Company> findCompaniesByOwner(Employee owner) {
        return companyRepository.findByOwner(owner);
    }

}
