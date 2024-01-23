package lab4;

public class Lecturer {
    private String surname;
    private String name;
    private String city;

    public Lecturer(String surname, String name, String city) {
        this.surname = surname;
        this.name = name;
        this.city = city;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student [surname=" + surname + ", name=" + name + ", city=" + city + "]";
    }
}
