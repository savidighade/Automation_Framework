package util_Classes;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataProvider {
	@DataProvider(name = "data")

	public Object[][] readExcelData()

	{

		return new ExcelRiderMain(getClass()).readData();

	}

	@Test(dataProvider = "data")

	public void m5(Map<String, String> input)

	{

		System.out.println(input.get("Name"));
		System.out.println(input.get("Age"));
		System.out.println(input.get("EmpID"));

	}

}
