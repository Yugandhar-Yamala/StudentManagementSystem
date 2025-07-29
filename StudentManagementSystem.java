import java.util.*;
//Student Details
class Student {
    private int rollNo;
    private String name;
    private int age;
    private String course;
    public Student(int rollNo, String name, int age, String course) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.course = course;
    }
    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + ", Name: " + name + ", Age: " + age + ", Course: " + course;
    }
}

public class StudentManagementSystem {
    static List<Student> studentList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    //Main method
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n******* Student Management System *******");
            System.out.println("        -------------------------        ");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll No");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: viewStudents(); break;
                    case 3: searchStudent(); break;
                    case 4: updateStudent(); break;
                    case 5: deleteStudent(); break;
                    case 6: 
                        System.out.println("Exiting program...");
                        backgroundSaveThread();
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter valid input!");
                sc.next(); 
                choice = 0; 
            }
        } while (true);
    }

    public static void addStudent() {
        try {
            System.out.print("Enter Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            studentList.add(new Student(roll, name, age, course));
            System.out.println(" Student added successfully!");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            sc.next(); // clear buffer
        }
    }

    public static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("\n--- Student List ---");
            for (Student s : studentList) {
                System.out.println(s);
            }
        }
    }

    public static void searchStudent() {
        System.out.print("Enter Roll No to search: ");
        int roll = sc.nextInt();

        for (Student s : studentList) {
            if (s.getRollNo() == roll) {
                System.out.println("Student Found: " + s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void updateStudent() {
        System.out.print("Enter Roll No to update: ");
        int roll = sc.nextInt();

        for (Student s : studentList) {
            if (s.getRollNo() == roll) {
                sc.nextLine();
                System.out.print("Enter new Name: ");
                s.setName(sc.nextLine());

                System.out.print("Enter new Age: ");
                s.setAge(sc.nextInt());
                sc.nextLine();

                System.out.print("Enter new Course: ");
                s.setCourse(sc.nextLine());

                System.out.println(" Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void deleteStudent() {
        System.out.print("Enter Roll No to delete: ");
        int roll = sc.nextInt();

        Iterator<Student> itr = studentList.iterator();
        while (itr.hasNext()) {
            Student s = itr.next();
            if (s.getRollNo() == roll) {
                itr.remove();
                System.out.println(" Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void backgroundSaveThread() {
        Thread t = new Thread(() -> {
            System.out.println(" Saving data to file (simulated)...");
            try {
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                System.out.println("Background save interrupted.");
            }
            System.out.println("6 Data saved successfully!");
            System.exit(0); 
        });
        t.start();
    }
}
