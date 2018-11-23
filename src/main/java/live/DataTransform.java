package live;

import java.util.Scanner;

public class DataTransform {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();

        String[] firstLineElements = null;
        if(firstLine != null) {
            firstLineElements = firstLine.split(",");
        }

        System.out.println("city,zip,year,median value");
        while(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if(data == null || data.isEmpty()) {
                break;
            }

            String[] elements = data.split(",");
            for(int i = 2; i < elements.length; i++) {
                System.out.println(
                        elements[0] + "," +
                        elements[1] + "," +
                        firstLineElements[i] + "," +
                        elements[i]);
            }
        }

    }
}
