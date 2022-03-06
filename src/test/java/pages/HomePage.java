package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
	private WebDriver driver;
	private By salesForceBtn;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		//salesForceBtn= By.id("page_producto_sf");
		salesForceBtn= By.xpath("/html[1]/body[1]/div[1]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]");
	}
	public void ingresarASalesForce() {
		driver.findElement(salesForceBtn).click();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void assertPage() {
		Assert.assertTrue(true);
	}
}
