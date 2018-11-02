package w7challengeemployeesanddepartments.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
   //Autowiring repositories
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    //requesting Indexpage that list of forms which can add department,employee ,and showing lists of employee and department.

    @RequestMapping("/")
    public String indexpage(){
        return "index";
    }

    @RequestMapping("/home")
    public String Homepage(){
        return "homeform";
    }
    //adding new department.
    @GetMapping("/addDepartment")
    public String getDepartmentForm(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }
    //saving new department in to database.
    @PostMapping("/addDepartment")
    public String addDepartment(@ModelAttribute("department") Department department){
        departmentRepository.save(department);
        return "redirect:/departmentlist";
    }
    //showing the list of departments from DB.
    @RequestMapping("/departmentlist")
    public String getDepartmentList(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentlistform";
    }
    //adding new employee.
    @GetMapping("/addEmployee")
    public String getEmployeeForm(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments",departmentRepository.findAll());
        return "employeeform";
    }

    //saving new department in to databse.
    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employeelist";
    }
    //showing the list of employee from DB.
    @RequestMapping("/employeelist")
    public String getEmployeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employeelistform";
    }

    //updating Employee
    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employeeform";
    }
    //showing details of an Employee
    @RequestMapping("/detail/{id}")
    public String getEmployeeList(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employeedetail";
    }
//deleting an employee from DB.
    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/employeelist";
    }
}
