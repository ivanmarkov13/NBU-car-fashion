package com.studio.carfashion.controller;

import com.studio.carfashion.model.Company;
import com.studio.carfashion.model.Employee;
import com.studio.carfashion.service.CompanyService;
import com.studio.carfashion.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
    private CompanyService companyService;

    @GetMapping
    public String getEmployees(Model model) {
        employeeService.addEmployeesByBossToModel(model);
        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);
        return "my-employees";
    }

    @GetMapping("/{employeeId}")
    public String getEmployee(@PathVariable Integer employeeId, Model model) {
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("company", employee.getCompany());
        model.addAttribute("companies", employeeService.getCurrentUserCompanies());
        return "employee-details";
    }

    @PostMapping("/register-owner")
    public void registerOwner(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String email,
                              @RequestParam String password) {
        employeeService.registerOwner(firstName, lastName, email, password);
    }

    @PostMapping("/register-employee")
    public String registerEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String email,
                                   @RequestParam String seniority,
                                   @RequestParam Double wagePerHour,
                                   @RequestParam Integer companyId,
                                   Model model) {
        employeeService.registerEmployee(firstName, lastName, email, seniority, wagePerHour, companyId);
        employeeService.addEmployeesByBossToModel(model);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "my-employees";
    }

    @PostMapping("/change-details/{employeeId}")
    public String changeEmployeeDetails(@PathVariable Integer employeeId,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam String email,
                                        @RequestParam String seniority,
                                        @RequestParam Double wagePerHour,
                                        @RequestParam Integer companyId,
                                        Model model) {

        employeeService.changeEmployeeDetails(employeeId, firstName, lastName, email, seniority, wagePerHour, companyId);
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("company", employee.getCompany());
        model.addAttribute("companies", employeeService.getCurrentUserCompanies());
        return "employee-details";
    }

}