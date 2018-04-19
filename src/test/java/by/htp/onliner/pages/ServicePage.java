package by.htp.onliner.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServicePage extends BasePage {

	private final Logger logger = LogManager.getLogger();
	private static final String BASE_URL = "https://s.onliner.by/";

	@FindBy(xpath = "//span[text()='Исполнители']")
	private WebElement executorsButton;

	@FindBy(xpath = "//div[@class='service-header__title service-header__title_huge' and text()='Исполнители']")
	private WebElement executorsLabel;

	@FindBy(xpath = "//span[@class='service-form__checkbox-sign ng-binding' and text()='Минск']")
	private WebElement regionOfServiceMinskLabelElement;

	@FindBy(xpath = "//div[@class='service-offers__list']/*")
	private List<WebElement> serviceOffersList;

	@FindBy(xpath = "//a[@class='service-offers__details-item service-offers__details-item_ok ng-binding ng-scope']")
	private List<WebElement> numberOfOrdersExecutedList;

	@FindBy(xpath = "//span[@class='service-response__rating-text ng-binding']")
	private List<WebElement> numberOfFeedbacksList;

	public ServicePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	private final String serviceExpandElementRegexp = "//a[text()='%s']";
	private final String serviceCheckboxRegexp = "//span[@class='service-form__checkbox-text']/*[text()='%s']";

	@Override
	public void openPage() {
		driver.get(BASE_URL);
		logger.info("Navigating to " + BASE_URL);
	}

	public void clickExecutorsButton() {
		executorsButton.click();
	}

	public boolean isExecutorsLabelPresent() {
		return executorsButton.isDisplayed();
	}

	public void clickregionOfServiceMinskCheckbox() {
		regionOfServiceMinskLabelElement.click();
	}

	public int getNumberOfOffers() {
		return serviceOffersList.size();
	}

	public boolean isServiceListOffersEmpty() {
		if (serviceOffersList.size() > 0)
			return false;
		return true;
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void clickExpandServiceElement(String serviceExpandMenu) {
		WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions
				.elementToBeClickable(By.xpath(String.format(serviceExpandElementRegexp, serviceExpandMenu))));
		scrollToElement(element);
		element.click();
	}

	public void clickserviceCheckbox(String serviceCheckbox) {
		new WebDriverWait(driver, 5).until(ExpectedConditions
				.elementToBeClickable(By.xpath(String.format(serviceCheckboxRegexp, serviceCheckbox)))).click();
	}

	public HashMap<Integer, Integer> getNumberOfFeedbacksAndOrdersExecuted() {
		HashMap<Integer, Integer> intMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < getNumberOfOffers(); i++) {
			intMap.put(Integer.parseInt(numberOfOrdersExecutedList.get(i).getText().replaceAll("[^0-9]", "")),
					Integer.parseInt(numberOfFeedbacksList.get(i).getText().replaceAll("[^0-9]", "")));
		}
		return intMap;
	}
	
	

}
