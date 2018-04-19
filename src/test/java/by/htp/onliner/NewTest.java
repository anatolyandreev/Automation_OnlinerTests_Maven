package by.htp.onliner;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.htp.onliner.steps.Steps;

public class NewTest {

	private final String serviceType = "Компьютерная помощь";
	private final String service = "Установка и настройка ПО";
	private final int minOrdersNumber = 2;
	private final int minFeedbackNumber = 3;
	
	private Steps steps;
	private WebDriver driver;

	@BeforeClass
	public void testSetup() {
		steps = new Steps();
		driver = steps.initBrowser();
	}

	@AfterClass
	public void afterTest() {
		steps.closeDriver();
	}
	
	@Test(priority=1)
	public void checkIfExecutorsArePresent() {
		steps.openOnlinerMainPage();
		steps.navigateToServicesPageViaPanel();
		steps.navigateToExecutorsPage();
		assertTrue(steps.isServicesPageOpened(), "\"Services\" page is not opened");
		assertFalse(steps.isExecutorsListEmpty(), "Executors list is empty, i.e. no executors in Minsk");
	}

	@Test(priority=2)
	public void checkExecutorsForSpecificService() throws InterruptedException {
		Thread.sleep(2000);
		steps.selectService(serviceType, service);
		assertFalse(steps.isExecutorsListEmpty(),
				"Executors list is empty, i.e. no executors, i.e. no executors for service type " + serviceType
						+ " and service " + service);
	}
	
	@Test(priority=3)
	public void checkForValidExecutor(){
		assertTrue(steps.isExecutorValid(minOrdersNumber, minFeedbackNumber));
	}
}
