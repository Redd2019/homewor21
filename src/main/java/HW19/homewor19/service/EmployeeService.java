package HW19.homewor19.service;

import HW19.homewor19.entity.Employee;
import HW19.homewor19.exception.EmployeeAlreadyAddedException;
import HW19.homewor19.exception.EmployeeNotFoundException;
import HW19.homewor19.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final int MAX_EMPLOYEES_COUNT = 2;

    private final List <Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
           throw new EmployeeStorageIsFullException("превышен лимит количества сотрудников в фирме");
        }

        if (employees.contains(employee)){
                throw new EmployeeAlreadyAddedException("добавляемый сотрудник уже имеется в коллекции");
            }
        employees.add(employee);

        return employee;
    }

    public Employee find(String firstName, String lastName){
        Employee employee = null;
        for (Employee e : employees){
            if (e!= null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())){
                employee = e;
            }
        }

        if (employee == null){
            throw new EmployeeNotFoundException("сотрудник не найден");
        }

        return employee;
    }

    public Employee remove(String firstName, String lastName){
        Employee employee = find(firstName, lastName);

        for (Employee e : employees){
            if (e.equals(employee)){
                return e;
            }
        }
        return  employee;
    }

    public List<Employee> getAll() {
        return employees;}
}
