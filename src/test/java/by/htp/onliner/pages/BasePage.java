package by.htp.onliner.pages;

import org.openqa.selenium.WebDriver;

import by.htp.onliner.driver.DriverSingleton;

public abstract class BasePage {
	
	protected WebDriver driver;

	public abstract void openPage();

	public BasePage(WebDriver driver)
	{
		this.driver = driver;
	}
}
