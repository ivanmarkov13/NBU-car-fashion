package com.studio.carfashion.controller;

import com.studio.carfashion.model.CarPart;
import com.studio.carfashion.model.Company;
import com.studio.carfashion.service.CarPartService;
import com.studio.carfashion.service.CompanyService;
import com.studio.carfashion.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/part")
@AllArgsConstructor
public class CarPartController {

    private final EmployeeService employeeService;
    private final CarPartService carPartService;
    private final CompanyService companyService;

    @GetMapping
    public String getParts(Model model) {

        List<CarPart> currentUserParts = employeeService.getCurrentUserParts();
        model.addAttribute("parts", currentUserParts);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "my-parts";
    }

    @GetMapping("/{partId}")
    public String getPartDetails(@PathVariable Integer partId, Model model) {

        CarPart carPart = carPartService.findById(partId);
        model.addAttribute("part", carPart);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "part-details";
    }


    @PostMapping("change-details/{partId}")
    public String changePartDetails(@PathVariable Integer partId,
                                    @RequestParam String partName,
                                    @RequestParam Double priceMultiplier,
                                    @RequestParam Integer companyId,
                                    Model model) {

        carPartService.changePartDetails(partId, partName, priceMultiplier, companyId);

        CarPart carPart = carPartService.findById(partId);
        model.addAttribute("part", carPart);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "part-details";
    }

    @PostMapping
    public String registerCarPart(@RequestParam String partName,
                                  @RequestParam Double priceMultiplier,
                                  @RequestParam Integer companyId,
                                  Model model) {

        carPartService.registerPart(partName, priceMultiplier, companyId);

        List<CarPart> currentUserParts = employeeService.getCurrentUserParts();
        model.addAttribute("parts", currentUserParts);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "my-parts";
    }

}
