package HW19.homewor19.controller;

import HW19.homewor19.entity.Employee;
import HW19.homewor19.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {this.employeeService = employeeService;}

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.add(firstName, lastName);

    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.find(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping(path = "/findAll")
    public Employee[] getEmployees() {
        return  employeeService.getAll();
    }

}
