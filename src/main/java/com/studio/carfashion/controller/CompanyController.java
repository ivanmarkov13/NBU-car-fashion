package com.studio.carfashion.controller;

import com.studio.carfashion.model.Company;
import com.studio.carfashion.service.CompanyService;
import com.studio.carfashion.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @GetMapping
    public String getCompanies(Model model) {
        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);
        return "my-companies";
    }

    @GetMapping("/{id}")
    public String getCompanyDetails(@PathVariable Integer id, Model model) {
        Company company = companyService.findById(id);
        model.addAttribute("company", company);
        employeeService.addEmployeesByCompanyToModel(id, model);
        return "company-details";
    }

    @PostMapping("/register")
    public String register(@RequestParam String companyName, Model model) {
        companyService.registerCompany(companyName);
        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);
        return "my-companies";
    }

    @PostMapping("/change-name/{companyId}")
    public String changeName(@PathVariable Integer companyId, @RequestParam String newName, Model model) {
        companyService.changeName(companyId, newName);
        Company company = companyService.findById(companyId);
        model.addAttribute("company", company);
        employeeService.addEmployeesByCompanyToModel(companyId, model);
        return "company-details";
    }
}
