package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class CommonMethods {

	static Propertiesfile pro;

	public static void pageload(WebDriver driver) throws Exception {
		Thread.sleep(5000);
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait2 = new WebDriverWait(driver, 400);
		wait2.until(pageLoadCondition);
	}

	public static void Takescreenshot(WebDriver driver, ITestResult result) throws Exception {
		pro = new Propertiesfile();

		if (result.getStatus() == ITestResult.FAILURE) {

			DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy_HHmmss");
			Date date = new Date();

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// The below method will save the screen shot in d drive with test
			// method name
			String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "Screenshots"
					+ System.getProperty("file.separator");
			try {
				FileUtils.copyFile(scrFile, new File(path + result.getName() + dateformat.format(date) + ".png"));
				System.out.println("***Placed screen shot in " + path+ " ***");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static Map<String, String> getdata() throws Exception {

		pro = new Propertiesfile();

		HashMap<String, String> testdatafromexcel = new HashMap<>();
		String Excelpath = System.getProperty("user.dir") + System.getProperty("file.separator") + "Inputs"
				+ System.getProperty("file.separator") + "Testdata.xlsx";
		FileInputStream fis = new FileInputStream(Excelpath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet s = wb.getSheetAt(0);

		Iterator<Row> itr = s.iterator();

		while (itr.hasNext()) {
			Row row = itr.next();

			Iterator<Cell> celliterator = row.iterator();

			while (celliterator.hasNext()) {

				Cell cell = celliterator.next();

				cell.setCellType(CellType.STRING);

				testdatafromexcel.put(cell.getStringCellValue(), celliterator.next().getStringCellValue());

			}

		}

		fis.close();
		return testdatafromexcel;

	}
}
