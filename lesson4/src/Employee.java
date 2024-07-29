public class Employee {
    private int id;
    private int phonenumber;
    private String name;
    private int experience;

    public Employee(int id, String name, int phonenumber, int experience) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return id + ". " +
                name +
                ", номер телефона: " + phonenumber +
                ", стаж: " + experience;
    }
}
