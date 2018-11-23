package live;

import java.util.Scanner;

public class DataTransform {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String firstInputLine = scanner.nextLine();

        String[] firstLineElements = null;
        if(firstInputLine != null) {
            firstLineElements = firstInputLine.split(",");
        }

        System.out.println("city,zip,year,median value");
        while(true) {
            String data = scanner.nextLine();
            if(data == null || data.isEmpty()) {
                return;
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
