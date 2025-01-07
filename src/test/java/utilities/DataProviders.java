package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path = ".\\testData\\Opencart_LoginData.xlsx"; // Path to the Excel file
        ExcelUtility xlutil = new ExcelUtility(path); // Create an object for ExcelUtility

        int totalRows = xlutil.getRowCount("Sheet1"); // Get total rows in the sheet
        int totalCols = xlutil.getCellCount("Sheet1", 1); // Get total columns in the first row

        String[][] loginData = new String[totalRows][totalCols]; // Create a 2D array to store data

        // Read data from Excel and store it in the 2D array
        for (int i = 1; i <= totalRows; i++) { // Loop through rows (starting from 1)
            for (int j = 0; j < totalCols; j++) { // Loop through columns
                loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // Store data in the array
            }
        }

        return loginData; // Return the 2D array
    }
}