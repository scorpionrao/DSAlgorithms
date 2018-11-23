package live;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CellExists {
    public static void main(String[] args) {
        WebDriver webDriver = null;
        try {
            System.setProperty("webdriver.chrome.driver","chromedriver");
            webDriver = new ChromeDriver();

            Path sampleFile = Paths.get("src/main/resources/webpage.html");
            String filePath = sampleFile.toUri().toString();
            System.out.println("File Path = " + filePath);
            webDriver.get(filePath);

            List<WebElement> rows = webDriver.findElements(By.xpath("//table//tr"));
            for(int row = 0; row < rows.size(); row++) {
                WebElement rowElement = rows.get(row);
                List<WebElement> cols = rowElement.findElements(By.xpath(".//td"));
                for(int col = 0; col < cols.size(); col++) {
                    WebElement colElement = cols.get(col);
                    String cellText = colElement.getText();
                    if(cellText != null && cellText.equals("8")) {
                        System.out.println("Row = " + (row+1));
                        System.out.println("Column = " + (col+1));
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(webDriver != null) {
                webDriver.close();
            }
        }
    }
}
