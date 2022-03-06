package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SalesForceContactPage {
	private WebDriver driver;
	private By nameField;
	private By mailField;
	private By companyField;
	private By productDrop;
	private By phoneField;
	private By submitBtn;
	private By successDiv;
	
	public SalesForceContactPage(WebDriver driver) {
		this.driver = driver;
		nameField= By.id("contact_form_nombre");
		mailField= By.id("contact_form_email");
		companyField= By.id("contact_form_empresa");
		productDrop= By.id("contact_form_producto");
		phoneField= By.id("contact_form_telefono");
		submitBtn= By.id("submit-landing-form");
		successDiv = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]");
	}

	public void llenarFormulario(String nombre, String email, String empresa, String producto, String telefono) {
		driver.findElement(nameField).sendKeys(nombre);
		driver.findElement(mailField).sendKeys(email);
		driver.findElement(companyField).sendKeys(empresa);
		Select selectProduct = new Select(driver.findElement(productDrop));
		selectProduct.selectByVisibleText(producto);
		driver.findElement(phoneField).sendKeys(telefono);
		driver.findElement(submitBtn).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.findElement(successDiv).getText().contains("Gracias por contactarte con nosotros"));
	}
}
