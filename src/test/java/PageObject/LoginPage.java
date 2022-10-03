package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;
	
  public LoginPage(WebDriver rDriver) 
  {
	  ldriver=rDriver;
	  PageFactory.initElements(rDriver, this);
  }
  
  @FindBy(xpath = "//input[@id='Email']")
  WebElement emial;
  
  @FindBy(xpath = "//input[@id='Password']")
  WebElement password;
  
  @FindBy(xpath = "//button[normalize-space()='Log in']")
  WebElement LoginBtn;
  
  @FindBy(linkText = "Logout")
  WebElement logout;
  
  public void enterEmail(String emialAdd) throws Throwable
  {
	  emial.clear();
	  Thread.sleep(2000);
	  emial.sendKeys(emialAdd);
  }

  public void enterPassword(String pwd)
  {
	  password.clear();
	  password.sendKeys(pwd);
  }
  
  public void clickOnLoginButton()
  {
     LoginBtn.click();
  } 
  
  public void clickOnLogoutButton() throws Throwable  
  {
//	  Thread.sleep(2000);
	  logout.click();
  }
}
