public class Student {
    private int age;
    private int year;
    private String name;

    public int getAge() {
        return age;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public Student(int age, int year, String name) {
        this.age = age;
        this.year = year;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", year=" + year +
                ", name='" + name + '\'' +
                '}';
    }
}
