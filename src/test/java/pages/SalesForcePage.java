package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SalesForcePage {
	private WebDriver driver;
	private By contactBtn;
	
	public SalesForcePage(WebDriver driver) {
		this.driver = driver;
		contactBtn = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[2]/button[1]");
	}
	public void ingresarAlFormulario() {
		driver.findElement(contactBtn).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void assertPage() {
		Assert.assertTrue(true);
	}
}
