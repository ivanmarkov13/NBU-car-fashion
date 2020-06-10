package com.studio.carfashion.service;

import com.studio.carfashion.model.*;
import com.studio.carfashion.repository.CompanyRepository;
import com.studio.carfashion.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private final CompanyService companyService;
    private final CarPartService carPartService;
    private final OrderService orderService;
    private final FabricService fabricService;

    public void registerOwner(String firstName, String lastName, String email, String password) {
        Employee employee = new Employee(email, password, firstName, lastName, "admin");
        employeeRepository.save(employee);
    }

    public void registerEmployee(String firstName,
                                 String lastName,
                                 String email,
                                 String seniority,
                                 Double wagePerHour,
                                 Integer companyId) {
        Company company = companyService.findById(companyId);
        Employee employee = new Employee(0, email, "N/A", firstName, lastName, "worker", seniority, wagePerHour, company, Collections.emptyList());
        employeeRepository.save(employee);
    }

    public void changeEmployeeDetails(Integer employeeId,
                                      String firstName,
                                      String lastName,
                                      String email,
                                      String seniority,
                                      Double wagePerHour,
                                      Integer companyId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        Company company = companyService.findById(companyId);

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setSeniority(seniority);
        employee.setWagePerHour(wagePerHour);
        employee.setCompany(company);

        employeeRepository.save(employee);
    }

    public void addEmployeesByBossToModel(Model model) {
        List<Company> companies = getCurrentUserCompanies();
        List<Employee> employees = new ArrayList<>();
        for (Company company : companies) {
            employees.addAll(employeeRepository.findByCompany(company));
        }
        employees.remove(getCurrentUser());
        model.addAttribute("employeesByBoss", employees);
    }



    public void addEmployeesByCompanyToModel(Integer companyId, Model model) {
        Company company = companyService.findById(companyId);
        List<Employee> employees = employeeRepository.findByCompany(company);
        employees.remove(getCurrentUser());
        model.addAttribute("employeesByCompany", employees);
    }

    public Employee findById(Integer employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public Employee getCurrentUser() {
//        return (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return employeeRepository.findByEmail("ivan.markov13@yahoo.com");
    }

    public List<Order> getCurrentUserOrders() {

        List<Company> companies = getCurrentUserCompanies();

        List<Order> orders = new ArrayList<>();
        for (Company company: companies) {
            orders.addAll(company.getOrders());
        }

        return orders;
    }

    public List<Company> getCurrentUserCompanies() {
        Employee currentUser = getCurrentUser();
        return companyService.findCompaniesByOwner(currentUser);
    }

    public List<CarPart> getCurrentUserParts() {
        List<Company> currentUserCompanies = getCurrentUserCompanies();
        List<CarPart> currentUserParts = new ArrayList<>();
        for(Company company : currentUserCompanies) {
            currentUserParts.addAll(carPartService.getPartsByCompany(company));
        }
        return currentUserParts;
    }

    public void assignEmployeesToOrder(Integer employeeId, Integer orderId) {

        Employee employee = findById(employeeId);
        Order order = orderService.findById(orderId);
        employee.addOrder(order);
        order.addEmployee(employee);

        employeeRepository.save(employee);
    }

    public List<Fabric> getCurrentUserFabrics() {
        List<Company> currentUserCompanies = getCurrentUserCompanies();
        List<Fabric> currentUserFabrics = new ArrayList<>();
        for(Company company : currentUserCompanies) {
            currentUserFabrics.addAll(fabricService.getFabricsByCompany(company));
        }
        return currentUserFabrics;
    }
}

