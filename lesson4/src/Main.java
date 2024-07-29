public class Main {
    public static void main(String[] args) throws Exception {

        Directory directory = new Directory();
        directory.addEmployee(new Employee(1, "Alex", 87654, 5));
        directory.addEmployee(new Employee(2, "Leonardo", 45329, 7));
        directory.addEmployee(new Employee(3, "Michel", 50936, 7));
        directory.addEmployee(new Employee(4, "Karl", 24683, 9));
        directory.addEmployee(new Employee(5, "Tom", 29364, 15));
        directory.getDirectory();
        System.out.println();

        String name = "Leonardo";
        System.out.println("Номер телефона сотрудника по имени " + name + " : " + directory.getPhonenumberByName(name));
        System.out.println();

        int experience = 7;
        System.out.println("Сотрудник со стажем " + experience + " месяцев: " + directory.searchByExperience(experience));
        System.out.println();

        int id = 2;
        System.out.println("Сотрудник с табельным номером " + id + " : " + directory.searchById(id));
    }
}
