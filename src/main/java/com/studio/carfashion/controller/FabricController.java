package com.studio.carfashion.controller;

import com.studio.carfashion.model.CarPart;
import com.studio.carfashion.model.Company;
import com.studio.carfashion.model.Fabric;
import com.studio.carfashion.service.CompanyService;
import com.studio.carfashion.service.EmployeeService;
import com.studio.carfashion.service.FabricService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fabric")
@AllArgsConstructor
public class FabricController {

    private final EmployeeService employeeService;
    private final FabricService fabricService;

    @GetMapping
    public String getFabrics(Model model) {

        List<Fabric> currentUserFabrics = employeeService.getCurrentUserFabrics();
        model.addAttribute("fabrics", currentUserFabrics);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "my-fabrics";
    }

    @GetMapping("/{fabricId}")
    public String getPartDetails(@PathVariable Integer fabricId, Model model) {

        Fabric fabric = fabricService.findById(fabricId);
        model.addAttribute("fabric", fabric);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "fabric-details";
    }


    @PostMapping("change-details/{fabricId}")
    public String changePartDetails(@PathVariable Integer fabricId,
                                    @RequestParam String material,
                                    @RequestParam Double pricePerSquareMeter,
                                    @RequestParam String origin,
                                    @RequestParam String description,
                                    @RequestParam Double amountInStock,
                                    @RequestParam Integer companyId,
                                    Model model) {

        fabricService.changeFabricDetails(fabricId, material, pricePerSquareMeter, origin, description, amountInStock, companyId);

        Fabric fabric = fabricService.findById(fabricId);
        model.addAttribute("fabric", fabric);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "fabric-details";
    }

    @PostMapping
    public String registerFabric(@RequestParam String material,
                                 @RequestParam Double pricePerSquareMeter,
                                 @RequestParam String origin,
                                 @RequestParam String description,
                                 @RequestParam Double amountInStock,
                                  @RequestParam Integer companyId,
                                  Model model) {

        fabricService.registerFabric(material, pricePerSquareMeter, origin, description, amountInStock, companyId);

        List<Fabric> currentUserFabrics = employeeService.getCurrentUserFabrics();
        model.addAttribute("fabrics", currentUserFabrics);

        List<Company> currentUserCompanies = employeeService.getCurrentUserCompanies();
        model.addAttribute("companies", currentUserCompanies);

        return "my-fabrics";
    }

}
