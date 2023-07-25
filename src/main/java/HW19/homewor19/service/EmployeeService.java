package HW19.homewor19.service;

import HW19.homewor19.entity.Employee;
import HW19.homewor19.exception.EmployeeAlreadyAddedException;
import HW19.homewor19.exception.EmployeeNotFoundException;
import HW19.homewor19.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class EmployeeService {
    private final int MAX_EMPLOYEES_COUNT = 2;

    private final Map<Integer, Employee> employeeByHashCode = new HashMap<>();

    public Employee add(String firstName, String lastName) {

        if (employeeByHashCode.size() == MAX_EMPLOYEES_COUNT) {
           throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }

        int employeeKey = getEmployeeKey(firstName, lastName);

        if (employeeByHashCode.containsKey(employeeKey)){
                throw new EmployeeAlreadyAddedException("Добавляемый сотрудник уже имеется в коллекции");
            }

        Employee employee = new Employee(firstName, lastName);
        employeeByHashCode.put(employeeKey,employee);

        return employee;
    }

    public Employee find(String firstName, String lastName){
        int employeeHashCode = getEmployeeKey(firstName, lastName);
        Employee employee = employeeByHashCode.get(employeeHashCode);

        presentCheck(employee);

        return employee;
    }

    public Employee remove(String firstName, String lastName){
        int employeeHashCode = getEmployeeKey(firstName, lastName);
        Employee employee = employeeByHashCode.remove(employeeHashCode);

        presentCheck(employee);
        return employee;
    }

    public List<Employee> getAll() {
        return (List<Employee>) employeeByHashCode.values().stream();
    }

    private int getEmployeeKey(String firstName, String lastName){
        return Objects.hash(firstName,lastName);
    }

    private void presentCheck(Employee employee) {
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }
}
