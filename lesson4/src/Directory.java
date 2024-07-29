import java.util.ArrayList;
import java.util.List;

public class Directory implements Repository{
    List<Employee> employees;

    public Directory() {
        employees = new ArrayList<>();
    }

    @Override
    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    @Override
    public void getDirectory(){
        for (Employee employee : employees){
            System.out.println(employee);
        }
    }

    @Override
    public List<Employee> searchByExperience(int experience){
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees){
            if (employee.getExperience() == experience){
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public List<Integer> getPhonenumberByName(String name){
        List<Integer> result = new ArrayList<>();
        for (Employee employee : employees){
            if (employee.getName().equals(name)){
                result.add(employee.getPhonenumber());
            }
        }
        return result;
    }

    @Override
    public Employee searchById(int id){
        for (Employee employee : employees){
            if (employee.getId() == id){
                return employee;
            }
        }
        return null;
    }
}
