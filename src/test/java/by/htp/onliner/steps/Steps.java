package by.htp.onliner.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.onliner.driver.DriverSingleton;
import by.htp.onliner.pages.MainPage;
import by.htp.onliner.pages.ServicePage;

public class Steps {
	
	private WebDriver driver;
	private MainPage mainPage;
	private ServicePage servicePage;
	private final Logger logger = LogManager.getRootLogger();

	public WebDriver initBrowser()
	{
		driver = DriverSingleton.getDriver();
		return driver;
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}
	
	public MainPage openOnlinerMainPage(){
		mainPage = new MainPage(driver);
		mainPage.openPage();
		return mainPage;
	}
		
	public void navigateToServicesPageViaPanel(){
		mainPage = new MainPage(driver);
		mainPage.clickServicesButton();
	}
	
	public void navigateToExecutorsPage(){
		servicePage = new ServicePage(driver);
		servicePage.clickExecutorsButton();
	}
	
	public boolean isServicesPageOpened(){
		servicePage = new ServicePage(driver);
		if (servicePage.isExecutorsLabelPresent())
			return true;
		return false;
	}
	
	public boolean isExecutorsListEmpty(){
		servicePage = new ServicePage(driver);
		if (servicePage.isServiceListOffersEmpty())
			return true;
		return false;
	}
	
	public void selectService(String serviceExpandMenu, String serviceCheckbox){
		servicePage = new ServicePage(driver);
		servicePage.clickExpandServiceElement(serviceExpandMenu);
		servicePage.clickserviceCheckbox(serviceCheckbox);
	}
	
	public boolean isExecutorValid(int minOrderNumber, int minFeedbackNumber){
		servicePage = new ServicePage(driver);
		Map<Integer, Integer> map = servicePage.getNumberOfFeedbacksAndOrdersExecuted();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > minOrderNumber && entry.getKey() >= minFeedbackNumber)
				return true;
		}
		return false;
	}

}
