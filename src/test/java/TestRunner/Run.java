package TestRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
//		features= "D:\\CoreJavaProject\\CucumberFramework\\Features\\Customers.feature",
//		features= "D:\\CoreJavaProject\\CucumberFramework\\Features\\",
		features= {"D:\\CoreJavaProject\\CucumberFramework\\Features\\Customers.feature",
				   "D:\\CoreJavaProject\\CucumberFramework\\Features\\Login.feature"},
//		features = "D:\\CoreJavaProject\\CucumberFramework\\Features\\Login.feature",
		glue = "StepDefination",
		dryRun = false,
		monochrome = true,
		tags = "@sanity",
//		plugin = {"pretty","html:target/CucumberReports/CustomerReport.html "
//				           "junit:target/CucumberReports/Report3.xml",
//		                   "json:target/CucumberReports/Report2.json"
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}	
		)

public class Run extends AbstractTestNGCucumberTests{
//This is EMpty File
}
