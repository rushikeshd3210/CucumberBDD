package StepDefination;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDef extends BaseClass {

	@Before("@sanity")
	public void setup1() throws Throwable {
		readConfig=new ReadConfig();
		log=LogManager.getLogger("StepDef");
		System.out.println("SetUp1-Sanity Method Executed.......");
		String browser =readConfig.getBrowser();
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
		log.fatal("Setup1 Executed..........");
	}
	
//	@Before("@regression")
//	public void setup2() {
//		System.out.println("SetUp2-Regression Method Executed.......");
//		WebDriverManager.chromedriver().setup();
//		driver=new ChromeDriver();
//		log.info("Setup2 Executed..........");
//	}
	
	@Given("User Launch Chrome Browser")
	public void user_launch_chrome_browser() {
	  
	  loginPg=new LoginPage(driver);
	  addNewCustPg=new AddNewCustomerPage(driver);
	  SearchCustPg=new SearchCustomerPage(driver);
	  log.info("Chrome Browser Launched");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
      driver.get(url);	    
      log.info("Url Opened");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emialAdd, String pwd) throws Throwable {
	   loginPg.enterEmail(emialAdd);
	   loginPg.enterPassword(pwd);
	   log.info("email and password entered");
	}

	@When("Click on Login")
	public void click_on_login() {
	    loginPg.clickOnLoginButton();
	    log.info("Click On Login Button");
	}
	
//////////////////////////    Login Feature   //////////////////////////
	
	@Then("Page Title Should be {string}")
	public void page_title_should_be(String expectedTitle) {
	   String actualTitle=driver.getTitle();
	   if(actualTitle.equals(expectedTitle)) {
		   log.warn("Test passed: Login feature :Page title matched.");
		   Assert.assertTrue(true);//pass
	   }
	   else {
		   log.warn("Test Failed: Login feature- page title not matched.");
		   Assert.assertTrue(false);//fail
	   }
	}

	@When("User click on Logout link")
	public void user_click_on_logout_link() throws Throwable  {
		  Thread.sleep(2000);
	    loginPg.clickOnLogoutButton();
	    log.info("user clicked on logout link.");
	}
    
//	@Then("close browser")
//	public void close_browser() {
//	    driver.close();
//	    log.info("Browser closed");
////	    driver.quit();
//	}
	
///////////////////////////////   Add New Customer   /////////////////////////	
	
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
	   String actualTitle=addNewCustPg.getPageTitle();
	   String expectedTitle="Dashboard / nopCommerce administration";
	   if(actualTitle.equals(expectedTitle)) {
		   log.info("user can view dashboard test passed.");
		   Assert.assertTrue(true);
	   }
	   else {
		   log.warn("user can view dashboard test failed.");
		  Assert.assertTrue(false);
	   } 
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
	    addNewCustPg.clickOnCustomersMenu();
	    log.info("customer menu clicked");
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
	    addNewCustPg.clickOnCustomersMenuItem();
	    log.info("customer menu item clicked");
	}

	@When("click on Add new button")	
	public void click_on_add_new_button() {
	   addNewCustPg.clickOnAddnew();
	   log.info("clicked on add new button.");
	}  

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	    String actualTitle=addNewCustPg.getPageTitle();
	    String expectedTitle= "Add a new customer / nopCommerce administration";
	    if(actualTitle.equals(expectedTitle)) {
	    	 log.info("User can view Add new customer page- passed");
			 Assert.assertTrue(true);
		   }
		   else {
			  log.info("User can view Add new customer page- failed"); 
			  Assert.assertTrue(false);
		   }
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
//	    addNewCustPg.enterEmail("aaaa@gmail.com");
		addNewCustPg.enterEmail(generateEmailId()+ "@gmail.com");
		addNewCustPg.enterPassword("test1");
		addNewCustPg.enterFirstName("Jay");
		addNewCustPg.enterLastName("Trix");
		addNewCustPg.enterGender("male");
		addNewCustPg.enterDob("6/13/1988");
		addNewCustPg.enterCompanyName("Abc Xyz");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor 1");
		log.info("customer information entered");
	}

	@When("click on Save button")
	public void click_on_save_button() {
	    addNewCustPg.clickOnSave();
	    log.info("clicked on save button");
	}
	
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {
	    String bodyTagText=driver.findElement(By.tagName("Body")).getText();
	    if(bodyTagText.contains(exptectedConfirmationMsg)) {
	    	Assert.assertTrue(true);
	    	log.info("User can view confirmation message - passed");
	    }
	    else {
	    	log.warn("User can view confirmation message - failed");
	    	Assert.assertTrue(false);
	    }
	}

////////////////////////////////  Search Customer by mail //////////////////////
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
	    SearchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
	    log.info("Email address entered");
	}

	@When("Click on search button")
	public void click_on_search_button() throws Throwable {
	    SearchCustPg.clickOnSearchButton();
	    log.info("Clicked on search button.");
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	   String expectedEmail= "victoria_victoria@nopCommerce.com";
//	   Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));
	   if(SearchCustPg.searchCustomerByEmail(expectedEmail)== true)
       {
    	  Assert.assertTrue(true);  
    	  log.info("User should found Email in the Search table - passed");
       }
	   else 
	   {
		  log.info("User should found Email in the Search table - failed");
		  Assert.assertTrue(false);
	   }			
	}
	
//////////////////////////////// Search Customer by name //////////////////////
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
	  SearchCustPg.enterFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
	  SearchCustPg.enterLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName= "Victoria Terces";
		   if(SearchCustPg.searchCustomerByName(expectedName)== true)
	       {
	    	  Assert.assertTrue(true);  
	       }
		   else 
		   {
			  Assert.assertTrue(false);
		   }   
	}
	
//	@After
	public void teardowon(Scenario sc) {
		System.out.println("Tear Down Method Executed.......");
		if(sc.isFailed()==true)
		{
			String fileWithPath = "D:\\CoreJavaProject\\CucumberFramework\\Screenshot\\FailedScShot.png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			
			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination
			File DestFile=new File(fileWithPath);

			//Copy file at destination
			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.quit();
	}
	
	@AfterStep
	public void addScreenshot (Scenario scenario) {
      if(scenario.isFailed())
      {
    	final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	//attach image file report(data, media type, name of the attachment) 
    scenario.attach(screenshot, "image/png", scenario.getName());
      }
	}
	
//	@BeforeStep
//	public void beforeStepMethodDemo() {
//		System.out.println("This is Before Step..........");
//	}
//	
//	@AfterStep
//	public void afterStepMethodDemo() {
//		System.out.println("This is After Step..........");
//	}	
 }