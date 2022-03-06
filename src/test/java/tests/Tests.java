package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import pages.HomePage;
import pages.SalesForceContactPage;
import pages.SalesForcePage;

public class Tests {
	private WebDriver driver;
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://virtualdreams.io/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	@Test
	public void validacionesCampoRequerido() {
		HomePage homePage = new HomePage(driver);
		homePage.ingresarASalesForce();
		Helpers helper = new Helpers();
		helper.sleepSeconds(2);
		SalesForcePage salesForcePage = new SalesForcePage(driver);
		salesForcePage.ingresarAlFormulario();
		SalesForceContactPage contactPage = new SalesForceContactPage(driver);
		//lectura de casos de prueba
		//for para correr los distintos casos
		contactPage.llenarFormulario("marcia", "marcia990_14@hotmail.com", "Delphos", "Marketing", "1122334455");
		helper.sleepSeconds(3);
		contactPage.assertPage();
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
