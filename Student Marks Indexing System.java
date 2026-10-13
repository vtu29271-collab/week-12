import java.util.*;

public class Main {

    static class Student {
        String name;
        double marks;

        public Student(String name, double marks) {
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Marks: " + marks;
        }
    }

    public static void main(String[] args) {
        Map<Integer, Student> studentIndex = new HashMap<>();
        studentIndex.put(101, new Student("Alice", 88.5));
        studentIndex.put(102, new Student("Bob", 92.0));
        studentIndex.put(103, new Student("Charlie", 79.5));
        studentIndex.put(104, new Student("Diana", 95.0));
        int[] queryIDs = {102, 105, 101};
        System.out.println("=== STUDENT MARKS INDEXING SYSTEM ===");
        for (int id : queryIDs) {
            System.out.print("Query ID " + id + ": ");
            if (studentIndex.containsKey(id)) {
                System.out.println(studentIndex.get(id));
            } else {
                System.out.println("Record Not Found");
            }
        }
    }
}