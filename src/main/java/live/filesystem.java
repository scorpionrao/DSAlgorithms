package live;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Filesystem {

    public static double sum(String filePath) throws IOException, NumberFormatException {

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            if(filePath == null) {
                return 0.0;
            }

            File file = new File(filePath);
            if(!file.exists() || !file.isFile()) {
                throw new RuntimeException("Error occurred while accessing the file. Please check the file path and ensure it is a file.");
            }

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            double sum = 0.0;
            String line;

            while((line = bufferedReader.readLine()) != null) {

                int input = Integer.parseInt(line);
                sum = sum + (double) input;
            }

            return sum;
        } catch (Exception e) {
            throw e;
        } finally {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if(fileReader != null) {
                fileReader.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        double result = sum("file2.txt");
        System.out.println("Sum = " + result);
    }
}
