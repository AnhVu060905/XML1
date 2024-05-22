import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentListToXML {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        // Nhập thông tin học sinh
        students.add(inputStudent());
        students.add(inputStudent());
        students.add(inputStudent());

        // Tạo XML từ danh sách học sinh và lưu vào file
        createXMLAndSave(students);
    }

    static Student inputStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên học sinh:");
        String name = scanner.nextLine();
        System.out.println("Nhập tuổi:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập GPA:");
        float gpa = Float.parseFloat(scanner.nextLine());
        return new Student(name, age, gpa);
    }

    static void createXMLAndSave(List<Student> students) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<students>\n");
        for (Student student : students) {
            xmlBuilder.append("<student>\n");
            xmlBuilder.append("<name>").append(student.getName()).append("</name>\n");
            xmlBuilder.append("<age>").append(student.getAge()).append("</age>\n");
            xmlBuilder.append("<gpa>").append(student.getGpa()).append("</gpa>\n");
            xmlBuilder.append("</student>\n");
        }
        xmlBuilder.append("</students>");

        // Lưu XML vào file
        try {
            FileWriter writer = new FileWriter("student_list.xml");
            writer.write(xmlBuilder.toString());
            writer.close();
            System.out.println("Danh sách sinh viên được lưu vào file: student_list.xml");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi lưu XML vào file.");
            e.printStackTrace();
        }
    }

    static class Student {
        private String name;
        private int age;
        private float gpa;

        public Student(String name, int age, float gpa) {
            this.name = name;
            this.age = age;
            this.gpa = gpa;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public float getGpa() {
            return gpa;
        }
    }
}
