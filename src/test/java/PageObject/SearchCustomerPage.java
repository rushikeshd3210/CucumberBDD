package PageObject;

import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {

	WebDriver ldriver;

	public SearchCustomerPage(WebDriver rDriver) {
		ldriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}

	@FindBy(id = "SearchEmail")
	WebElement emailAdd;

	@FindBy(id = "search-customers")
	WebElement searchBtn;

	@FindBy(xpath = "(//table[@role='grid'])")
	WebElement searchResult;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;

//	  @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr[1]/td")
//	  List<WebElement> tableColumns;

	@FindBy(id = "SearchFirstName")
	WebElement firstName;

	@FindBy(id = "SearchLastName")
	WebElement lastName;

	public void enterEmailAdd(String email) {
		emailAdd.sendKeys(email);
	}

	public void clickOnSearchButton() throws Throwable {
		searchBtn.click();
		Thread.sleep(2000);
	}

	public boolean searchCustomerByEmail(String email) {
		boolean found = false;
		int ttlRows = tableRows.size();
//		  int ttlColumns = tableColumns.size();
		for (int i = 1; i <= ttlRows; i++) {
			WebElement webElementEmail = ldriver
					.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[2]"));
			String actualEmailAdd = webElementEmail.getText();
			if (actualEmailAdd.equals(email)) {
				found = true;
			}
		}
		return found;
	}

	public void enterFirstName(String firstNameText) {
		firstName.sendKeys(firstNameText);
	}

	public void enterLastName(String LastNameText) {
		lastName.sendKeys(LastNameText);
	}
	
	public boolean searchCustomerByName(String name) {
		boolean found = false;
		
		int ttlRows = tableRows.size();
		
		for (int i = 1; i <= ttlRows; i++) 
	   {
		WebElement webElementName = ldriver
				.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[3]"));		
		String actualName = webElementName.getText();
		if (actualName.equals(name)) {
				found = true;
				break;
			}
		}
		return found;
	}
}