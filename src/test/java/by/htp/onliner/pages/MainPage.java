package by.htp.onliner.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage{

	private final Logger logger = LogManager.getLogger();
	private static final String BASE_URL = "https://onliner.by/";
	
	@FindBy(xpath="//span[text()='Услуги']")
	private WebElement servicesButton;
	
	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	@Override
	public void openPage() {
		driver.get(BASE_URL);
		logger.info("Navigating to " + BASE_URL);		
	}

	public void clickServicesButton(){
		servicesButton.click();
	}
	
}
