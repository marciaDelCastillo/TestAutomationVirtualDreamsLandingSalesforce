package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import helpers.Helpers;

public class SalesForceContactPage {
	private WebDriver driver;
	private By nameField;
	private By mailField;
	private By companyField;
	private By productDrop;
	private By phoneField;
	private By submitBtn;
	private By successDiv;
	private By errorLabels;
	
	public SalesForceContactPage(WebDriver driver) {
		this.driver = driver;
		nameField= By.id("contact_form_nombre");
		mailField= By.id("contact_form_email");
		companyField= By.id("contact_form_empresa");
		productDrop= By.id("contact_form_producto");
		phoneField= By.id("contact_form_telefono");
		submitBtn= By.id("submit-landing-form");
		successDiv = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]");
		errorLabels = By.tagName("label");
	}

	public void llenarFormulario(String nombre, String email, String empresa, String producto, String telefono) {

		//cargo los valores
		driver.findElement(nameField).sendKeys(nombre);
		driver.findElement(mailField).sendKeys(email);
		driver.findElement(companyField).sendKeys(empresa);
		Select selectProduct = new Select(driver.findElement(productDrop));
		selectProduct.selectByVisibleText(producto);
		driver.findElement(phoneField).sendKeys(telefono);
		Helpers helper = new Helpers();
		helper.sleepSeconds(2);
		
		driver.findElement(submitBtn).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	
	//datos: nombre, mail, empresa, producto, telefono (index es el valor que viene vacio y cuya validacion debe aparecer)
	public void assertPageCase2to6(int index) {
		WebElement labelError=null;
		String nombreError="";
		switch(index)
		{
		   case 0 :
		      labelError = driver.findElement(By.id("contact_form_nombre-error"));
		      nombreError = "Nombre";
		      break; // break es opcional
		   case 1 :
			   labelError = driver.findElement(By.id("contact_form_email-error"));
			      nombreError = "Email";
		      break; // break es opcional
		   case 2 :
			   labelError = driver.findElement(By.id("contact_form_empresa-error"));
			      nombreError = "Empresa";
			  break; // break es opcional   
		   case 3 :
			   labelError = driver.findElement(By.id("contact_form_producto-error"));
			      nombreError = "Producto";
			  break; // break es opcional
		   case 4 :
			   labelError = driver.findElement(By.id("contact_form_telefono-error"));
			      nombreError = "Teléfono";
			  break; // break es opcional
		   default : 
		      Assert.assertTrue(false, "Error de programación");
		}
		Assert.assertTrue(labelError.getText().contains("Este campo es obligatorio"), "La validación del campo "+nombreError+" no funcionó");
		/*
		List <WebElement> mensajes = driver.findElements(errorLabels);
		Assert.assertTrue((mensajes.get(0)).getText().contains("Este campo es obligatorio"), "La validación del indice "+index+" no funcionó");
		*/
	}
	
	public void assertPageCase7() {
			Assert.assertTrue(driver.findElement(successDiv).getText().contains("Gracias por contactarte con nosotros"), "El formulario no fue enviado");
		
	}
}
