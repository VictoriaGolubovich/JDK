import java.util.List;

public interface Repository {
    void addEmployee(Employee employee);
    void getDirectory();
    List<Employee> searchByExperience(int experience);
    public List<Integer> getPhonenumberByName(String name);
    public Employee searchById(int id);

}
