import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class main {
    static Student s1 = new Student(19, 2, "Vadim"),
            s2 = new Student(21, 1, "Timur"),
            s3 = new Student(18, 3, "Mage");

    public static void main(String[] args) {

        System.out.println("Age priority");
        test(new PriorityQueue<>(Comparator.comparing(Student::getAge)));
        System.out.println("Year priority");
        test(new PriorityQueue<>(Comparator.comparing(Student::getYear)));
        System.out.println("Name priority");
        test(new PriorityQueue<>(Comparator.comparing(Student::getName)));
    }

    public static void test(Queue<Student> queue) {
        queue.add(s1);
        queue.add(s2);
        queue.add(s3);

        while (queue.size() != 0) {
            System.out.println(queue.poll());
        }
    }
}
