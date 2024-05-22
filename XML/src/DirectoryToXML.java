import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryToXML {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập đường dẫn thư mục
        System.out.println("Nhập đường dẫn của thư mục:");
        String directoryPath = scanner.nextLine();
        File directory = new File(directoryPath);

        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        buildXML(directory, xmlBuilder);

        // Ghi XML vào file
        try {
            FileWriter writer = new FileWriter("directory_listing.xml");
            writer.write(xmlBuilder.toString());
            writer.close();
            System.out.println("Danh sách thư mục được lưu vào file: directory_listing.xml");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi lưu XML vào file.");
            e.printStackTrace();
        }

        scanner.close();
    }

    private static void buildXML(File directory, StringBuilder xmlBuilder) {
        xmlBuilder.append("<").append(directory.getName()).append(">\n");

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    buildXML(file, xmlBuilder);
                } else {
                    xmlBuilder.append("\t<file>").append(file.getName()).append("</file>\n");
                }
            }
        }

        xmlBuilder.append("</").append(directory.getName()).append(">\n");
    }
}
