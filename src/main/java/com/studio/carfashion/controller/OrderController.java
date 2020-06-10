package com.studio.carfashion.controller;

import com.studio.carfashion.model.*;
import com.studio.carfashion.service.CarPartService;
import com.studio.carfashion.service.EmployeeService;
import com.studio.carfashion.service.FabricService;
import com.studio.carfashion.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final EmployeeService employeeService;
    private final OrderService orderService;
    private final CarPartService carPartService;
    private final FabricService fabricService;

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Integer orderId, Model model) {

        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);

        Company orderCompany = order.getCompany();
        employeeService.addEmployeesByCompanyToModel(orderCompany.getId(), model);

        StringBuilder orderEmployees = new StringBuilder();
        for (Employee orderEmployee : order.getEmployees()) {
            orderEmployees.append(String.format("%s %s , ", orderEmployee.getFirstName(), orderEmployee.getLastName()));
        }
        model.addAttribute("orderEmployees", orderEmployees);

        List<CarPart> carParts = employeeService.getCurrentUserParts();
        model.addAttribute("carParts", carParts);

        List<Fabric> fabrics = fabricService.getFabricsByCompany(orderCompany);
        model.addAttribute("fabrics", fabrics);

        return "order-details";
    }

    @GetMapping
    public String getOrders(Model model) {
        model.addAttribute("companies", employeeService.getCurrentUserCompanies());
        model.addAttribute("orders", employeeService.getCurrentUserOrders());
        return "my-orders";
    }

    @PostMapping("/register")
    public String registerOrder(@RequestParam Integer companyId, Model model) {
        orderService.registerOrder(companyId);
        model.addAttribute("companies", employeeService.getCurrentUserCompanies());
        model.addAttribute("orders", employeeService.getCurrentUserOrders());
        return "my-orders";
    }

    @PostMapping("/assign-employee/{orderId}")
    public String assingEmployeeToOrder(@RequestParam Integer employeeId, @PathVariable Integer orderId, Model model) {

        employeeService.assignEmployeesToOrder(employeeId, orderId);

        Order order = orderService.findById(orderId);

        model.addAttribute("order", order);

        StringBuilder orderEmployees = new StringBuilder();
        for (Employee orderEmployee : order.getEmployees()) {
            orderEmployees.append(String.format("%s %s , ", orderEmployee.getFirstName(), orderEmployee.getLastName()));
        }
        model.addAttribute("orderEmployees", orderEmployees);

        Company orderCompany = order.getCompany();
        employeeService.addEmployeesByCompanyToModel(orderCompany.getId(), model);

        List<CarPart> carParts = carPartService.getPartsByCompany(orderCompany);
        model.addAttribute("carParts", carParts);

        List<Fabric> fabrics = fabricService.getFabricsByCompany(orderCompany);
        model.addAttribute("fabrics", fabrics);

        return "order-details";
    }

    @PostMapping("/add-work/{orderId}")
    public String addPieceOfWorkToOrder(@PathVariable Integer orderId,
                                        @RequestParam Integer partId,
                                        @PathVariable Integer fabricId,
                                        Model model) {
        CarPart carPart = carPartService.findById(partId);
        Fabric fabric = fabricService.findById(fabricId);

        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);

        Company orderCompany = order.getCompany();
        employeeService.addEmployeesByCompanyToModel(orderCompany.getId(), model);

        StringBuilder orderEmployees = new StringBuilder();
        for (Employee orderEmployee : order.getEmployees()) {
            orderEmployees.append(String.format("%s %s , ", orderEmployee.getFirstName(), orderEmployee.getLastName()));
        }
        model.addAttribute("orderEmployees", orderEmployees);

        List<CarPart> carParts = employeeService.getCurrentUserParts();
        model.addAttribute("carParts", carParts);

        List<Fabric> fabrics = fabricService.getFabricsByCompany(orderCompany);
        model.addAttribute("fabrics", fabrics);

        return "order-details";
    }

}
