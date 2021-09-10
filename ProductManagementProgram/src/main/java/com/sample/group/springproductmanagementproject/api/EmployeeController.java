package com.sample.group.springproductmanagementproject.api;

import com.sample.group.springproductmanagementproject.domain.Employee;
import com.sample.group.springproductmanagementproject.domain.EmployeeRepo;
import com.sample.group.springproductmanagementproject.domain.RoleRepo;
import com.sample.group.springproductmanagementproject.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepository;

    @Autowired
    RoleRepo roleRepository;

    @RequestMapping("/employee/{id}")
    public String employee (@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeRepository.findOne(id))
        model.addAttribute("role", roleRepository.findAll());
        return "employee";
    }

    @RequestMapping(value="/employees",method= RequestMethod.GET)
    public String employeesList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees";
    }

    @RequestMapping(value="/employees",method=RequestMethod.POST)
    public String employeesAdd(@RequestParam String email,
                                @RequestParam String firstName, @RequestParam String lastName, Model model) {
        Employee newEmployee = new Employee();
        newEmployee.setEmail(email);
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        employeeRepository.save(newEmployee);

        model.addAttribute("employee", newEmployee);
        model.addAttribute("role", roleRepository.findAll());
        return "redirect:/employee/" + newEmployee.getId();
    }

    @RequestMapping(value="/employee/{id}/roles", method=RequestMethod.POST)
    public String employeesAddAtribute(@PathVariable Long id, @RequestParam Long roleId, Model model) {
        Role role = roleRepository.findOne(roleId);
        Employee employee = (Employee) employeeRepository.findOne(id);

        if (employee != null) {
            if (!employee.hasRole(role)) {
                employee.getRoles().add(role);
            }
            employeeRepository.save(employee);
            model.addAttribute("employee", employeeRepository.findOne(id));
            model.addAttribute("roles", roleRepository.findAll());
            return "redirect:/employee/" + employee.getId();
        }

        model.addAttribute("employees", employeeRepository.findAll());
        return "redirect:/employees";
    }

}
