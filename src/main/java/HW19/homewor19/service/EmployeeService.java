package HW19.homewor19.service;

import HW19.homewor19.entity.Employee;
import HW19.homewor19.exception.EmployeeAlreadyAddedException;
import HW19.homewor19.exception.EmployeeNotFoundException;
import HW19.homewor19.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;

@Service
public class EmployeeService {
    private final int MAX_EMPLOYEES_COUNT = 2;

    private final Employee[] employees = new Employee[MAX_EMPLOYEES_COUNT];

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees[MAX_EMPLOYEES_COUNT - 1] != null) {
           throw new EmployeeStorageIsFullException("превышен лимит количества сотрудников в фирме");
        }

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].equals(employee)){
                throw new EmployeeAlreadyAddedException("добавляемый сотрудник уже имеется в коллекции");
            }

            if (employees[i] == null) {
                employees[i] = employee;
                break;
            }
        }
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

        for (int i = 0; i < employees.length; i++){
            if (employees[i] != null && employees[i].equals(employee)){
                employees[i] = null;
            }
        }
        return  employee;
    }

    public Employee[] getAll() {return employees;}
}
